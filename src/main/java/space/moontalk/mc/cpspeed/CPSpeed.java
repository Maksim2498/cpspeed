package space.moontalk.mc.cpspeed;

import org.bukkit.plugin.java.JavaPlugin;

import org.jetbrains.annotations.Nullable;

import lombok.Getter;
import lombok.val;

import space.moontalk.mc.commands.DefaultMultiCommandHandler;
import space.moontalk.mc.commands.ParsingMultiCommandHandler;
import space.moontalk.mc.commands.route.ParsingRouter;

import space.moontalk.mc.cpspeed.command.*;
import space.moontalk.mc.cpspeed.message.*;
import space.moontalk.mc.cpspeed.teleport.*;

@Getter
public class CPSpeed extends    JavaPlugin 
                     implements MessageProviderHolder, 
                                TeleportManagerHolder {
    private @Nullable MessageProvider                           messageProvider;
    private @Nullable TeleportManager                           teleportManager;
    private @Nullable ParsingMultiCommandHandler<ParsingRouter> commandHandler;

    @Override
    public void onEnable() {
        setupConfig();
        setupMessageProvider();
        setupTeleportManager();
        setupCommandHandler();
        setupCommands();
    }

    private void setupConfig() {
        saveDefaultConfig();
    }

    private void setupMessageProvider() {
        messageProvider = new DefaultMessageProvider(getConfig());
    }

    private void setupTeleportManager() {
        val builder = new DefaultTeleporationManager.Builder();

        teleportManager = builder.messageProvider(messageProvider)
                                 .plugin(this)
                                 .build();
    }

    private void setupCommandHandler() {
        commandHandler = new DefaultMultiCommandHandler(this);

        val messageProviderManager = commandHandler.getMessageProviderManager();
        messageProviderManager.clearMessageProviders();
        messageProviderManager.setMessageProvider(MessageProvider.class, messageProvider); 

        val placeholderManager = commandHandler.getPlaceholderManager();

        val tpaToPlayerPlaceholder = new TpaToPlayerPlaceholder(messageProviderManager, teleportManager);
        placeholderManager.setPlaceholder('t', tpaToPlayerPlaceholder);

        val tpaRequestPlayerPlaceholder = new TpaRequestPlayerPlaceholder(messageProviderManager, teleportManager);
        placeholderManager.setPlaceholder('r', tpaRequestPlayerPlaceholder);
    }

    private void setupCommands() {
        // Spawn:

        val spawnHandler = new SpawnHandler(teleportManager);
        commandHandler.addCommandRoute("spawn", spawnHandler);

        // World Spawn:

        val worldSpawnHandler = new WorldSpawnHandler(teleportManager);
        commandHandler.addCommandRoute("worldspawn %w?", worldSpawnHandler);

        // Tpa:

        // - List:

        // -- Got:

        val tpaListGotOtherHandler = new TpaListGotOtherHandler(teleportManager);
        commandHandler.addCommandRoute("tpa list (got | in) %p", tpaListGotOtherHandler);

        val tpaListGotSelfHandler = new TpaListGotSelfHandler(teleportManager);
        commandHandler.addCommandRoute("tpa list (got | in)", tpaListGotSelfHandler);

        // -- Sent:

        val tpaListSentOtherHandler = new TpaListSentOtherHandler(teleportManager);
        commandHandler.addCommandRoute("tpa list (sent | out) %p", tpaListSentOtherHandler);

        val tpaListSentSelfHandler = new TpaListSentSelfHandler(teleportManager);
        commandHandler.addCommandRoute("tpa list (sent | out)", tpaListSentSelfHandler);

        // - To:

        val tpaToHandler = new TpaToHandler(teleportManager);
        commandHandler.addCommandRoute("tpa to? %t", tpaToHandler); 

        // - Accept:

        val tpaAcceptHandler = new TpaAcceptHandler(teleportManager);
        commandHandler.addCommandRoute("tpa accept %r?", tpaAcceptHandler); 

        // - Deny:

        val tpaDenyHandler = new TpaDenyHandler(teleportManager);
        commandHandler.addCommandRoute("tpa deny %r?", tpaDenyHandler); 
    }
}
