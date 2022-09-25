package space.moontalk.mc.cpspeed.command;

import org.jetbrains.annotations.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;

import space.moontalk.mc.commands.route.RouteCall;
import space.moontalk.mc.commands.route.RouteHandler;

import space.moontalk.mc.cpspeed.teleport.TeleportManager;

// list (got | in) %p?

@Getter
@AllArgsConstructor
public class TpaListGotHandler implements RouteHandler {
    private final @NotNull TeleportManager teleportManager;

    @Override
    public void onRoute(@NotNull RouteCall call) throws Exception {

    }

    @Override
    public @NotNull String getPermission() {
        return "cpspeed.tpa.list.got";
    }
}
