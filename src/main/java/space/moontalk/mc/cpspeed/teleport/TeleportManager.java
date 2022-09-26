package space.moontalk.mc.cpspeed.teleport;

import java.util.Set;
import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import org.jetbrains.annotations.NotNull;

import lombok.val;

import static space.moontalk.mc.cpspeed.Utility.*;
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
        val player = getPlayer(playerName);
        return isCoolDownOver(player);
    }

    default boolean isCoolDownOver(@NotNull UUID playerUniqueId) {
        val player = getPlayer(playerUniqueId);
        return isCoolDownOver(player);
    }

    default boolean isCoolDownOver(@NotNull Player player) {
        return getCoolDownSeconds(player) <= 0;
    }

    // - Get:

    default int getCoolDownSeconds(@NotNull String playerName) {
        val player = getPlayer(playerName);
        return getCoolDownSeconds(player);
    }

    default int getCoolDownSeconds(@NotNull UUID playerUniqueId) {
        val player = getPlayer(playerUniqueId);
        return getCoolDownSeconds(player);
    }

    int getCoolDownSeconds(@NotNull Player player);
    
    // Spawn:

    default void teleportToSpawn(@NotNull String playerName) throws Exception {
        val player = getPlayer(playerName);
        teleportToSpawn(player);
    }

    default void teleportToSpawn(@NotNull UUID playerUniqueId) throws Exception {
        val player = getPlayer(playerUniqueId);
        teleportToSpawn(player);
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
        val player = getPlayer(playerName);
        teleportToWorldSpawn(player);
    }

    default void teleportToWorldSpawn(@NotNull UUID playerUniqueId) throws Exception {
        val player = getPlayer(playerUniqueId);
        teleportToWorldSpawn(player);
    }

    default void teleportToWorldSpawn(@NotNull Player player) throws Exception {
        val world = getDefaultWorld();
        teleportToWorldSpawn(player, world);
    }

    // - Specific:

    default void teleportToWorldSpawn(@NotNull String playerName, @NotNull String worldName) throws Exception {
        val world = getWorld(worldName);
        teleportToWorldSpawn(playerName, world);
    }

    default void teleportToWorldSpawn(@NotNull String playerName, @NotNull UUID worldUniqueId) throws Exception {
        val world = getWorld(worldUniqueId);
        teleportToWorldSpawn(playerName, world);
    }

    default void teleportToWorldSpawn(@NotNull String playerName, @NotNull World world) throws Exception {
        val player = getPlayer(playerName);
        teleportToWorldSpawn(player, world);
    }

    default void teleportToWorldSpawn(@NotNull UUID playerUniqueId, @NotNull String worldName) throws Exception {
        val world = getWorld(worldName);
        teleportToWorldSpawn(playerUniqueId, world);
    }

    default void teleportToWorldSpawn(@NotNull UUID playerUniqueId, @NotNull UUID worldUniqueId) throws Exception {
        val world = getWorld(worldUniqueId);
        teleportToWorldSpawn(playerUniqueId, world);
    }

    default void teleportToWorldSpawn(@NotNull UUID playerUniqueId, @NotNull World world) throws Exception {
        val player = getPlayer(playerUniqueId);
        teleportToWorldSpawn(player, world);
    }

    default void teleportToWorldSpawn(@NotNull Player player, @NotNull String worldName) throws Exception {
        val world = getWorld(worldName);
        teleportToWorldSpawn(player, world);
    }

    default void teleportToWorldSpawn(@NotNull Player player, @NotNull UUID worldUniqueId) throws Exception {
        val world = getWorld(worldUniqueId);
        teleportToWorldSpawn(player, world);
    }

    default void teleportToWorldSpawn(@NotNull Player player, @NotNull World world) throws Exception {
        val spawn = world.getSpawnLocation();
        teleport(player, spawn, "world spawn");
    }

    @NotNull World getDefaultWorld();

    // Teleport:

    // - Can:

    default boolean canTeleport(@NotNull String playerName) {
        val player = getPlayer(playerName);
        return canTeleport(player);
    }

    default boolean canTeleport(@NotNull UUID playerUniqueId) {
        val player = getPlayer(playerUniqueId);
        return canTeleport(player);
    }

    default boolean canTeleport(@NotNull Player player) {
        return isCoolDownOver(player) && !isTeleporting(player);
    }

    // - Is:

    default boolean isTeleporting(@NotNull String playerName) {
        val player = getPlayer(playerName);
        return isTeleporting(player);
    }

    default boolean isTeleporting(@NotNull UUID playerUniqueId) {
        val player = getPlayer(playerUniqueId);
        return isTeleporting(player);
    }

    boolean isTeleporting(@NotNull Player player);

    // - Unnamed:

    default void teleport(@NotNull String playerName, @NotNull Location location) throws Exception {
        val player = getPlayer(playerName);
        teleport(player, location);
    }

    default void teleport(@NotNull UUID playerUniqueId, @NotNull Location location) throws Exception {
        val player = getPlayer(playerUniqueId);
        teleport(player, location);
    }

    void teleport(@NotNull Player player, @NotNull Location location) throws Exception;

    // - Named:

    default void teleport(
        @NotNull String   playerName,
        @NotNull Location location,
        @NotNull String   locationName
    ) throws Exception {
        val player = getPlayer(playerName);
        teleport(player, location);
    }

    default void teleport(
        @NotNull UUID     playerUniqueId,
        @NotNull Location location,
        @NotNull String   locationName
    ) throws Exception {
        val player = getPlayer(playerUniqueId);
        teleport(player, location, locationName);
    }

    void teleport(@NotNull Player player, @NotNull Location location, @NotNull String locationName) throws Exception;

    // Request:

    default void sendRequest(@NotNull String fromName, @NotNull String toName) throws Exception {
        val from = getPlayer(fromName);
        val to   = getPlayer(toName);
        sendRequest(from, to);
    }

    default void sendRequest(@NotNull String fromName, @NotNull UUID toUniqueId) throws Exception {
        val from = getPlayer(fromName);
        val to   = getPlayer(toUniqueId);
        sendRequest(from, to);
    }

    default void sendRequest(@NotNull String fromName, @NotNull Player to) throws Exception {
        val from = getPlayer(fromName);
        sendRequest(from, to);
    }

    default void sendRequest(@NotNull UUID fromUniqueId, @NotNull String toName) throws Exception {
        val from = getPlayer(fromUniqueId);
        val to   = getPlayer(toName);
        sendRequest(from, to);
    }

    default void sendRequest(@NotNull UUID fromUniqueId, @NotNull UUID toUniqueId) throws Exception {
        val from = getPlayer(fromUniqueId);
        val to   = getPlayer(toUniqueId);
        sendRequest(from, to);
    }

    default void sendRequest(@NotNull UUID fromUniqueId, @NotNull Player to) throws Exception {
        val from = getPlayer(fromUniqueId);
        sendRequest(from, to);
    }

    default void sendRequest(@NotNull Player from, @NotNull String toName) throws Exception {
        val to = getPlayer(toName);
        sendRequest(from, to);
    }

    default void sendRequest(@NotNull Player from, @NotNull UUID toUniqueId) throws Exception {
        val to = getPlayer(toUniqueId);
        sendRequest(from, to);
    }

    void sendRequest(@NotNull Player from, @NotNull Player to) throws Exception;

    // Accept:

    // - Only:

    default void acceptRequest(@NotNull String toName) throws Exception {
        val to = getPlayer(toName);
        acceptRequest(to);
    }
    
    default void acceptRequest(@NotNull UUID toUniqueId) throws Exception {
        val to = getPlayer(toUniqueId);
        acceptRequest(to);
    }
    

    default void acceptRequest(@NotNull Player to) throws Exception {
        val from = getTheOnlyRequest(to);
        acceptRequest(from, to);
    }

    // - Specific:

    default void acceptRequest(@NotNull String fromName, @NotNull String toName) throws Exception {
        val from = getPlayer(fromName);
        val to   = getPlayer(toName);
        acceptRequest(from, to);
    }

    default void acceptRequest(@NotNull String fromName, @NotNull UUID toUniqueId) throws Exception {
        val from = getPlayer(fromName);
        val to   = getPlayer(toUniqueId);
        acceptRequest(from, to);
    }

    default void acceptRequest(@NotNull String fromName, @NotNull Player to) throws Exception {
        val from = getPlayer(fromName);
        acceptRequest(from, to);
    }

    default void acceptRequest(@NotNull UUID fromUniqueId, @NotNull String toName) throws Exception {
        val from = getPlayer(fromUniqueId);
        val to   = getPlayer(toName);
        acceptRequest(from, to);
    }

    default void acceptRequest(@NotNull UUID fromUniqueId, @NotNull UUID toUniqueId) throws Exception {
        val from = getPlayer(fromUniqueId);
        val to   = getPlayer(toUniqueId);
        acceptRequest(from, to);
    }

    default void acceptRequest(@NotNull UUID fromUniqueId, @NotNull Player to) throws Exception {
        val from = getPlayer(fromUniqueId);
        acceptRequest(from, to);
    }

    default void acceptRequest(@NotNull Player from, @NotNull String toName) throws Exception {
        val to = getPlayer(toName);
        acceptRequest(from, to);
    }

    default void acceptRequest(@NotNull Player from, @NotNull UUID toUniqueId) throws Exception {
        val to = getPlayer(toUniqueId);
        acceptRequest(from, to);
    }

    void acceptRequest(@NotNull Player from, @NotNull Player to) throws Exception;
    
    // Deny:

    // - Only:

    default void denyRequest(@NotNull String toName) throws Exception {
        val to = getPlayer(toName);
        denyRequest(to);
    }
    
    default void denyRequest(@NotNull UUID toUniqueId) throws Exception {
        val to = getPlayer(toUniqueId);
        denyRequest(to);
    }

    default void denyRequest(@NotNull Player to) throws Exception {
        val from = getTheOnlyRequest(to);
        denyRequest(from, to);
    }

    // - Specific:

    default void denyRequest(@NotNull String fromName, @NotNull String toName) throws Exception {
        val from = getPlayer(fromName);
        val to   = getPlayer(toName);
        denyRequest(from, to);
    }

    default void denyRequest(@NotNull String fromName, @NotNull UUID toUniqueId) throws Exception {
        val from = getPlayer(fromName);
        val to   = getPlayer(toUniqueId);
        denyRequest(from, to);
    }

    default void denyRequest(@NotNull String fromName, @NotNull Player to) throws Exception {
        val from = getPlayer(fromName);
        denyRequest(from, to);
    }

    default void denyRequest(@NotNull UUID fromUniqueId, @NotNull String toName) throws Exception {
        val from = getPlayer(fromUniqueId);
        val to   = getPlayer(toName);
        denyRequest(from, to);
    }

    default void denyRequest(@NotNull UUID fromUniqueId, @NotNull UUID toUniqueId) throws Exception {
        val from = getPlayer(fromUniqueId);
        val to   = getPlayer(toUniqueId);
        denyRequest(from, to);
    }

    default void denyRequest(@NotNull UUID fromUniqueId, @NotNull Player to) throws Exception {
        val from = getPlayer(fromUniqueId);
        denyRequest(from, to);
    }

    default void denyRequest(@NotNull Player from, @NotNull String toName) throws Exception {
        val to = getPlayer(toName);
        denyRequest(from, to);
    }

    default void denyRequest(@NotNull Player from, @NotNull UUID toUniqueId) throws Exception {
        val to = getPlayer(toUniqueId);
        denyRequest(from, to);
    }

    void denyRequest(@NotNull Player from, @NotNull Player to) throws Exception;
    
    // List:
    
    // - Got:

    default @NotNull Set<Player> getGotRequests(@NotNull String playerName) {
        val player = getPlayer(playerName);
        return getGotRequests(player);
    }

    default @NotNull Set<Player> getGotRequests(@NotNull UUID toUniqueId) {
        val to = getPlayer(toUniqueId);
        return getGotRequests(to);
    }

    @NotNull Set<Player> getGotRequests(@NotNull Player to);

    // - Sent:

    default @NotNull Set<Player> getSentRequests(@NotNull String fromName) {
        val from = getPlayer(fromName);
        return getSentRequests(from);
    }

    default @NotNull Set<Player> getSentRequests(@NotNull UUID fromUniqueId) {
        val from = getPlayer(fromUniqueId);
        return getSentRequests(from);
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
