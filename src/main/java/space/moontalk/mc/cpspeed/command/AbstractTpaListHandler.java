package space.moontalk.mc.cpspeed.command;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import org.jetbrains.annotations.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.val;

import space.moontalk.mc.commands.CommandException;
import space.moontalk.mc.commands.route.RouteCall;
import space.moontalk.mc.commands.route.RouteHandler;

import space.moontalk.mc.cpspeed.teleport.TeleportManager;
import space.moontalk.mc.cpspeed.teleport.TeleportManagerHolder;

// list (got | in) %p?

@Getter
@AllArgsConstructor
public abstract class AbstractTpaListHandler implements RouteHandler, TeleportManagerHolder {
    protected final @NotNull TeleportManager teleportManager;
    private   final @NotNull String          type;

    @Override
    public void onRoute(@NotNull RouteCall call) throws Exception {
        Player specifiedPlayer = call.getPlaceholdedAtOrNull(0);
        val    sender          = call.getCommandSender();

        if (specifiedPlayer != null) {
            prehandleSpecifiedPlayer(sender, specifiedPlayer);
            return;
        }

        if (sender instanceof Player senderPlayer) {
            prehandleSenderPlayer(senderPlayer);
            return;
        }

        throw new CommandException();
    }

    private void prehandleSpecifiedPlayer(@NotNull CommandSender sender, @NotNull Player player) throws Exception {
        val permission = String.format("cpspeed.tpa.list.%s.other", type);

        if (!sender.hasPermission(permission))
            throw new CommandException();

        handleSpecifiedPlayer(sender, player);
    }

    protected abstract void handleSpecifiedPlayer(@NotNull CommandSender sender, @NotNull Player player);

    private void prehandleSenderPlayer(@NotNull Player player) throws Exception {
        val permission = String.format("cpspeed.tpa.list.%s.self", type);

        if (!player.hasPermission(permission))
            throw new CommandException();

        handleSenderPlayer(player);
    }

    protected abstract void handleSenderPlayer(@NotNull Player player);
}
