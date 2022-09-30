package space.moontalk.mc.cpspeed.teleport;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.val;

import static space.moontalk.mc.more.bukkit.MoreBukkit.*;

import space.moontalk.mc.cpspeed.message.MessageProvider;
import space.moontalk.mc.cpspeed.message.Time;

public class DefaultTeleporationManager implements TeleportManager {
    // Key - <to>, value - <from> list
    private final @NotNull Map<UUID, Set<UUID>> requestsTable                = new HashMap<>();
    private final @NotNull Map<UUID, Integer>   lastTeleporationSecondTable = new HashMap<>();
    private final @NotNull Set<UUID>            teleporting                 = new HashSet<>(); 

    @Getter
    private final @NotNull MessageProvider messageProvider;

    @Getter
    private final @NotNull World defaultWorld;

    @Getter
    private final @NotNull JavaPlugin plugin;

    @Getter
    private final int delaySeconds;

    @Getter
    private final int opDelaySeconds;

    @Getter
    private final int coolDownSeconds;

    @Getter
    private final int opCoolDownSeconds;

    @Getter
    private final int timeOutSeconds;

    private DefaultTeleporationManager(
        @NotNull MessageProvider messageProvider,
        @NotNull World           defaultWorld,
        @NotNull JavaPlugin      plugin,
        int                      delaySeconds,
        int                      opDelaySeconds,
        int                      coolDownSeconds,
        int                      opCoolDownSeconds,
        int                      timeOutSeconds
    ) {
        this.messageProvider   = messageProvider;
        this.defaultWorld      = defaultWorld;
        this.plugin            = plugin;
        this.delaySeconds      = delaySeconds;
        this.opDelaySeconds    = opDelaySeconds;
        this.coolDownSeconds   = coolDownSeconds;
        this.opCoolDownSeconds = opCoolDownSeconds;
        this.timeOutSeconds    = timeOutSeconds;
    }

    @Override
    public int getCoolDownSeconds(@NotNull Player player) {
        val lastTeleporationSecond = getLastTeleporationSecond(player);
        val currentSecond          = getCurrentSecond();
        val delta                  = currentSecond - lastTeleporationSecond;
        val coolDownSeconds        = player.isOp() ? opCoolDownSeconds : this.coolDownSeconds;
        val left                   = coolDownSeconds - delta;
        return Math.max(0, left); 
    }

    @Override
    public boolean isTeleporting(@NotNull Player player) {
        val playerUniqueId = player.getUniqueId();
        return teleporting.contains(playerUniqueId);
    }

    @Override
    public void teleport(@NotNull Player player, @NotNull Location location) throws Exception {
        throwIfCannotTeleport(player);

        val message = messageProvider.makeTeleportingToLocationMessage(player);
        player.sendMessage(message);

        val locationProvider = new LocationWrapper(location);
        teleport(player, locationProvider);
    }

    @Override
    public void teleport(
        @NotNull Player   player,
        @NotNull Location location,
        @NotNull String   locationName
    ) throws Exception {
        throwIfCannotTeleport(player);
        
        val message = messageProvider.makeTeleportingToNamedLocationMessage(player, locationName);
        player.sendMessage(message);

        val locationProvider = new LocationWrapper(location);
        teleport(player, locationProvider);
    }

    @Override
    public void sendRequest(@NotNull Player from, @NotNull Player to) throws Exception {
        throwIfSendingYourSelf(from, to);
        throwIfCannotTeleport(from);
        
        val fromUniqueId = from.getUniqueId();
        val toUniqueId   = to.getUniqueId();
        val requests     = requestsTable.computeIfAbsent(toUniqueId, k -> new HashSet<>());

        if (requests.contains(fromUniqueId))
            throwAlreadySent(from, to);

        requests.add(fromUniqueId);

        notifyRequest(from, to);

        val scheduler = Bukkit.getScheduler();
        scheduler.runTaskLater(plugin, () -> {
            if (!requests.contains(fromUniqueId))
                return;

            notifyTimeOut(from, to);
            requests.remove(fromUniqueId);
        }, timeOutSeconds * SECOND_TICKS);
    }

