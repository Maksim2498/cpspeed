package space.moontalk.mc.cpspeed.command;

import java.util.List;
import java.util.stream.Collectors;

import org.bukkit.entity.Player;

import org.jetbrains.annotations.NotNull;

import lombok.Getter;
import lombok.val;

import space.moontalk.mc.commands.CommandCall;
import space.moontalk.mc.commands.message.MessageProviderManager;
import space.moontalk.mc.commands.placeholder.PlayerPlaceholder;

import space.moontalk.mc.cpspeed.teleport.TeleportManager;

@Getter
public class TpaToPlayerPlaceholder extends PlayerPlaceholder {
    private final @NotNull TeleportManager teleportManager;

    public TpaToPlayerPlaceholder(
        @NotNull MessageProviderManager messageProviderManager,
        @NotNull TeleportManager        teleportManager
    ) {
        super(messageProviderManager);
        this.teleportManager = teleportManager;
    }

    @Override
    public @NotNull List<String> evalVariants(@NotNull CommandCall call) {
        val variants = super.evalVariants(call);
        val sender   = call.getCommandSender();

        if (sender instanceof Player senderPlayer) {
            val senderName = sender.getName();
            val requests   = teleportManager.getSentRequests(senderPlayer);

            return variants.stream().filter(name -> {
                if (name.equalsIgnoreCase(senderName))
                    return false;

                for (val request : requests) {
                    val requestName = request.getName();

                    if (name.equalsIgnoreCase(requestName))
                        return false;
                }

                return true;
            }).collect(Collectors.toList());
        }

        return variants;
    }
}
