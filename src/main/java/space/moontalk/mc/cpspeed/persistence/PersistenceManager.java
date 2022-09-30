package space.moontalk.mc.cpspeed.persistence;

import java.io.IOException;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.val;

import static space.moontalk.mc.more.bukkit.MoreBukkit.*;

import space.moontalk.more.collection.AbstractLiveSubSet;

public interface PersistenceManager {
    // I/O:
    
    void load() throws IOException, ClassNotFoundException;
    void save() throws IOException;



    // Get:
    
    // - Player:

    // -- Not Null:

    default @NotNull Location getPlayerWarp(@NotNull String playerName, @NotNull String warpName) {
        val warp = getPlayerWarpOrNull(playerName, warpName);

        if (warp == null) {
            val message = String.format(
                "Warp with name %s not found for player with name %s",
                warpName, 
                playerName
            );

            throw new IllegalArgumentException(message);
        }

        return warp;
    }

    default @NotNull Location getPlayerWarp(@NotNull Player player, @NotNull String warpName) {
        val warp = getPlayerWarpOrNull(player, warpName);

        if (warp == null) {
            val message = String.format(
                "Warp with name %s not found for player with name %s",
                warpName, 
                player.getName()
            );

            throw new IllegalArgumentException(message);
        }

        return warp;
    }

    default @NotNull Location getPlayerWarp(@NotNull OfflinePlayer player, @NotNull String warpName) {
        val warp = getPlayerWarpOrNull(player, warpName);

        if (warp == null) {
            val message = String.format(
                "Warp with name %s not found for offline player with name %s",
                warpName, 
                player.getName()
            );

            throw new IllegalArgumentException(message);
        }

        return warp;
    }

    default @NotNull Location getPlayerWarp(@NotNull UUID playerUniqueId, @NotNull String warpName) {
        val warp = getPlayerWarpOrNull(playerUniqueId, warpName);

        if (warp == null) {
            val message = String.format(
                "Warp with name %s not found for player with UUID %s",
                warpName, 
                playerUniqueId.toString()
            );

            throw new IllegalArgumentException(message);
        }

        return warp;
    }

    // -- Nullable:

    default @Nullable Location getPlayerWarpOrNull(@NotNull String playerName, @NotNull String warpName) {
        return getPlayerWarpOrNull(getPlayerUniqueId(playerName), warpName);
    }

    default @Nullable Location getPlayerWarpOrNull(@NotNull Player player, @NotNull String warpName) {
        return getPlayerWarpOrNull(player.getUniqueId(), warpName);
    }

    default @Nullable Location getPlayerWarpOrNull(@NotNull OfflinePlayer player, @NotNull String warpName) {
        return getPlayerWarpOrNull(player.getUniqueId(), warpName);
    }

    default @Nullable Location getPlayerWarpOrNull(@NotNull UUID playerUniqueId, @NotNull String warpName) {
        return null;
    }

    // - Global:

    // -- Not Null:

    default @NotNull Location getGlobalWarp(@NotNull String warpName) {
        val warp = getGlobalWarpOrNull(warpName);

        if (warp == null) {
            val message = String.format("Global warp with name %s not found", warpName);
            throw new IllegalArgumentException(message);
        }

        return warp;
    }

    // -- Nullable:

    default @Nullable Location getGlobalWarpOrNull(@NotNull String warpName) {
        val entries = getGlobalEntries();

        for (val entry : entries) 
            if (warpName.equalsIgnoreCase(entry.getName()))
                    return entry.getLocation();
        
        return null;
    }


    // Set:

    // - Player:

    default void setPlayerWarp(@NotNull String playerName, @NotNull String warpName, @NotNull Location location) {
        setPlayerWarp(getPlayerUniqueId(playerName), warpName, location);
    }

    default void setPlayerWarp(@NotNull Player player, @NotNull String warpName, @NotNull Location location) {
        setPlayerWarp(player.getUniqueId(), warpName, location);
    }

    default void setPlayerWarp(@NotNull OfflinePlayer player, @NotNull String warpName, @NotNull Location location) {
        setPlayerWarp(player.getUniqueId(), warpName, location);
    }

    default void setPlayerWarp(@NotNull UUID playerUniqueId, @NotNull String warpName, @NotNull Location location) {
        val entries = getEntries();
        val entry   = new PlayerEntry(playerUniqueId, warpName, location);
        entries.add(entry);
    }

    // - Global:

