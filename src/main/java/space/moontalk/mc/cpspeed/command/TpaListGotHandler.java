package space.moontalk.mc.cpspeed.command;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import org.jetbrains.annotations.NotNull;

import space.moontalk.mc.cpspeed.teleport.TeleportManager;

// list (got | in) %p?

public class TpaListGotHandler extends AbstractTpaListHandler {
    public TpaListGotHandler(@NotNull TeleportManager teleportManager) {
        super(teleportManager, "got");
    }

    @Override
    protected void handleSpecifiedPlayer(@NotNull CommandSender sender, @NotNull Player player) {

    }

    @Override
    protected void handleSenderPlayer(@NotNull Player player) {

    }
}
