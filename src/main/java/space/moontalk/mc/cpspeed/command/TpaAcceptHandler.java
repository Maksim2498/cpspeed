package space.moontalk.mc.cpspeed.command;

import java.util.Set;

import org.bukkit.entity.Player;

import org.jetbrains.annotations.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;

import space.moontalk.mc.commands.route.RouteCall;
import space.moontalk.mc.commands.route.RouteHandler;

import space.moontalk.mc.cpspeed.teleport.TeleportManager;
import space.moontalk.mc.cpspeed.teleport.TeleportManagerHolder;

// tpa accept %p?

@Getter
@AllArgsConstructor
public class TpaAcceptHandler implements RouteHandler,
                                         TeleportManagerHolder {
    private final @NotNull TeleportManager teleportManager;

    @Override
    public void onRoute(@NotNull RouteCall call) throws Exception {
        final Player from = call.getPlaceholdedAtOrNull(0); 
        final Player to   = call.getCommandSender();

        if (from == null)
            teleportManager.acceptRequest(to);
        else
            teleportManager.acceptRequest(from, to);
    }

    @Override
    public @NotNull Set<Class<?>> getClasses() {
        return Set.of(Player.class);
    }

    @Override
    public @NotNull String getPermission() {
        return "cpspeed.tpa.accept";
    }
}
