package space.moontalk.mc.cpspeed.message;

import java.util.Set;

import org.bukkit.entity.Player;

import org.jetbrains.annotations.NotNull;

public interface MessageProvider extends SecondsMessageProvider {
    // Requests:
    
	@NotNull String makeAlreadySentRequestMessage(@NotNull Player from, @NotNull Player to);
    
	@NotNull String makeRequestSentMessage(@NotNull Player from, @NotNull Player to);
    
	@NotNull String makeRequestGotMessage(@NotNull Player from, @NotNull Player to);
    
	@NotNull String makeYouHaveNoRequestsMessage(@NotNull Player to);
    
	@NotNull String makeYouHaveNoRequestFromMessage(@NotNull Player from, @NotNull Player to);
    
	@NotNull String makeYouHaveDeniedRequestMessage(@NotNull Player from, @NotNull Player to);
    
	@NotNull String makeYourRequestWasDeniedMessage(@NotNull Player from, @NotNull Player to);
    
	@NotNull String makeYouHaveAcceptedRequestMessage(@NotNull Player from, @NotNull Player to);
    
	@NotNull String makeYourRequestWasAcceptedMessage(@NotNull Player from, @NotNull Player to);
    
	@NotNull String makeYourRequestTimeOutMessage(@NotNull Player from, @NotNull Player to);
    
	@NotNull String makeRequestTimeOutMessage(@NotNull Player from, @NotNull Player to);

	@NotNull String makeSpecifyRequestMessage(@NotNull Player to, @NotNull Set<Player> requests);

	@NotNull String makeYouHaveNoGotRequestsMessage(@NotNull Player to);

	@NotNull String makeHasNoGotRequestsMessage(@NotNull Player to);
    
	@NotNull String makeYourGotRequestsHeaderMessage(@NotNull Player to, @NotNull Set<Player> requests);

	@NotNull String makeGotRequestsHeaderMessage(@NotNull Player to, @NotNull Set<Player> requests);
    
	@NotNull String makeGotRequestsItemMessage(@NotNull Player from, @NotNull Player to);
    
	@NotNull String makeYouHaveNoSentRequestsMessage(@NotNull Player to);

	@NotNull String makeHasNoSentRequestsMessage(@NotNull Player to);
    
	@NotNull String makeYourSentRequestsHeaderMessage(@NotNull Player from, @NotNull Set<Player> requests);

	@NotNull String makeSentRequestsHeaderMessage(@NotNull Player from, @NotNull Set<Player> requests);
    
	@NotNull String makeSentRequestsItemMessage(@NotNull Player from, @NotNull Player to);

    // Teleporting:
    
	@NotNull String makeCannotBeTeleportedNowMessage(@NotNull Player from, @NotNull Player to);
    
	@NotNull String makeTeleportingToLocationMessage(@NotNull Player from);
    
	@NotNull String makeTeleportingToNamedLocationMessage(@NotNull Player from, @NotNull String to);
    
	@NotNull String makeTeleportingToPlayerMessage(@NotNull Player from, @NotNull Player to);
    
	@NotNull String makeTeleportingToYouMessage(@NotNull Player from, @NotNull Player to);
    
	@NotNull String makeCountDownMessage(@NotNull Time left, @NotNull Time delay);
    
	@NotNull String makeTeleportedMessage(@NotNull Player player);

    // Errors:
    
	@NotNull String makeCannotSendRequestToYourSelfMessage(@NotNull Player from);
    
	@NotNull String makeWaitUntilCoolDownOverMessage(@NotNull Player from, @NotNull Time left, @NotNull Time delay);
    
	@NotNull String makeMissingBedMessage(@NotNull Player from);
    
	@NotNull String makeAlreadyTeleportingMessage(@NotNull Player from);
}
