package space.moontalk.mc.cpspeed;

import java.util.Objects;

import org.bukkit.plugin.java.JavaPlugin;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import lombok.Getter;
import lombok.Setter;
import lombok.val;

import space.moontalk.mc.commands.CommandHandler;
import space.moontalk.mc.commands.route.RouteHandler;

import space.moontalk.mc.cpspeed.command.*;
import space.moontalk.mc.cpspeed.message.*;
import space.moontalk.mc.cpspeed.teleport.*;

@Getter
@Setter
public class CPSpeed extends JavaPlugin {
    private @Nullable MessageProvider messageProvider;
    private @Nullable TeleportManager teleportManager;

    @Override
    public void onEnable() {
        try {
            setupConfig();
            setupMessageProvider();
            setupTeleportManager();
            setupCommands();
        } catch (Exception exception) {
            getLogger().info(exception.getMessage());
            getServer().getPluginManager().disablePlugin(this);
        }
    }

    private void setupConfig() {
        saveDefaultConfig();
    }

    private void setupMessageProvider() {
        messageProvider = new DefaultMessageProvider(getConfig());
    }

    private void setupTeleportManager() throws Exception {
        val builder = new DefaultTeleporationManager.Builder();

        teleportManager = builder.messageProvider(messageProvider)
                                 .plugin(this)
                                 .build();
    }

    private void setupCommands() throws Exception {
        setupSpawnCommand();
        setupWorldSpawnCommand();
        setupTpaCommand();
    }

    private void setupSpawnCommand() throws Exception {
        val handler = new SpawnHandler(teleportManager);
        setupCommandRouteHandler("spawn", "", handler);
    }

    private void setupWorldSpawnCommand() throws Exception {
        val handler = new WorldSpawnHandler(teleportManager);
        setupCommandRouteHandler("worldspawn", "%w?", handler);
    }

    private void setupCommandRouteHandler(
        @NotNull String       commandName,
        @NotNull String       route,
        @NotNull RouteHandler routeHandler
    ) throws Exception {
        val commandHandler = new CommandHandler();
        val router         = commandHandler.getRouter();
        router.addRoute(route, routeHandler);

        val command = Objects.requireNonNull(getCommand(commandName));
        commandHandler.attach(command);
    }

    private void setupTpaCommand() throws Exception {
        val commandHandler = new CommandHandler();
        val router         = commandHandler.getRouter();

        val tpaListGotHandler = new TpaListGotHandler(teleportManager);
        router.addRoute("list got %p?", tpaListGotHandler);

        val tpaListSentHandler = new TpaListSentHandler(teleportManager);
        router.addRoute("list sent %p?", tpaListSentHandler);

        val tpaToHandler = new TpaToHandler(teleportManager);
        router.addRoute("to? %p", tpaToHandler); 

        val tpaAcceptHandler = new TpaAcceptHandler(teleportManager);
        router.addRoute("accept %p?", tpaAcceptHandler); 

        val tpaDenyHandler = new TpaDenyHandler(teleportManager);
        router.addRoute("deny %p?", tpaDenyHandler); 

        val command = Objects.requireNonNull(getCommand("tpa"));
        commandHandler.attach(command);
    }
}
