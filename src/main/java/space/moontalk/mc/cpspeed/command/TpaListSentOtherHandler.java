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

// tpa list (sent | out) %p

@Getter
@AllArgsConstructor
public class TpaListSentOtherHandler implements RouteHandler,
                                                TeleportManagerHolder {
    protected final @NotNull TeleportManager teleportManager;

    @Override
    public void onRoute(@NotNull RouteCall call) throws Exception {
        final Player player   = call.getPlaceholdedAt(0);
        val          sender   = call.getCommandSender();
        val          requests = teleportManager.getSentRequests(player);
        val messageProvider   = teleportManager.getMessageProvider();
        
        if (requests.isEmpty()) {
            val message = messageProvider.makeHasNoSentRequestsMessage(player);
            sender.sendMessage(message);
            return;
        }

        var message = messageProvider.makeSentRequestsHeaderMessage(player, requests);
        sender.sendMessage(message);

        for (val request : requests) {
            message = messageProvider.makeSentRequestsItemMessage(player, request);
            sender.sendMessage(message);
        }
    }

    @Override
    public @NotNull String getPermission() {
        return "cpspeed.tpa.list.sent.other";
    }
}
