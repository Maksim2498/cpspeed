package space.moontalk.mc.cpspeed.command;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import org.jetbrains.annotations.NotNull;

import lombok.val;

import space.moontalk.mc.cpspeed.teleport.TeleportManager;

// list (sent | out) %p?

public class TpaListSentHandler extends AbstractTpaListHandler {
    public TpaListSentHandler(@NotNull TeleportManager teleportManager) {
        super(teleportManager, "sent");
    }

    @Override
    protected void handleSpecifiedPlayer(@NotNull CommandSender sender, @NotNull Player player) {
        val requests        = teleportManager.getSentRequests(player);
        val messageProvider = teleportManager.getMessageProvider();
        
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
    protected void handleSenderPlayer(@NotNull Player player) {
        val requests        = teleportManager.getSentRequests(player);
        val messageProvider = teleportManager.getMessageProvider();
        
        if (requests.isEmpty()) {
            val message = messageProvider.makeYouHaveNoSentRequestsMessage(player);
            player.sendMessage(message);
            return;
        }

        var message = messageProvider.makeYourSentRequestsHeaderMessage(player, requests);
        player.sendMessage(message);

        for (val request : requests) {
            message = messageProvider.makeSentRequestsItemMessage(player, request);
            player.sendMessage(message);
        }
    }
}
