package space.moontalk.mc.cpspeed.command;

import java.util.Set;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import org.jetbrains.annotations.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.val;

import space.moontalk.mc.commands.MissingPermissionException;
import space.moontalk.mc.commands.route.RouteCall;
import space.moontalk.mc.commands.route.RouteHandler;

import space.moontalk.mc.cpspeed.teleport.TeleportManager;
import space.moontalk.mc.cpspeed.teleport.TeleportManagerHolder;

// spawn %a?

@Getter
@AllArgsConstructor
public class SpawnHandler implements RouteHandler, 
                                     TeleportManagerHolder {
    private final @NotNull TeleportManager teleportManager;
    
    @Override
    public void onRoute(@NotNull RouteCall call) throws Exception {
        final Player        player       = call.getCommandSender(); 
        final OfflinePlayer targetPlayer = getTargetPlayer(call);

        if (targetPlayer == null || player == targetPlayer) {
            val permission = "cpspeed.spawn.self";

            if (!player.hasPermission(permission))
                throw new MissingPermissionException(permission);

            teleportManager.teleportToSpawn(player);
            return;
        }

        val permission = "cpspeed.spawn.other";

        if (!player.hasPermission(permission))
            throw new MissingPermissionException(permission);

        teleportManager.teleportToPlayerSpawn(player, targetPlayer);
    }

    private @NotNull OfflinePlayer getTargetPlayer(@NotNull RouteCall call) {
        final OfflinePlayer specifiedPlayer = call.getPlaceholdedAtOrNull(0);
        
        if (specifiedPlayer != null)
            return specifiedPlayer;

        return call.getCommandSender();
    }

    @Override
    public @NotNull Set<Class<?>> getClasses() {
        return Set.of(Player.class);
    }

    @Override 
    public @NotNull String getPermission() {
        return "cpspeed.spawn";
    }
}
