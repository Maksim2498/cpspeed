package space.moontalk.mc.cpspeed;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;

import org.jetbrains.annotations.NotNull;

import lombok.val;

public interface Utility {
    static final int SECOND_TICKS = 20;

    static int getCurrentSecond() {
        val tick   = Bukkit.getCurrentTick();
        val second = tick / 20;
        return second;
    }

    static @NotNull World getWorld(@NotNull UUID worldUniqueId) {
        val world = Bukkit.getWorld(worldUniqueId);

        if (world == null) {
            val message = String.format("world with uuid %s not found", worldUniqueId.toString());
            throw new IllegalArgumentException(message);
        }

        return world;
    }

    static @NotNull World getWorld(@NotNull String worldName) {
        val world = Bukkit.getWorld(worldName);

        if (world == null) {
            val message = String.format("world with name %s not found");
            throw new IllegalArgumentException(message);
        }

        return world;
    }

    static @NotNull Player getPlayer(@NotNull UUID playerUniqueId) {
        val player = Bukkit.getPlayer(playerUniqueId);

        if (player == null) {
            val message = String.format("player with uuid %s not found", playerUniqueId.toString());
            throw new IllegalArgumentException(message);
        }

        return player;
    }

    static @NotNull Player getPlayer(@NotNull String playerName) {
        val player = Bukkit.getPlayer(playerName);

        if (player == null) {
            val message = String.format("player with name %s not found", playerName);
            throw new IllegalArgumentException(message);
        }

        return player;
    }
}
