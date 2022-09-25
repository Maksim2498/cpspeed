package space.moontalk.mc.cpspeed.command;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import org.jetbrains.annotations.NotNull;

import lombok.val;

import space.moontalk.mc.cpspeed.teleport.TeleportManager;

// list (got | in) %p?

public class TpaListGotHandler extends AbstractTpaListHandler {
    public TpaListGotHandler(@NotNull TeleportManager teleportManager) {
        super(teleportManager, "got");
    }

    @Override
    protected void handleSpecifiedPlayer(@NotNull CommandSender sender, @NotNull Player player) {
        val requests        = teleportManager.getGotRequests(player);
        val messageProvider = teleportManager.getMessageProvider();
        
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
    protected void handleSenderPlayer(@NotNull Player player) {
        val requests        = teleportManager.getGotRequests(player);
        val messageProvider = teleportManager.getMessageProvider();
        
        if (requests.isEmpty()) {
            val message = messageProvider.makeYouHaveNoGotRequestsMessage(player);
            player.sendMessage(message);
            return;
        }

        var message = messageProvider.makeYourGotRequestsHeaderMessage(player, requests);
        player.sendMessage(message);

        for (val request : requests) {
            message = messageProvider.makeGotRequestsItemMessage(request, player);
            player.sendMessage(message);
        }
    }
}
