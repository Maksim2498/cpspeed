
package space.moontalk.mc.cpspeed.command;

import java.util.List;

import org.bukkit.World;
import org.bukkit.entity.Player;

import org.jetbrains.annotations.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;

import space.moontalk.mc.commands.route.RouteCall;
import space.moontalk.mc.commands.route.RouteHandler;

import space.moontalk.mc.cpspeed.teleport.TeleportManager;

// %w?

@Getter
@AllArgsConstructor
public class WorldSpawnHandler implements RouteHandler {
    private final @NotNull TeleportManager teleportManager;

    @Override
    public void onRoute(@NotNull RouteCall call) throws Exception {
        final World  world  = call.getPlaceholdedAtOrNull(0);
        final Player player = call.getCommandSender();

        if (null == world)
            teleportManager.teleportToWorldSpawn(player);
        else
            teleportManager.teleportToWorldSpawn(player, world);
    }

    @Override
    public @NotNull List<Class<?>> getClasses() {
        return List.of(Player.class);
    }
}