    @Override
    public void acceptRequest(@NotNull Player from, @NotNull Player to) throws Exception {
        val fromUniqueId = from.getUniqueId();
        val toUniqueId   = to.getUniqueId();
        val requests     = requestsTable.computeIfAbsent(toUniqueId, k -> new HashSet<>());

        if (!requests.contains(fromUniqueId))
            throwMissingRequest(from, to); 

        throwIfCannotTeleportNow(from, to);
        notifyAccepted(from, to);
        requests.remove(fromUniqueId);

        val locationProvider = new PlayerLocationProvider(to);
        teleport(from, locationProvider);
    }

    @Override
    public void denyRequest(@NotNull Player from, @NotNull Player to) throws Exception {
        val fromUniqueId = from.getUniqueId();
        val toUniqueId   = to.getUniqueId();
        val requests     = requestsTable.computeIfAbsent(toUniqueId, k -> new HashSet<>());

        if (!requests.contains(fromUniqueId))
            throwMissingRequest(from, to); 

        notifyDenied(from, to);
        requests.remove(fromUniqueId);
    }

    @Override
    public @NotNull Set<Player> getGotRequests(@NotNull Player to) {
        val toUniqueId = to.getUniqueId();
        val requests   = requestsTable.get(toUniqueId);

        if (requests == null)
            return Collections.emptySet();

        return requests.stream()
                       .map(uuid -> {
                            try {
                                return getPlayer(uuid);
                            } catch (Exception exception) {
                                return null;
                            }
                       })
                       .filter(p -> p != null)
                       .collect(Collectors.toSet());
    }

    @Override
    public @NotNull Set<Player> getSentRequests(@NotNull Player from) {
        val fromUniqueId = from.getUniqueId();
        val result       = new HashSet<Player>();

        for (val entry : requestsTable.entrySet()) {
            val fromUniqueIdList = entry.getValue();

            if (fromUniqueIdList.contains(fromUniqueId)) {
                val toUniqueId = entry.getKey();
                val to         = getPlayer(toUniqueId);
                result.add(to);
            }
        }

        return result;
    }

    private @NotNull int getLastTeleporationSecond(@NotNull Player player) {
        val defaultSecond  = getDefaultLastTeleportationSecond();
        val playerUniqueId = player.getUniqueId();
        return lastTeleporationSecondTable.getOrDefault(playerUniqueId, defaultSecond);
    }

    private int getDefaultLastTeleportationSecond() {
        return getCurrentSecond() - coolDownSeconds;
    }

    private void throwIfCannotTeleport(@NotNull Player player) throws Exception {
        if (isTeleporting(player))
            throwAlreadyTeleporting(player);

        if (!isCoolDownOver(player))
            throwWaitUntilYourCoolDownOver(player);
    }

    private void throwAlreadyTeleporting(@NotNull Player player) throws Exception {
        val message = messageProvider.makeAlreadyTeleportingMessage(player);
        throw new Exception(message);
    }

    private void throwWaitUntilYourCoolDownOver(@NotNull Player player) throws Exception {
        val coolDownSeconds = getCoolDownSeconds(player);
        val coolDown        = new Time(coolDownSeconds);
        val delay           = new Time(delaySeconds);
        val message         = messageProvider.makeWaitUntilCoolDownOverMessage(player, coolDown, delay);
        throw new Exception(message);
    }

    private void throwIfSendingYourSelf(@NotNull Player from, @NotNull Player to) throws Exception {
        if (from == to)
            throwSendingYourSelf(from);
    }

    private void throwSendingYourSelf(@NotNull Player from) throws Exception {
        val message = messageProvider.makeCannotSendRequestToYourSelfMessage(from);
        throw new Exception(message);
    }

    private void throwAlreadySent(@NotNull Player from, @NotNull Player to) throws Exception {
        val message = messageProvider.makeAlreadySentRequestMessage(from, to);
        throw new Exception(message);
    }