    default void setGlobalWarp(@NotNull String warpName, @NotNull Location location) {
        val entries = getEntries();
        val entry   = new GlobalEntry(warpName, location);
        entries.add(entry);
    }



    // Remove:



    // Rename:



    // Contains:

    // - Player:

    default boolean hasPlayerWarp(@NotNull String playerName, @NotNull String warpName) {
        return hasPlayerWarp(getPlayerUniqueId(playerName), warpName);
    }

    default boolean hasPlayerWarp(@NotNull Player player, @NotNull String warpName) {
        return hasPlayerWarp(player.getUniqueId(), warpName);
    }

    default boolean hasPlayerWarp(@NotNull OfflinePlayer player, @NotNull String warpName) {
        return hasPlayerWarp(player.getUniqueId(), warpName);
    }

    default boolean hasPlayerWarp(@NotNull UUID playerUniqueId, @NotNull String warpName) {
        val entries = getPlayerEntries();
        return entries.stream().anyMatch(e -> playerUniqueId.equals(e.getPlayerUniqueId())
                                           && warpName.equalsIgnoreCase(e.getName()));
    }

    // - Global:

    default boolean hasGlobalWarp(@NotNull String warpName) {
        val entries = getGlobalEntries();
        return entries.stream().anyMatch(e -> warpName.equalsIgnoreCase(e.getName()));
    }



    // UUIDs:

    default @NotNull Set<UUID> collectPlayerUniqueIds() {
        val entries = getPlayerEntries();
        return entries.stream().map(e -> e.getPlayerUniqueId())
                               .collect(Collectors.toSet());
    }

    // Entry:

    // - Player:

    // -- Specific:

    default @NotNull Set<PlayerEntry> getPlayerEntries(@NotNull String playerName) {
        return getPlayerEntries(getPlayerUniqueId(playerName));
    }

    default @NotNull Set<PlayerEntry> getPlayerEntries(@NotNull Player player) {
        return getPlayerEntries(player.getUniqueId());
    }

    default @NotNull Set<PlayerEntry> getPlayerEntries(@NotNull OfflinePlayer player) {
        return getPlayerEntries(player.getUniqueId());
    }

    default @NotNull Set<PlayerEntry> getPlayerEntries(@NotNull UUID playerUniqueId) {
        return new AbstractLiveSubSet<>(getEntries()) {
            @Override
            public boolean belongs(@Nullable Object object) {
                return object instanceof PlayerEntry playerEntry
                    && playerUniqueId.equals(playerEntry.getPlayerUniqueId());
            }
        };
    }

    // -- All:

    default @NotNull Set<PlayerEntry> getPlayerEntries() {
        return new AbstractLiveSubSet<>(getEntries()) {
            @Override
            public boolean belongs(@Nullable Object object) {
                return object instanceof PlayerEntry;
            }
        };
    }

    // - Global:

    default @NotNull Set<GlobalEntry> getGlobalEntries() {
        return new AbstractLiveSubSet<>(getEntries()) {
            @Override
            public boolean belongs(@Nullable Object object) {
                return object instanceof GlobalEntry;
            }
        };
    }

    // - All:

    @NotNull Set<Entry> getEntries();



    // Classes:

    @Getter
    @AllArgsConstructor
    public sealed abstract static class Entry implements Serializable 
                                              permits    PlayerEntry,
                                                         GlobalEntry {
        protected final @NotNull String   name;
        protected final @NotNull Location location;

        @Override
        public int hashCode() {
            return Objects.hash(name);
        }

        @Override
        public boolean equals(@Nullable Object object) {
            if (this == object)
                return true;

            val thisClass   = getClass();
            val objectClass = object.getClass();

            if (!thisClass.equals(objectClass))
                return false;

            val other = (Entry) object;

            return name.equalsIgnoreCase(other.name);
        }
    }

    public static final class GlobalEntry extends Entry {
        public GlobalEntry(@NotNull String name, @NotNull Location location) {
            super(name, location);
        }
    }

    @Getter
    public final static class PlayerEntry extends Entry {
        private final @NotNull UUID playerUniqueId;

        public PlayerEntry(@NotNull UUID playerUniqueId, @NotNull String name, @NotNull Location location) {
            super(name, location);
            this.playerUniqueId = playerUniqueId;
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, playerUniqueId);
        }

        @Override
        public boolean equals(@Nullable Object object) {
            val superRes = super.equals(object);

            if (!superRes)
                return false;

            val other = (PlayerEntry) object;

            return playerUniqueId.equals(other.playerUniqueId);
        }
    }
}
