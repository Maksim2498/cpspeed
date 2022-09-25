package space.moontalk.mc.cpspeed.message;

import java.util.Set;

import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;

import org.jetbrains.annotations.NotNull;

import lombok.AllArgsConstructor;
import lombok.val;

import space.moontalk.placeholders.DefaultSubstituter;
import space.moontalk.placeholders.Substituter;

@AllArgsConstructor
public class DefaultMessageProvider implements MessageProvider {
    private final @NotNull Configuration config;
    private final @NotNull Substituter   substituter = new DefaultSubstituter();
    
    @Override
    public @NotNull String makeAlreadySentRequestMessage(@NotNull Player from, @NotNull Player to) {
        return getFormatedString(
            "request.already-sent",
            "from", from.getName(),
            "to",   to.getName()
        );
    }

    @Override
    public @NotNull String makeRequestSentMessage(@NotNull Player from, @NotNull Player to) {
        return getFormatedString(
            "request.sent",
            "from", from.getName(),
            "to",   to.getName()
        );
    }

    @Override
    public @NotNull String makeRequestGotMessage(@NotNull Player from, @NotNull Player to) {
        return getFormatedString(
            "request.got",
            "from", from.getName(),
            "to",   to.getName()
        );
    }

	@Override
    public @NotNull String makeYouHaveNoRequestsMessage(@NotNull Player to) {
        return getFormatedString(
            "request.you-have-no",
            "to", to.getName()
        );
    }
    
	@Override
    public @NotNull String makeYouHaveNoRequestFromMessage(@NotNull Player from, @NotNull Player to) {
        return getFormatedString(
            "request.you-have-no-from",
            "from", from.getName(),
            "to",   to.getName()
        );
    }
    
	@Override
    public @NotNull String makeYouHaveDeniedRequestMessage(@NotNull Player from, @NotNull Player to) {
        return getFormatedString(
            "request.you-have-denied",
            "from", from.getName(),
            "to",   to.getName()
        );
    }
    
	@Override
    public @NotNull String makeYourRequestWasDeniedMessage(@NotNull Player from, @NotNull Player to) {
        return getFormatedString(
            "request.your-was-denied",
            "from", from.getName(),
            "to",   to.getName()
        );
    }

    @Override
    public @NotNull String makeYouHaveAcceptedRequestMessage(@NotNull Player from, @NotNull Player to) {
        return getFormatedString(
            "request.you-have-accepted",
            "from", from.getName(),
            "to",   to.getName()
        );
    }

    @Override
    public @NotNull String makeYourRequestWasAcceptedMessage(@NotNull Player from, @NotNull Player to) {
        return getFormatedString(
            "request.your-was-accepted",
            "from", from.getName(),
            "to",   to.getName()
        );
    }
    
	@Override
    public @NotNull String makeYourRequestTimeOutMessage(@NotNull Player from, @NotNull Player to) {
        return getFormatedString(
            "request.your-time-out",
            "from", from.getName(),
            "to",   to.getName()
        );
    }
    
	@Override
    public @NotNull String makeRequestTimeOutMessage(@NotNull Player from, @NotNull Player to) {
        return getFormatedString(
            "request.time-out",
            "from", from.getName(),
            "to",   to.getName()
        );
    }
    
	@Override
    public @NotNull String makeSpecifyRequestMessage(@NotNull Player to, @NotNull Set<Player> requests) {
        return getFormatedString(
            "request.specify",
            "to",    to.getName(),
            "count", Integer.toString(requests.size())
        );
    }

    @Override
	public @NotNull String makeYouHaveNoGotRequestsMessage(@NotNull Player to) {
        return getFormatedString(
            "request.list.you-have-no-got",
            "to", to.getName()
        );
    }

    @Override
	public @NotNull String makeHasNoGotRequestsMessage(@NotNull Player to) {
        return getFormatedString(
            "request.list.has-no-got",
            "to", to.getName()
        );
    }

	@Override
	public @NotNull String makeYourGotRequestsHeaderMessage(@NotNull Player to, @NotNull Set<Player> requests) {
        return getFormatedString(
            "request.list.your-got-header",
            "to",    to.getName(),
            "count", Integer.toString(requests.size())
        );
    }