    private void notifyRequest(@NotNull Player from, @NotNull Player to) {
        var message = messageProvider.makeRequestSentMessage(from, to);
        from.sendMessage(message);

        message = messageProvider.makeRequestGotMessage(from, to);
        to.sendMessage(message);
    }

    private void notifyTimeOut(@NotNull Player from, @NotNull Player to) {
        var message = messageProvider.makeYourRequestTimeOutMessage(from, to);
        from.sendMessage(message);

        message = messageProvider.makeRequestTimeOutMessage(from, to);
        to.sendMessage(message);
    }

    private void throwMissingRequest(@NotNull Player from, @NotNull Player to) throws Exception {
        val message = messageProvider.makeYouHaveNoRequestFromMessage(from, to);
        throw new Exception(message);
    }

    private void notifyDenied(@NotNull Player from, @NotNull Player to) throws Exception {
        var message = messageProvider.makeYouHaveDeniedRequestMessage(from, to);
        to.sendMessage(message);

        message = messageProvider.makeYourRequestWasDeniedMessage(from, to);
        from.sendMessage(message);
    }

    private void throwIfCannotTeleportNow(@NotNull Player from, @NotNull Player to) throws Exception {
        if (!canTeleport(from))
            throwCannotTeleportNow(from, to);
    }

    private void throwCannotTeleportNow(@NotNull Player from, @NotNull Player to) throws Exception {
        val message = messageProvider.makeCannotBeTeleportedNowMessage(from, to);
        throw new Exception(message);
    }

    private void notifyAccepted(@NotNull Player from, @NotNull Player to) throws Exception {
        var message = messageProvider.makeYouHaveAcceptedRequestMessage(from, to);
        to.sendMessage(message);

        message = messageProvider.makeTeleportingToYouMessage(from, to);
        to.sendMessage(message);

        message = messageProvider.makeYourRequestWasAcceptedMessage(from, to);
        from.sendMessage(message);
    }

    private void teleport(@NotNull Player from, @NotNull LocationProvider locationProvider) {
        val fromUniqueId = from.getUniqueId();
        teleporting.add(fromUniqueId);

        val scehduler    = Bukkit.getScheduler(); 
        val delaySeconds = from.isOp() ? opDelaySeconds : this.delaySeconds;
        val status       = new Object() {
            int leftSeconds = delaySeconds;
            int task;
        };

        status.task = scehduler.scheduleSyncRepeatingTask(plugin, () -> {
            if (delaySeconds != 0)
                notifyCountDown(from, locationProvider, status.leftSeconds);

            if (status.leftSeconds <= 0) {
                notifyTeleported(from, locationProvider);

                val oldLocation = from.getLocation();
                playEffect(oldLocation);

                val location = locationProvider.getLocation();
                from.teleport(location);
                playEffect(location);

                val currentSecond = getCurrentSecond();
                lastTeleporationSecondTable.put(fromUniqueId, currentSecond);
                teleporting.remove(fromUniqueId);

                scehduler.cancelTask(status.task);
            }

            --status.leftSeconds;
        }, 0, SECOND_TICKS);
    }

    private static void playEffect(@NotNull Location location) {
        val world = location.getWorld();
        world.playSound(location, Sound.ENTITY_ENDERMAN_TELEPORT, 1, 1);
        world.spawnParticle(Particle.PORTAL, location, 40, 1, 1, 1);
    }

    private void notifyCountDown(@NotNull Player from, @NotNull LocationProvider locationProvider, int leftSeconds) {
        val left    = new Time(leftSeconds);
        val delay   = new Time(delaySeconds);
        var message = messageProvider.makeCountDownMessage(left, delay);
        from.sendMessage(message);

        if (locationProvider instanceof PlayerLocationProvider playerLocationProvider) {
            val to = playerLocationProvider.getPlayer();
            to.sendMessage(message);
        }
    }

    private void notifyTeleported(@NotNull Player from, @NotNull LocationProvider locationProvider) {
        val message = messageProvider.makeTeleportedMessage(from);
        from.sendMessage(message);

        if (locationProvider instanceof PlayerLocationProvider playerLocationProvider) {
            val to = playerLocationProvider.getPlayer();
            to.sendMessage(message);
        }
    }

