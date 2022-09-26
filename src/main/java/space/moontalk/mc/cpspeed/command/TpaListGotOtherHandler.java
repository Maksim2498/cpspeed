package space.moontalk.mc.cpspeed.command;

import org.bukkit.entity.Player;

import org.jetbrains.annotations.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.val;

import space.moontalk.mc.commands.route.RouteCall;
import space.moontalk.mc.commands.route.RouteHandler;

import space.moontalk.mc.cpspeed.teleport.TeleportManager;
import space.moontalk.mc.cpspeed.teleport.TeleportManagerHolder;

// tpa list (got | in) %p

@Getter
@AllArgsConstructor
public class TpaListGotOtherHandler implements RouteHandler,
                                               TeleportManagerHolder {
    protected final @NotNull TeleportManager teleportManager;

    @Override
    public void onRoute(@NotNull RouteCall call) throws Exception {
        final Player player   = call.getPlaceholdedAt(0);
        val          sender   = call.getCommandSender();
        val          requests = teleportManager.getGotRequests(player);
        val messageProvider   = teleportManager.getMessageProvider();
        
        if (requests.isEmpty()) {
            val message = messageProvider.makeHasNoGotRequestsMessage(player);
            sender.sendMessage(message);
            return;
        }

        var message = messageProvider.makeGotRequestsHeaderMessage(player, requests);
        sender.sendMessage(message);

        for (val request : requests) {
            message = messageProvider.makeGotRequestsItemMessage(request, player);
            sender.sendMessage(message);
        }
    }

    @Override
    public @NotNull String getPermission() {
        return "cpspeed.tpa.list.got.other";
    }
}
