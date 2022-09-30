package space.moontalk.mc.cpspeed.teleport;

import java.util.Set;
import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import org.jetbrains.annotations.NotNull;

import lombok.val;

import static space.moontalk.mc.more.bukkit.MoreBukkit.*;

import space.moontalk.mc.cpspeed.message.MessageProvider;
import space.moontalk.mc.cpspeed.message.MessageProviderHolder;

public interface TeleportManager extends MessageProviderHolder {
    int getDelaySeconds();
    int getOpDelaySeconds();
    int getCoolDownSeconds();
    int getOpCoolDownSeconds();
    int getTimeOutSeconds();
    @NotNull MessageProvider getMessageProvider();

    // Cool Down:

    // - Is Over:

    default boolean isCoolDownOver(@NotNull String playerName) {
        return isCoolDownOver(getPlayer(playerName));
    }

    default boolean isCoolDownOver(@NotNull UUID playerUniqueId) {
        return isCoolDownOver(getPlayer(playerUniqueId));
    }

    default boolean isCoolDownOver(@NotNull Player player) {
        return getCoolDownSeconds(player) <= 0;
    }

    // - Get:

    default int getCoolDownSeconds(@NotNull String playerName) {
        return getCoolDownSeconds(getPlayer(playerName));
    }

    default int getCoolDownSeconds(@NotNull UUID playerUniqueId) {
        return getCoolDownSeconds(getPlayer(playerUniqueId));
    }

    int getCoolDownSeconds(@NotNull Player player);
    
    // Spawn:

    default void teleportToSpawn(@NotNull String playerName) throws Exception {
        teleportToSpawn(getPlayer(playerName));
    }

    default void teleportToSpawn(@NotNull UUID playerUniqueId) throws Exception {
        teleportToSpawn(getPlayer(playerUniqueId));
    }

    default void teleportToSpawn(@NotNull Player player) throws Exception {
        val spawn = player.getBedSpawnLocation();

        if (spawn == null) 
            throwMissingBed(player);

        teleport(player, spawn, "spawn");
    }

    // World Spawn:

    // - Default:

    default void teleportToWorldSpawn(@NotNull String playerName) throws Exception {
        teleportToWorldSpawn(getPlayer(playerName));
    }

    default void teleportToWorldSpawn(@NotNull UUID playerUniqueId) throws Exception {
        teleportToWorldSpawn(getPlayer(playerUniqueId));
    }

    default void teleportToWorldSpawn(@NotNull Player player) throws Exception {
        val world = getDefaultWorld();
        teleportToWorldSpawn(player, world);
    }

    // - Specific:

    default void teleportToWorldSpawn(@NotNull String playerName, @NotNull String worldName) throws Exception {
        teleportToWorldSpawn(playerName, getWorld(worldName));
    }

    default void teleportToWorldSpawn(@NotNull String playerName, @NotNull UUID worldUniqueId) throws Exception {
        teleportToWorldSpawn(playerName, getWorld(worldUniqueId));
    }

    default void teleportToWorldSpawn(@NotNull String playerName, @NotNull World world) throws Exception {
        teleportToWorldSpawn(getPlayer(playerName), world);
    }

    default void teleportToWorldSpawn(@NotNull UUID playerUniqueId, @NotNull String worldName) throws Exception {
        teleportToWorldSpawn(playerUniqueId, getWorld(worldName));
    }

    default void teleportToWorldSpawn(@NotNull UUID playerUniqueId, @NotNull UUID worldUniqueId) throws Exception {
        teleportToWorldSpawn(playerUniqueId, getWorld(worldUniqueId));
    }

    default void teleportToWorldSpawn(@NotNull UUID playerUniqueId, @NotNull World world) throws Exception {
        teleportToWorldSpawn(getPlayer(playerUniqueId), world);
    }

    default void teleportToWorldSpawn(@NotNull Player player, @NotNull String worldName) throws Exception {
        teleportToWorldSpawn(player, getWorld(worldName));
    }

    default void teleportToWorldSpawn(@NotNull Player player, @NotNull UUID worldUniqueId) throws Exception {
        teleportToWorldSpawn(player, getWorld(worldUniqueId));
    }

    default void teleportToWorldSpawn(@NotNull Player player, @NotNull World world) throws Exception {
        teleport(player, world.getSpawnLocation(), "world spawn");
    }

    @NotNull World getDefaultWorld();

    // Teleport:

    // - Can:

    default boolean canTeleport(@NotNull String playerName) {
        return canTeleport(getPlayer(playerName));
    }

    default boolean canTeleport(@NotNull UUID playerUniqueId) {
        return canTeleport(getPlayer(playerUniqueId));
    }

    default boolean canTeleport(@NotNull Player player) {
        return isCoolDownOver(player) && !isTeleporting(player);
    }

    // - Is:

    default boolean isTeleporting(@NotNull String playerName) {
        return isTeleporting(getPlayer(playerName));
    }

    default boolean isTeleporting(@NotNull UUID playerUniqueId) {
        return isTeleporting(getPlayer(playerUniqueId));
    }

    boolean isTeleporting(@NotNull Player player);

    // - Unnamed:

    default void teleport(@NotNull String playerName, @NotNull Location location) throws Exception {
        teleport(getPlayer(playerName), location);
    }

    default void teleport(@NotNull UUID playerUniqueId, @NotNull Location location) throws Exception {
        teleport(getPlayer(playerUniqueId), location);
    }

    void teleport(@NotNull Player player, @NotNull Location location) throws Exception;

    // - Named:

    default void teleport(
        @NotNull String   playerName,
        @NotNull Location location,
        @NotNull String   locationName
    ) throws Exception {
        teleport(getPlayer(playerName), location);
    }

    default void teleport(
        @NotNull UUID     playerUniqueId,
        @NotNull Location location,
        @NotNull String   locationName
    ) throws Exception {
        teleport(getPlayer(playerUniqueId), location, locationName);
    }

    void teleport(@NotNull Player player, @NotNull Location location, @NotNull String locationName) throws Exception;

    // Request:

    default void sendRequest(@NotNull String fromName, @NotNull String toName) throws Exception {
        sendRequest(getPlayer(fromName), getPlayer(toName));
    }

    default void sendRequest(@NotNull String fromName, @NotNull UUID toUniqueId) throws Exception {
        sendRequest(getPlayer(fromName), getPlayer(toUniqueId));
    }

    default void sendRequest(@NotNull String fromName, @NotNull Player to) throws Exception {
        sendRequest(getPlayer(fromName), to);
    }

    default void sendRequest(@NotNull UUID fromUniqueId, @NotNull String toName) throws Exception {
        sendRequest(getPlayer(fromUniqueId), getPlayer(toName));
    }

    default void sendRequest(@NotNull UUID fromUniqueId, @NotNull UUID toUniqueId) throws Exception {
        sendRequest(getPlayer(fromUniqueId), getPlayer(toUniqueId));
    }

    default void sendRequest(@NotNull UUID fromUniqueId, @NotNull Player to) throws Exception {
        sendRequest(getPlayer(fromUniqueId), to);
    }

    default void sendRequest(@NotNull Player from, @NotNull String toName) throws Exception {
        sendRequest(from, getPlayer(toName));
    }

    default void sendRequest(@NotNull Player from, @NotNull UUID toUniqueId) throws Exception {
        sendRequest(from, getPlayer(toUniqueId));
    }

    void sendRequest(@NotNull Player from, @NotNull Player to) throws Exception;

    // Accept:

    // - Only:

    default void acceptRequest(@NotNull String toName) throws Exception {
        acceptRequest(getPlayer(toName));
    }
    
    default void acceptRequest(@NotNull UUID toUniqueId) throws Exception {
        acceptRequest(getPlayer(toUniqueId));
    }
    

    default void acceptRequest(@NotNull Player to) throws Exception {
        acceptRequest(getTheOnlyRequest(to), to);
    }

    // - Specific:

    default void acceptRequest(@NotNull String fromName, @NotNull String toName) throws Exception {
        acceptRequest(getPlayer(fromName), getPlayer(toName));
    }

    default void acceptRequest(@NotNull String fromName, @NotNull UUID toUniqueId) throws Exception {
        acceptRequest(getPlayer(fromName), getPlayer(toUniqueId));
    }

    default void acceptRequest(@NotNull String fromName, @NotNull Player to) throws Exception {
        acceptRequest(getPlayer(fromName), to);
    }

    default void acceptRequest(@NotNull UUID fromUniqueId, @NotNull String toName) throws Exception {
        acceptRequest(getPlayer(fromUniqueId), getPlayer(toName));
    }

    default void acceptRequest(@NotNull UUID fromUniqueId, @NotNull UUID toUniqueId) throws Exception {
        acceptRequest(getPlayer(fromUniqueId), getPlayer(toUniqueId));
    }