    private static interface LocationProvider {
        @NotNull Location getLocation();
    }

    @Getter
    @AllArgsConstructor
    private static final class LocationWrapper implements LocationProvider {
        private final @NotNull Location location;
    }

    @Getter
    @AllArgsConstructor
    private static final class PlayerLocationProvider implements LocationProvider {
        private final @NotNull Player player;

        @Override
        public @NotNull Location getLocation() {
            return player.getLocation();
        }
    }

    public static final class Builder {
        private @Nullable MessageProvider messageProvider;
        private @Nullable World           defaultWorld;
        private @Nullable JavaPlugin      plugin;
        private           int             delaySeconds;
        private           int             opDelaySeconds;
        private           int             coolDownSeconds;
        private           int             opCoolDownSeconds;
        private           int             timeOutSeconds;

        public @NotNull DefaultTeleporationManager build() {
            if (messageProvider == null)
                throw new IllegalArgumentException("message provider isn't set");

            if (defaultWorld == null)
                throw new IllegalArgumentException("default world isn't set");

            if (plugin == null)
                throw new IllegalArgumentException("plugin isn't set");

            return new DefaultTeleporationManager(
                messageProvider, 
                defaultWorld, 
                plugin, 
                delaySeconds, 
                opDelaySeconds,
                coolDownSeconds, 
                opCoolDownSeconds,
                timeOutSeconds
            );
        }

        public @NotNull Builder messageProvider(@NotNull MessageProvider messageProvider) {
            this.messageProvider = messageProvider;
            return this;
        }

        public @NotNull Builder plugin(@NotNull JavaPlugin plugin) {
            this.plugin = plugin;
            return config(plugin.getConfig());
        }

        public @NotNull Builder config(@NotNull Configuration config) {
            val defaultWorldName = config.getString("default-world");
            val delaySeconds     = config.getInt("timing.delay");
            val coolDownSeconds  = config.getInt("timing.cool-down");
            val timeOutSeconds   = config.getInt("timing.time-out");

            return defaultWorld(defaultWorldName)
                  .delaySeconds(delaySeconds)
                  .coolDownSeconds(coolDownSeconds)
                  .timeOutSeconds(timeOutSeconds);
        }

        public @NotNull Builder defaultWorld(@NotNull UUID worldUniqueId) {
            val world = getWorld(worldUniqueId);
            return defaultWorld(world);
        }

        public @NotNull Builder defaultWorld(@NotNull String worldName) {
            val world = getWorld(worldName);
            return defaultWorld(world);
        }

        public @NotNull Builder defaultWorld(@NotNull World defaultWorld) {
            this.defaultWorld = defaultWorld;
            return this;
        }

        public @NotNull Builder delaySeconds(int delaySeconds) {
            if (delaySeconds < 0)
                throw new IllegalArgumentException("delay has to be positive");

            this.delaySeconds = delaySeconds;

            return this;
        }

        public @NotNull Builder opDelaySeconds(int opDelaySeconds) {
            if (opDelaySeconds < 0)
                throw new IllegalArgumentException("op delay has to be positive");

            this.opDelaySeconds = opDelaySeconds;

            return this;
        }

        public @NotNull Builder coolDownSeconds(int coolDownSeconds) {
            if (coolDownSeconds < 0)
                throw new IllegalArgumentException("cool down has to be positive");

            this.coolDownSeconds = coolDownSeconds;

            return this;
        }

        public @NotNull Builder opCoolDownSeconds(int opCoolDownSeconds) {
            if (opCoolDownSeconds < 0)
                throw new IllegalArgumentException("op cool down has to be positive");

            this.opCoolDownSeconds = opCoolDownSeconds;

            return this;
        }

        public @NotNull Builder timeOutSeconds(int timeOutSeconds) throws IllegalArgumentException {
            if (timeOutSeconds < 0)
                throw new IllegalArgumentException("time out has to be positive");

            this.timeOutSeconds = timeOutSeconds;

            return this;
        }
    }
}
