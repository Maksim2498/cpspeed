package space.moontalk.mc.cpspeed;

import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import lombok.Getter;
import lombok.val;

import space.moontalk.mc.more.bukkit.MoreBukkit;

import space.moontalk.mc.commands.DefaultMultiCommandHandler;
import space.moontalk.mc.commands.ParsingMultiCommandHandler;
import space.moontalk.mc.commands.route.ParsingRouter;

import space.moontalk.mc.cpspeed.command.*;
import space.moontalk.mc.cpspeed.message.*;
import space.moontalk.mc.cpspeed.persistence.*;
import space.moontalk.mc.cpspeed.teleport.*;

@Getter
public class CPSpeed extends    JavaPlugin 
                     implements MessageProviderHolder, 
                                PersistenceManagerHolder,
                                TeleportManagerHolder {
    private @Nullable MessageProvider                           messageProvider;
    private @Nullable TeleportManager                           teleportManager;
    private @Nullable PersistenceManager                        persistenceManager;
    private @Nullable ParsingMultiCommandHandler<ParsingRouter> commandHandler;

    @Override
    public void onEnable() {
        try {
            setupConfig();
            setupMessageProvider();
            setupPersistenceManager();
            setupTeleportManager();
            setupCommandHandler();
            setupCommands();
        } catch (IOException | ClassNotFoundException exception) {
            reactCriticalError(exception);
        }
    }

    @Override
    public void onDisable() {
        if (persistenceManager == null) 
            return;

        try {
            persistenceManager.save();
        } catch (IOException exception) {
            reactError(exception);
        }
    }

    private void setupConfig() {
        saveDefaultConfig();
    }

    private void setupMessageProvider() {
        messageProvider = new DefaultMessageProvider(getConfig());
    }

    private void setupPersistenceManager() throws ClassNotFoundException, IOException {
        persistenceManager = createPersistanceManager();
        persistenceManager.load();
        setupAutosave();
    }

    private @NotNull PersistenceManager createPersistanceManager() throws IOException {
        val config = getConfig();
        val method = config.getString("persistence.method");

        return switch (method) {
            case "fs" -> new FilePersistenceManager(messageProvider, getDataFolder());

            default -> {
                val message = String.format("invalid persistence method value: %s", method);
                throw new RuntimeException(message);
            }
        };
    }

    private void setupAutosave() {
        val config   = getConfig();
        val autosave = config.getBoolean("persistence.autosave");

        if (!autosave)
            return;

        val autosaveIntervalSeconds = config.getInt("persistence.autosave-interval");
        val autosaveIntervalTicks   = autosaveIntervalSeconds * MoreBukkit.SECOND_TICKS;
        val scheduler               = Bukkit.getScheduler();
        scheduler.scheduleSyncRepeatingTask(this, () -> {
            try {
                if (persistenceManager != null)
                    persistenceManager.save();
            } catch (IOException exception) {
                reactError(exception);
            }
        }, autosaveIntervalTicks, autosaveIntervalTicks);
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

    private void reactCriticalError(@NotNull Exception exception) {
        val logger  = getLogger();
        val message = String.format("Critical error occurred:%s\nDisabling plugin...", exception.getMessage());
        logger.info(message);

        val server        = getServer();
        val pluginManager = server.getPluginManager();
        pluginManager.disablePlugin(this);
    }

    private void reactError(@NotNull Exception exception) {
        val logger  = getLogger();
        val message = String.format("Error occurred:%s", exception.getMessage());
        logger.info(message);
    }
}