    default void acceptRequest(@NotNull UUID fromUniqueId, @NotNull Player to) throws Exception {
        acceptRequest(getPlayer(fromUniqueId), to);
    }

    default void acceptRequest(@NotNull Player from, @NotNull String toName) throws Exception {
        acceptRequest(from, getPlayer(toName));
    }

    default void acceptRequest(@NotNull Player from, @NotNull UUID toUniqueId) throws Exception {
        acceptRequest(from, getPlayer(toUniqueId));
    }

    void acceptRequest(@NotNull Player from, @NotNull Player to) throws Exception;
    
    // Deny:

    // - Only:

    default void denyRequest(@NotNull String toName) throws Exception {
        denyRequest(getPlayer(toName));
    }
    
    default void denyRequest(@NotNull UUID toUniqueId) throws Exception {
        denyRequest(getPlayer(toUniqueId));
    }

    default void denyRequest(@NotNull Player to) throws Exception {
        denyRequest(getTheOnlyRequest(to), to);
    }

    // - Specific:

    default void denyRequest(@NotNull String fromName, @NotNull String toName) throws Exception {
        denyRequest(getPlayer(fromName), getPlayer(toName));
    }

    default void denyRequest(@NotNull String fromName, @NotNull UUID toUniqueId) throws Exception {
        denyRequest(getPlayer(fromName), getPlayer(toUniqueId));
    }

    default void denyRequest(@NotNull String fromName, @NotNull Player to) throws Exception {
        denyRequest(getPlayer(fromName), to);
    }

    default void denyRequest(@NotNull UUID fromUniqueId, @NotNull String toName) throws Exception {
        denyRequest(getPlayer(fromUniqueId), getPlayer(toName));
    }

    default void denyRequest(@NotNull UUID fromUniqueId, @NotNull UUID toUniqueId) throws Exception {
        denyRequest(getPlayer(fromUniqueId), getPlayer(toUniqueId));
    }

    default void denyRequest(@NotNull UUID fromUniqueId, @NotNull Player to) throws Exception {
        denyRequest(getPlayer(fromUniqueId), to);
    }

    default void denyRequest(@NotNull Player from, @NotNull String toName) throws Exception {
        denyRequest(from, getPlayer(toName));
    }

    default void denyRequest(@NotNull Player from, @NotNull UUID toUniqueId) throws Exception {
        denyRequest(from, getPlayer(toUniqueId));
    }

    void denyRequest(@NotNull Player from, @NotNull Player to) throws Exception;
    
    // List:
    
    // - Got:

    default @NotNull Set<Player> getGotRequests(@NotNull String playerName) {
        return getGotRequests(getPlayer(playerName));
    }

    default @NotNull Set<Player> getGotRequests(@NotNull UUID toUniqueId) {
        return getGotRequests(getPlayer(toUniqueId));
    }

    @NotNull Set<Player> getGotRequests(@NotNull Player to);

    // - Sent:

    default @NotNull Set<Player> getSentRequests(@NotNull String fromName) {
        return getSentRequests(getPlayer(fromName));
    }

    default @NotNull Set<Player> getSentRequests(@NotNull UUID fromUniqueId) {
        return getSentRequests(getPlayer(fromUniqueId));
    }

    @NotNull Set<Player> getSentRequests(@NotNull Player from);

    // Utility:
    
    private @NotNull Player getTheOnlyRequest(@NotNull Player to) throws Exception {
        val requests = getGotRequests(to);

        if (requests.isEmpty()) 
            throwYouHaveNoRequests(to);

        if (requests.size() > 1) 
            throwSpecifyRequest(to);

        return requests.stream()
                       .findFirst()
                       .get();
    }
    
    private void throwYouHaveNoRequests(@NotNull Player to) throws Exception {
        val messageProvider = getMessageProvider();
        val message         = messageProvider.makeYouHaveNoRequestsMessage(to);
        throw new Exception(message);
    }

    private void throwSpecifyRequest(@NotNull Player to) throws Exception {
        val requests        = getGotRequests(to);
        val messageProvider = getMessageProvider();
        val message         = messageProvider.makeSpecifyRequestMessage(to, requests);
        throw new Exception(message);
    }

    private void throwMissingBed(@NotNull Player player) throws Exception {
        val messageProvider = getMessageProvider();
        val message         = messageProvider.makeMissingBedMessage(player);
        throw new Exception(message);
    }
}
