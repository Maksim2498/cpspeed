package space.moontalk.mc.cpspeed.message;

import java.util.Set;

import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;

import org.jetbrains.annotations.NotNull;

import space.moontalk.mc.more.bukkit.message.AbstractConfigMessageProvider;

public class DefaultMessageProvider extends    AbstractConfigMessageProvider
                                    implements MessageProvider {
    
    public DefaultMessageProvider(@NotNull Configuration config) {
        super(config, "message");
    }

    @Override
    public @NotNull String makeAlreadySentRequestMessage(@NotNull Player from, @NotNull Player to) {
        return getFormattedString(
            "request.already-sent",
            "from", from.getName(),
            "to",   to.getName()
        );
    }

    @Override
    public @NotNull String makeRequestSentMessage(@NotNull Player from, @NotNull Player to) {
        return getFormattedString(
            "request.sent",
            "from", from.getName(),
            "to",   to.getName()
        );
    }

    @Override
    public @NotNull String makeRequestGotMessage(@NotNull Player from, @NotNull Player to) {
        return getFormattedString(
            "request.got",
            "from", from.getName(),
            "to",   to.getName()
        );
    }

	@Override
    public @NotNull String makeYouHaveNoRequestsMessage(@NotNull Player to) {
        return getFormattedString(
            "request.you-have-no",
            "to", to.getName()
        );
    }
    
	@Override
    public @NotNull String makeYouHaveNoRequestFromMessage(@NotNull Player from, @NotNull Player to) {
        return getFormattedString(
            "request.you-have-no-from",
            "from", from.getName(),
            "to",   to.getName()
        );
    }
    
	@Override
    public @NotNull String makeYouHaveDeniedRequestMessage(@NotNull Player from, @NotNull Player to) {
        return getFormattedString(
            "request.you-have-denied",
            "from", from.getName(),
            "to",   to.getName()
        );
    }
    
	@Override
    public @NotNull String makeYourRequestWasDeniedMessage(@NotNull Player from, @NotNull Player to) {
        return getFormattedString(
            "request.your-was-denied",
            "from", from.getName(),
            "to",   to.getName()
        );
    }

    @Override
    public @NotNull String makeYouHaveAcceptedRequestMessage(@NotNull Player from, @NotNull Player to) {
        return getFormattedString(
            "request.you-have-accepted",
            "from", from.getName(),
            "to",   to.getName()
        );
    }

    @Override
    public @NotNull String makeYourRequestWasAcceptedMessage(@NotNull Player from, @NotNull Player to) {
        return getFormattedString(
            "request.your-was-accepted",
            "from", from.getName(),
            "to",   to.getName()
        );
    }
    
	@Override
    public @NotNull String makeYourRequestTimeOutMessage(@NotNull Player from, @NotNull Player to) {
        return getFormattedString(
            "request.your-time-out",
            "from", from.getName(),
            "to",   to.getName()
        );
    }
    
	@Override
    public @NotNull String makeRequestTimeOutMessage(@NotNull Player from, @NotNull Player to) {
        return getFormattedString(
            "request.time-out",
            "from", from.getName(),
            "to",   to.getName()
        );
    }
    
	@Override
    public @NotNull String makeSpecifyRequestMessage(@NotNull Player to, @NotNull Set<Player> requests) {
        return getFormattedString(
            "request.specify",
            "to",    to.getName(),
            "count", Integer.toString(requests.size())
        );
    }

    @Override
	public @NotNull String makeYouHaveNoGotRequestsMessage(@NotNull Player to) {
        return getFormattedString(
            "request.list.you-have-no-got",
            "to", to.getName()
        );
    }

    @Override
	public @NotNull String makeHasNoGotRequestsMessage(@NotNull Player to) {
        return getFormattedString(
            "request.list.has-no-got",
            "to", to.getName()
        );
    }

	@Override
	public @NotNull String makeYourGotRequestsHeaderMessage(@NotNull Player to, @NotNull Set<Player> requests) {
        return getFormattedString(
            "request.list.your-got-header",
            "to",    to.getName(),
            "count", Integer.toString(requests.size())
        );
    }

	@Override
	public @NotNull String makeGotRequestsHeaderMessage(@NotNull Player to, @NotNull Set<Player> requests) {
        return getFormattedString(
            "request.list.got-header",
            "to",    to.getName(),
            "count", Integer.toString(requests.size())
        );
    }
    
	@Override
	public @NotNull String makeGotRequestsItemMessage(@NotNull Player from, @NotNull Player to) {
        return getFormattedString(
            "request.list.got-item",
            "from",  from.getName(),
            "to",    to.getName()
        );
    }

    @Override
	public @NotNull String makeYouHaveNoSentRequestsMessage(@NotNull Player to) {
        return getFormattedString(
            "request.list.you-have-no-sent",
            "from", to.getName()
        );
    }

	public @NotNull String makeHasNoSentRequestsMessage(@NotNull Player to) {
        return getFormattedString(
            "request.list.has-no-sent",
            "from", to.getName()
        );
    }
    
	@Override
	public @NotNull String makeYourSentRequestsHeaderMessage(@NotNull Player from, @NotNull Set<Player> requests) {
        return getFormattedString(
            "request.list.your-sent-header",
            "sent",  from.getName(),
            "count", Integer.toString(requests.size())
        );
    }

	@Override
	public @NotNull String makeSentRequestsHeaderMessage(@NotNull Player from, @NotNull Set<Player> requests) {
        return getFormattedString(
            "request.list.sent-header",
            "from",  from.getName(),
            "count", Integer.toString(requests.size())
        );
    }
    
	@Override
	public @NotNull String makeSentRequestsItemMessage(@NotNull Player from, @NotNull Player to) {
        return getFormattedString(
            "request.list.sent-item",
            "from",  from.getName(),
            "to",    to.getName()
        );
    }
    
    @Override
    public @NotNull String makeCannotBeTeleportedNowMessage(@NotNull Player from, @NotNull Player to) {
        return getFormattedString(
            "teleport.cannot",
            "from", from.getName(),
            "to",   to.getName()
        );
    }
    
	@Override
    public @NotNull String makeTeleportingToLocationMessage(@NotNull Player from) {
        return getFormattedString(
            "teleport.to-location",
            "from", from.getName() 
        );
    }
    
	@Override
    public @NotNull String makeTeleportingToNamedLocationMessage(@NotNull Player from, @NotNull String to) {
        return getFormattedString(
            "teleport.to-named-location",
            "from", from.getName(),
            "to",   to
        );
    }
    
	@Override
    public @NotNull String makeTeleportingToPlayerMessage(@NotNull Player from, @NotNull Player to) {
        return getFormattedString(
            "teleport.to-player",
            "from", from.getName(),
            "to",   to.getName()
        );
    }

    @Override
    public @NotNull String makeTeleportingToYouMessage(@NotNull Player from, @NotNull Player to) {
        return getFormattedString(
            "teleport.to-you",
            "from", from.getName(),
            "to",   to.getName()
        );
    }
    
	@Override
    public @NotNull String makeCountDownMessage(@NotNull Time left, @NotNull Time delay) {
        return getFormattedString(
            "teleport.count-down",
            "left",          left.toString(this),
            "left-seconds",  Integer.toString(left.getSeconds()),
            "left-minutes",  Integer.toString(left.getMinutes()),
            "delay",         delay.toString(this),
            "delay-seconds", Integer.toString(delay.getSeconds()),
            "delay-minutes", Integer.toString(delay.getMinutes())
        );
    }
    
	@Override
    public @NotNull String makeTeleportedMessage(@NotNull Player from) {
        return getFormattedString(
            "teleport.finish",
            "from", from.getName()
        );
    }

    @Override
    public @NotNull String makeSecondsMessage() {
        return getString("teleport.seconds");
    }
    
	@Override
    public @NotNull String makeCannotSendRequestToYourSelfMessage(@NotNull Player from) {
        return getFormattedString(
            "error.cannot-send-request-to-your-self",
            "from", from.getName()
        );
    }
    
	@Override
    public @NotNull String makeWaitUntilCoolDownOverMessage(@NotNull Player from, @NotNull Time left, @NotNull Time delay) {
        return getFormattedString(
            "error.wait-until-cool-down-over",
            "from",          from.getName(),
            "left",          left.toString(this),
            "left-seconds",  Integer.toString(left.getSeconds()),
            "left-minutes",  Integer.toString(left.getMinutes()),
            "delay",         delay.toString(this),
            "delay-seconds", Integer.toString(delay.getSeconds()),
            "delay-minutes", Integer.toString(delay.getMinutes())
        );
    }
    
	@Override
    public @NotNull String makeMissingBedMessage(@NotNull Player from) {
        return getFormattedString(
            "error.missing-bed",
            "from", from.getName()
        );
    }

    @Override
	public @NotNull String makeMissingPlayerBedMessage(@NotNull Player from, @NotNull OfflinePlayer to) {
        return getFormattedString(
            "error.missing-player-bed",
            "from", from.getName(),
            "to",   to.getName()
        );
    }

    @Override
    public @NotNull String makeAlreadyTeleportingMessage(@NotNull Player from) {
        return getFormattedString(
            "error.already-teleporting",
            "from", from.getName()
        );
    }

    @Override
	public @NotNull String makePlayerNotFoundMessage(@NotNull String player) {
        return getFormattedString(
            "error.player-not-found",
            "player", player 
        );
    }
	
    @Override
	public @NotNull String makeWorldNotFoundMessage(@NotNull String world) {
        return getFormattedString(
            "error.world-not-found",
            "world", world 
        );
    }

	@Override
	public @NotNull String makeMissingPermissionMessage(@NotNull CommandSender sender, @NotNull String permission) {
        return getFormattedString(
            "error.missing-permission",
            "sender",     sender.getName(),
            "permission", permission
        );
    }

	@Override
	public @NotNull String makeInvalidClassMessage(@NotNull Set<Class<?>> classes) {
        return getString("error.cannot-run");
    }
}
