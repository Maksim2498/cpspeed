package space.moontalk.mc.cpspeed.command;

import java.util.List;

import org.bukkit.entity.Player;

import org.jetbrains.annotations.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;

import space.moontalk.mc.commands.route.RouteCall;
import space.moontalk.mc.commands.route.RouteHandler;

import space.moontalk.mc.cpspeed.teleport.TeleportManager;
import space.moontalk.mc.cpspeed.teleport.TeleportManagerHolder;

@Getter
@AllArgsConstructor
public class SpawnHandler implements RouteHandler, TeleportManagerHolder {
    private final @NotNull TeleportManager teleportManager;
    
    @Override
    public void onRoute(@NotNull RouteCall call) throws Exception {
        final Player player = call.getCommandSender(); 
        teleportManager.teleportToSpawn(player);
    }

    @Override
    public @NotNull List<Class<?>> getClasses() {
        return List.of(Player.class);
    }
}
