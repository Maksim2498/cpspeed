package space.moontalk.mc.cpspeed.command;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import org.jetbrains.annotations.NotNull;

import lombok.Getter;
import lombok.val;

import space.moontalk.mc.commands.CommandCall;
import space.moontalk.mc.commands.message.MessageProviderManager;
import space.moontalk.mc.commands.placeholder.PlayerPlaceholder;

import space.moontalk.mc.cpspeed.teleport.TeleportManager;

@Getter
public class TpaRequestPlayerPlaceholder extends PlayerPlaceholder {
    private final @NotNull TeleportManager teleportManager;

    public TpaRequestPlayerPlaceholder(
        @NotNull MessageProviderManager messageProviderManager,
        @NotNull TeleportManager        teleportManager
    ) {
        super(messageProviderManager);
        this.teleportManager = teleportManager;
    }

    @Override
    public @NotNull List<String> evalVariants(@NotNull CommandCall call) {
        val sender = call.getCommandSender();

        if (sender instanceof Player senderPlayer) {
            val variants = super.evalVariants(call);
            val requests = teleportManager.getGotRequests(senderPlayer);

            return variants.stream().filter(name -> requests.contains(Bukkit.getPlayer(name)))
                                    .collect(Collectors.toList());
        }

        return Collections.emptyList();
    }
}