	@Override
	public @NotNull String makeGotRequestsHeaderMessage(@NotNull Player to, @NotNull Set<Player> requests) {
        return getFormatedString(
            "request.list.got-header",
            "to",    to.getName(),
            "count", Integer.toString(requests.size())
        );
    }
    
	@Override
	public @NotNull String makeGotRequestsItemMessage(@NotNull Player from, @NotNull Player to) {
        return getFormatedString(
            "request.list.got-item",
            "from",  from.getName(),
            "to",    to.getName()
        );
    }

    @Override
	public @NotNull String makeYouHaveNoSentRequestsMessage(@NotNull Player to) {
        return getFormatedString(
            "request.list.you-have-no-sent",
            "to", to.getName()
        );
    }

	public @NotNull String makeHasNoSentRequestsMessage(@NotNull Player to) {
        return getFormatedString(
            "request.list.has-no-sent",
            "to", to.getName()
        );
    }
    
	@Override
	public @NotNull String makeYourSentRequestsHeaderMessage(@NotNull Player from, @NotNull Set<Player> requests) {
        return getFormatedString(
            "request.list.your-sent-header",
            "sent",  from.getName(),
            "count", Integer.toString(requests.size())
        );
    }

	@Override
	public @NotNull String makeSentRequestsHeaderMessage(@NotNull Player from, @NotNull Set<Player> requests) {
        return getFormatedString(
            "request.list.sent-header",
            "from",  from.getName(),
            "count", Integer.toString(requests.size())
        );
    }
    
	@Override
	public @NotNull String makeSentRequestsItemMessage(@NotNull Player from, @NotNull Player to) {
        return getFormatedString(
            "request.list.sent-item",
            "from",  from.getName(),
            "to",    to.getName()
        );
    }
    
    @Override
    public @NotNull String makeCannotBeTeleportedNowMessage(@NotNull Player from, @NotNull Player to) {
        return getFormatedString(
            "teleport.cannot",
            "from", from.getName(),
            "to",   to.getName()
        );
    }
    
	@Override
    public @NotNull String makeTeleportingToLocationMessage(@NotNull Player from) {
        return getFormatedString(
            "teleport.to-location",
            "from", from.getName() 
        );
    }
    
	@Override
    public @NotNull String makeTeleportingToNamedLocationMessage(@NotNull Player from, @NotNull String to) {
        return getFormatedString(
            "teleport.to-named-location",
            "from", from.getName(),
            "to",   to
        );
    }
    
	@Override
    public @NotNull String makeTeleportingToPlayerMessage(@NotNull Player from, @NotNull Player to) {
        return getFormatedString(
            "teleport.to-player",
            "from", from.getName(),
            "to",   to.getName()
        );
    }

    @Override
    public @NotNull String makeTeleportingToYouMessage(@NotNull Player from, @NotNull Player to) {
        return getFormatedString(
            "teleport.to-you",
            "from", from.getName(),
            "to",   to.getName()
        );
    }
    
	@Override
    public @NotNull String makeCountDownMessage(@NotNull Time left, @NotNull Time delay) {
        return getFormatedString(
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
        return getFormatedString(
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
        return getFormatedString(
            "error.cannot-send-request-to-your-self",
            "from", from.getName()
        );
    }
    
	@Override
    public @NotNull String makeWaitUntilCoolDownOverMessage(@NotNull Player from, @NotNull Time left, @NotNull Time delay) {
        return getFormatedString(
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
        return getFormatedString(
            "error.missing-bed",
            "from", from.getName()
        );
    }

    @Override
    public @NotNull String makeAlreadyTeleportingMessage(@NotNull Player from) {
        return getFormatedString(
            "error.already-teleporting",
            "from", from.getName()
        );
    }

    private @NotNull String getFormatedString(@NotNull String path, @NotNull String ...replacements) {
        val string    = getString(path);
        val formatted = substituter.substitute(string, replacements);

        return formatted;
    }

    private @NotNull String getString(@NotNull String path) {
        return config.getString("message." + path);
    }
}
