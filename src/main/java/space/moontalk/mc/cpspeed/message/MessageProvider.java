package space.moontalk.mc.cpspeed.message;

import java.util.Set;
import java.util.UUID;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import org.jetbrains.annotations.NotNull;

import lombok.val;

import space.moontalk.mc.commands.message.placeholder.PlayerNotFoundMessageProvider;
import space.moontalk.mc.commands.message.placeholder.WorldNotFoundMessageProvider;
import space.moontalk.mc.commands.message.route.InvalidClassMessageProvider;
import space.moontalk.mc.commands.message.route.MissingPermissionMessageProvider;

import space.moontalk.mc.more.bukkit.message.ConfigMessageProvider;

import static space.moontalk.mc.more.bukkit.MoreBukkit.*;

public interface MessageProvider extends SecondsMessageProvider,
										 ConfigMessageProvider,
										 PlayerNotFoundMessageProvider,
										 WorldNotFoundMessageProvider,
										 MissingPermissionMessageProvider,
										 InvalidClassMessageProvider {
    // Request:

	// - Already Sent:

	default @NotNull String makeAlreadySentRequestMessage(@NotNull String fromName, @NotNull String toName) {
		return makeAlreadySentRequestMessage(getPlayer(fromName), getPlayer(toName));
	}
    
	default @NotNull String makeAlreadySentRequestMessage(@NotNull String fromName, @NotNull UUID toUniqueId) {
		return makeAlreadySentRequestMessage(getPlayer(fromName), getPlayer(toUniqueId));
	}
    
	default @NotNull String makeAlreadySentRequestMessage(@NotNull String fromName, @NotNull Player to) {
		return makeAlreadySentRequestMessage(getPlayer(fromName), to);
	}

	default @NotNull String makeAlreadySentRequestMessage(@NotNull UUID fromUniqueId, @NotNull String toName) {
		return makeAlreadySentRequestMessage(getPlayer(fromUniqueId), getPlayer(toName));
	}
    
	default @NotNull String makeAlreadySentRequestMessage(@NotNull UUID fromUniqueId, @NotNull UUID toUniqueId) {
		return makeAlreadySentRequestMessage(getPlayer(fromUniqueId), getPlayer(toUniqueId));
	}
    
	default @NotNull String makeAlreadySentRequestMessage(@NotNull UUID fromUniqueId, @NotNull Player to) {
		return makeAlreadySentRequestMessage(getPlayer(fromUniqueId), to);
	}
    
	default @NotNull String makeAlreadySentRequestMessage(@NotNull Player from, @NotNull String toName) {
		return makeAlreadySentRequestMessage(from, getPlayer(toName));
	}

	default @NotNull String makeAlreadySentRequestMessage(@NotNull Player from, @NotNull UUID toUniqueId) {
		return makeAlreadySentRequestMessage(from, getPlayer(toUniqueId));
	}

	@NotNull String makeAlreadySentRequestMessage(@NotNull Player from, @NotNull Player to);

	// - Sent:
    
	default @NotNull String makeRequestSentMessage(@NotNull String fromName, @NotNull String toName) {
		return makeRequestSentMessage(getPlayer(fromName), getPlayer(toName));
	}
    
	default @NotNull String makeRequestSentMessage(@NotNull String fromName, @NotNull UUID toUniqueId) {
		return makeRequestSentMessage(getPlayer(fromName), getPlayer(toUniqueId));
	}
    
	default @NotNull String makeRequestSentMessage(@NotNull String fromName, @NotNull Player to) {
		return makeRequestSentMessage(getPlayer(fromName), to);
	}

	default @NotNull String makeRequestSentMessage(@NotNull UUID fromUniqueId, @NotNull String toName) {
		return makeRequestSentMessage(getPlayer(fromUniqueId), getPlayer(toName));
	}
    
	default @NotNull String makeRequestSentMessage(@NotNull UUID fromUniqueId, @NotNull UUID toUniqueId) {
		return makeRequestSentMessage(getPlayer(fromUniqueId), getPlayer(toUniqueId));
	}
    
	default @NotNull String makeRequestSentMessage(@NotNull UUID fromUniqueId, @NotNull Player to) {
		return makeRequestSentMessage(getPlayer(fromUniqueId), to);
	}
    
	default @NotNull String makeRequestSentMessage(@NotNull Player from, @NotNull String toName) {
		return makeRequestSentMessage(from, getPlayer(toName));
	}

	@NotNull String makeRequestSentMessage(@NotNull Player from, @NotNull Player to);

	// - Got:
    
	default @NotNull String makeRequestGotMessage(@NotNull String fromName, @NotNull String toName) {
		return makeRequestGotMessage(getPlayer(fromName), getPlayer(toName));
	}
    
	default @NotNull String makeRequestGotMessage(@NotNull String fromName, @NotNull UUID toUniqueId) {
		return makeRequestGotMessage(getPlayer(fromName), getPlayer(toUniqueId));
	}
    
	default @NotNull String makeRequestGotMessage(@NotNull String fromName, @NotNull Player to) {
		return makeRequestGotMessage(getPlayer(fromName), to);
	}

	default @NotNull String makeRequestGotMessage(@NotNull UUID fromUniqueId, @NotNull String toName) {
		return makeRequestGotMessage(getPlayer(fromUniqueId), getPlayer(toName));
	}
    
	default @NotNull String makeRequestGotMessage(@NotNull UUID fromUniqueId, @NotNull UUID toUniqueId) {
		return makeRequestGotMessage(getPlayer(fromUniqueId), getPlayer(toUniqueId));
	}
    
	default @NotNull String makeRequestGotMessage(@NotNull UUID fromUniqueId, @NotNull Player to) {
		return makeRequestGotMessage(getPlayer(fromUniqueId), to);
	}
    
	default @NotNull String makeRequestGotMessage(@NotNull Player from, @NotNull String toName) {
		return makeRequestGotMessage(from, getPlayer(toName));
	}

	@NotNull String makeRequestGotMessage(@NotNull Player from, @NotNull Player to);

	// - Haven't:
    
	default @NotNull String makeYouHaveNoRequestsMessage(@NotNull String toName) {
		return makeYouHaveNoRequestsMessage(getPlayer(toName));
	}

	default @NotNull String makeYouHaveNoRequestsMessage(@NotNull UUID toUniqueId) {
		return makeYouHaveNoRequestsMessage(getPlayer(toUniqueId));
	}

	@NotNull String makeYouHaveNoRequestsMessage(@NotNull Player to);

	// - Haven't From:
    
	default @NotNull String makeYouHaveNoRequestFromMessage(@NotNull String fromName, @NotNull String toName) {
		return makeYouHaveNoRequestFromMessage(getPlayer(fromName), getPlayer(toName));
	}
    
	default @NotNull String makeYouHaveNoRequestFromMessage(@NotNull String fromName, @NotNull UUID toUniqueId) {
		return makeYouHaveNoRequestFromMessage(getPlayer(fromName), getPlayer(toUniqueId));
	}
    
	default @NotNull String makeYouHaveNoRequestFromMessage(@NotNull String fromName, @NotNull Player to) {
		return makeYouHaveNoRequestFromMessage(getPlayer(fromName), to);
	}

	default @NotNull String makeYouHaveNoRequestFromMessage(@NotNull UUID fromUniqueId, @NotNull String toName) {
		return makeYouHaveNoRequestFromMessage(getPlayer(fromUniqueId), getPlayer(toName));
	}
    
	default @NotNull String makeYouHaveNoRequestFromMessage(@NotNull UUID fromUniqueId, @NotNull UUID toUniqueId) {
		return makeYouHaveNoRequestFromMessage(getPlayer(fromUniqueId), getPlayer(toUniqueId));
	}
    
	default @NotNull String makeYouHaveNoRequestFromMessage(@NotNull UUID fromUniqueId, @NotNull Player to) {
		return makeYouHaveNoRequestFromMessage(getPlayer(fromUniqueId), to);
	}
    
	default @NotNull String makeYouHaveNoRequestFromMessage(@NotNull Player from, @NotNull String toName) {
		return makeYouHaveNoRequestFromMessage(from, getPlayer(toName));
	}

	@NotNull String makeYouHaveNoRequestFromMessage(@NotNull Player from, @NotNull Player to);

	// - You Have Denied:
    
	default @NotNull String makeYouHaveDeniedRequestMessage(@NotNull String fromName, @NotNull String toName) {
		return makeYouHaveDeniedRequestMessage(getPlayer(fromName), getPlayer(toName));
	}
    
	default @NotNull String makeYouHaveDeniedRequestMessage(@NotNull String fromName, @NotNull UUID toUniqueId) {
		return makeYouHaveDeniedRequestMessage(getPlayer(fromName), getPlayer(toUniqueId));
	}
    
	default @NotNull String makeYouHaveDeniedRequestMessage(@NotNull String fromName, @NotNull Player to) {
		return makeYouHaveDeniedRequestMessage(getPlayer(fromName), to);
	}

	default @NotNull String makeYouHaveDeniedRequestMessage(@NotNull UUID fromUniqueId, @NotNull String toName) {
		return makeYouHaveDeniedRequestMessage(getPlayer(fromUniqueId), getPlayer(toName));
	}
    
	default @NotNull String makeYouHaveDeniedRequestMessage(@NotNull UUID fromUniqueId, @NotNull UUID toUniqueId) {
		return makeYouHaveDeniedRequestMessage(getPlayer(fromUniqueId), getPlayer(toUniqueId));
	}
    
	default @NotNull String makeYouHaveDeniedRequestMessage(@NotNull UUID fromUniqueId, @NotNull Player to) {
		return makeYouHaveDeniedRequestMessage(getPlayer(fromUniqueId), to);
	}
    
	default @NotNull String makeYouHaveDeniedRequestMessage(@NotNull Player from, @NotNull String toName) {
		return makeYouHaveDeniedRequestMessage(from, getPlayer(toName));
	}

	@NotNull String makeYouHaveDeniedRequestMessage(@NotNull Player from, @NotNull Player to);

	// - Your Has Been Denied:
    
	default @NotNull String makeYourRequestWasDeniedMessage(@NotNull String fromName, @NotNull String toName) {
		return makeYourRequestWasDeniedMessage(getPlayer(fromName), getPlayer(toName));
	}
    
	default @NotNull String makeYourRequestWasDeniedMessage(@NotNull String fromName, @NotNull UUID toUniqueId) {
		return makeYourRequestWasDeniedMessage(getPlayer(fromName), getPlayer(toUniqueId));
	}
    
	default @NotNull String makeYourRequestWasDeniedMessage(@NotNull String fromName, @NotNull Player to) {
		return makeYourRequestWasDeniedMessage(getPlayer(fromName), to);
	}

	default @NotNull String makeYourRequestWasDeniedMessage(@NotNull UUID fromUniqueId, @NotNull String toName) {
		return makeYourRequestWasDeniedMessage(getPlayer(fromUniqueId), getPlayer(toName));
	}
    
	default @NotNull String makeYourRequestWasDeniedMessage(@NotNull UUID fromUniqueId, @NotNull UUID toUniqueId) {
		return makeYourRequestWasDeniedMessage(getPlayer(fromUniqueId), getPlayer(toUniqueId));
	}
    
	default @NotNull String makeYourRequestWasDeniedMessage(@NotNull UUID fromUniqueId, @NotNull Player to) {
		return makeYourRequestWasDeniedMessage(getPlayer(fromUniqueId), to);
	}
    
	default @NotNull String makeYourRequestWasDeniedMessage(@NotNull Player from, @NotNull String toName) {
		return makeYourRequestWasDeniedMessage(from, getPlayer(toName));
	}

	@NotNull String makeYourRequestWasDeniedMessage(@NotNull Player from, @NotNull Player to);

	// - You Have Accepted:
    
	default @NotNull String makeYouHaveAcceptedRequestMessage(@NotNull String fromName, @NotNull String toName) {
		return makeYouHaveAcceptedRequestMessage(getPlayer(fromName), getPlayer(toName));
	}
    
	default @NotNull String makeYouHaveAcceptedRequestMessage(@NotNull String fromName, @NotNull UUID toUniqueId) {
		return makeYouHaveAcceptedRequestMessage(getPlayer(fromName), getPlayer(toUniqueId));
	}
    
	default @NotNull String makeYouHaveAcceptedRequestMessage(@NotNull String fromName, @NotNull Player to) {
		return makeYouHaveAcceptedRequestMessage(getPlayer(fromName), to);
	}

	default @NotNull String makeYouHaveAcceptedRequestMessage(@NotNull UUID fromUniqueId, @NotNull String toName) {
		return makeYouHaveAcceptedRequestMessage(getPlayer(fromUniqueId), getPlayer(toName));
	}
    
	default @NotNull String makeYouHaveAcceptedRequestMessage(@NotNull UUID fromUniqueId, @NotNull UUID toUniqueId) {
		return makeYouHaveAcceptedRequestMessage(getPlayer(fromUniqueId), getPlayer(toUniqueId));
	}
    
	default @NotNull String makeYouHaveAcceptedRequestMessage(@NotNull UUID fromUniqueId, @NotNull Player to) {
		return makeYouHaveAcceptedRequestMessage(getPlayer(fromUniqueId), to);
	}
    
	default @NotNull String makeYouHaveAcceptedRequestMessage(@NotNull Player from, @NotNull String toName) {
		return makeYouHaveAcceptedRequestMessage(from, getPlayer(toName));
	}

	@NotNull String makeYouHaveAcceptedRequestMessage(@NotNull Player from, @NotNull Player to);

	// - Your Has Been Accepted:
    
	default @NotNull String makeYourRequestWasAcceptedMessage(@NotNull String fromName, @NotNull String toName) {
		return makeYourRequestWasAcceptedMessage(getPlayer(fromName), getPlayer(toName));
	}
    
	default @NotNull String makeYourRequestWasAcceptedMessage(@NotNull String fromName, @NotNull UUID toUniqueId) {
		return makeYourRequestWasAcceptedMessage(getPlayer(fromName), getPlayer(toUniqueId));
	}
    
	default @NotNull String makeYourRequestWasAcceptedMessage(@NotNull String fromName, @NotNull Player to) {
		return makeYourRequestWasAcceptedMessage(getPlayer(fromName), to);
	}

	default @NotNull String makeYourRequestWasAcceptedMessage(@NotNull UUID fromUniqueId, @NotNull String toName) {
		return makeYourRequestWasAcceptedMessage(getPlayer(fromUniqueId), getPlayer(toName));
	}
    
	default @NotNull String makeYourRequestWasAcceptedMessage(@NotNull UUID fromUniqueId, @NotNull UUID toUniqueId) {
		return makeYourRequestWasAcceptedMessage(getPlayer(fromUniqueId), getPlayer(toUniqueId));
	}
    
	default @NotNull String makeYourRequestWasAcceptedMessage(@NotNull UUID fromUniqueId, @NotNull Player to) {
		return makeYourRequestWasAcceptedMessage(getPlayer(fromUniqueId), to);
	}
    
	default @NotNull String makeYourRequestWasAcceptedMessage(@NotNull Player from, @NotNull String toName) {
		return makeYourRequestWasAcceptedMessage(from, getPlayer(toName));
	}

	@NotNull String makeYourRequestWasAcceptedMessage(@NotNull Player from, @NotNull Player to);

	// - Your's Time Out:
    
	default @NotNull String makeYourRequestTimeOutMessage(@NotNull String fromName, @NotNull String toName) {
		return makeYourRequestTimeOutMessage(getPlayer(fromName), getPlayer(toName));
	}
    
	default @NotNull String makeYourRequestTimeOutMessage(@NotNull String fromName, @NotNull UUID toUniqueId) {
		return makeYourRequestTimeOutMessage(getPlayer(fromName), getPlayer(toUniqueId));
	}
    
	default @NotNull String makeYourRequestTimeOutMessage(@NotNull String fromName, @NotNull Player to) {
		return makeYourRequestTimeOutMessage(getPlayer(fromName), to);
	}

	default @NotNull String makeYourRequestTimeOutMessage(@NotNull UUID fromUniqueId, @NotNull String toName) {
		return makeYourRequestTimeOutMessage(getPlayer(fromUniqueId), getPlayer(toName));
	}
    
	default @NotNull String makeYourRequestTimeOutMessage(@NotNull UUID fromUniqueId, @NotNull UUID toUniqueId) {
		return makeYourRequestTimeOutMessage(getPlayer(fromUniqueId), getPlayer(toUniqueId));
	}
    
	default @NotNull String makeYourRequestTimeOutMessage(@NotNull UUID fromUniqueId, @NotNull Player to) {
		return makeYourRequestTimeOutMessage(getPlayer(fromUniqueId), to);
	}
    
	default @NotNull String makeYourRequestTimeOutMessage(@NotNull Player from, @NotNull String toName) {
		return makeYourRequestTimeOutMessage(from, getPlayer(toName));
	}

	@NotNull String makeYourRequestTimeOutMessage(@NotNull Player from, @NotNull Player to);

	// - Time Out:
    
	default @NotNull String makeRequestTimeOutMessage(@NotNull String fromName, @NotNull String toName) {
		return makeRequestTimeOutMessage(getPlayer(fromName), getPlayer(toName));
	}
    
	default @NotNull String makeRequestTimeOutMessage(@NotNull String fromName, @NotNull UUID toUniqueId) {
		return makeRequestTimeOutMessage(getPlayer(fromName), getPlayer(toUniqueId));
	}
    
	default @NotNull String makeRequestTimeOutMessage(@NotNull String fromName, @NotNull Player to) {
		return makeRequestTimeOutMessage(getPlayer(fromName), to);
	}

	default @NotNull String makeRequestTimeOutMessage(@NotNull UUID fromUniqueId, @NotNull String toName) {
		return makeRequestTimeOutMessage(getPlayer(fromUniqueId), getPlayer(toName));
	}
    
	default @NotNull String makeRequestTimeOutMessage(@NotNull UUID fromUniqueId, @NotNull UUID toUniqueId) {
		return makeRequestTimeOutMessage(getPlayer(fromUniqueId), getPlayer(toUniqueId));
	}
    
	default @NotNull String makeRequestTimeOutMessage(@NotNull UUID fromUniqueId, @NotNull Player to) {
		return makeRequestTimeOutMessage(getPlayer(fromUniqueId), to);
	}
    
	default @NotNull String makeRequestTimeOutMessage(@NotNull Player from, @NotNull String toName) {
		return makeRequestTimeOutMessage(from, getPlayer(toName));
	}

	@NotNull String makeRequestTimeOutMessage(@NotNull Player from, @NotNull Player to);

	// - Specify:

	default @NotNull String makeSpecifyRequestMessage(@NotNull String toName, @NotNull Set<Player> requests) {
		return makeSpecifyRequestMessage(getPlayer(toName), requests);
	}

	default @NotNull String makeSpecifyRequestMessage(@NotNull UUID toUniqueId, @NotNull Set<Player> requests) {
		return makeSpecifyRequestMessage(getPlayer(toUniqueId), requests);
	}

	@NotNull String makeSpecifyRequestMessage(@NotNull Player to, @NotNull Set<Player> requests);

	// - List:

	// -- You Have No Got:
    
	default @NotNull String makeYouHaveNoGotRequestsMessage(@NotNull String toName) {
		return makeYouHaveNoGotRequestsMessage(getPlayer(toName));
	}

	default @NotNull String makeYouHaveNoGotRequestsMessage(@NotNull UUID toUniqueId) {
		return makeYouHaveNoGotRequestsMessage(getPlayer(toUniqueId));
	}

	@NotNull String makeYouHaveNoGotRequestsMessage(@NotNull Player to);

	// -- Has No Got:
    
	default @NotNull String makeHasNoGotRequestsMessage(@NotNull String toName) {
		return makeHasNoGotRequestsMessage(getPlayer(toName));
	}

	default @NotNull String makeHasNoGotRequestsMessage(@NotNull UUID toUniqueId) {
		return makeHasNoGotRequestsMessage(getPlayer(toUniqueId));
	}

	@NotNull String makeHasNoGotRequestsMessage(@NotNull Player to);

	// -- Your Got Header:
    
	default @NotNull String makeYourGotRequestsHeaderMessage(@NotNull String toName, @NotNull Set<Player> requests) {
		return makeYourGotRequestsHeaderMessage(getPlayer(toName), requests);
	}

	default @NotNull String makeYourGotRequestsHeaderMessage(@NotNull UUID toUniqueId, @NotNull Set<Player> requests) {
		return makeYourGotRequestsHeaderMessage(getPlayer(toUniqueId), requests);
	}

	@NotNull String makeYourGotRequestsHeaderMessage(@NotNull Player to, @NotNull Set<Player> requests);

	// -- Got Header:

	default @NotNull String makeGotRequestsHeaderMessage(@NotNull String toName, @NotNull Set<Player> requests) {
		return makeGotRequestsHeaderMessage(getPlayer(toName), requests);
	}

	default @NotNull String makeGotRequestsHeaderMessage(@NotNull UUID toUniqueId, @NotNull Set<Player> requests) {
		return makeGotRequestsHeaderMessage(getPlayer(toUniqueId), requests);
	}

	@NotNull String makeGotRequestsHeaderMessage(@NotNull Player to, @NotNull Set<Player> requests);

	// -- Got Item:
    
	default @NotNull String makeGotRequestsItemMessage(@NotNull String fromName, @NotNull String toName) {
		return makeGotRequestsItemMessage(getPlayer(fromName), getPlayer(toName));
	}
    
	default @NotNull String makeGotRequestsItemMessage(@NotNull String fromName, @NotNull UUID toUniqueId) {
		return makeGotRequestsItemMessage(getPlayer(fromName), getPlayer(toUniqueId));
	}
    
	default @NotNull String makeGotRequestsItemMessage(@NotNull String fromName, @NotNull Player to) {
		return makeGotRequestsItemMessage(getPlayer(fromName), to);
	}

	default @NotNull String makeGotRequestsItemMessage(@NotNull UUID fromUniqueId, @NotNull String toName) {
		return makeGotRequestsItemMessage(getPlayer(fromUniqueId), getPlayer(toName));
	}
    
	default @NotNull String makeGotRequestsItemMessage(@NotNull UUID fromUniqueId, @NotNull UUID toUniqueId) {
		return makeGotRequestsItemMessage(getPlayer(fromUniqueId), getPlayer(toUniqueId));
	}
    
	default @NotNull String makeGotRequestsItemMessage(@NotNull UUID fromUniqueId, @NotNull Player to) {
		return makeGotRequestsItemMessage(getPlayer(fromUniqueId), to);
	}
    
	default @NotNull String makeGotRequestsItemMessage(@NotNull Player from, @NotNull String toName) {
		return makeGotRequestsItemMessage(from, getPlayer(toName));
	}

	@NotNull String makeGotRequestsItemMessage(@NotNull Player from, @NotNull Player to);

	// -- You Have No Sent:
    
	default @NotNull String makeYouHaveNoSentRequestsMessage(@NotNull String toName) {
		return makeYouHaveNoSentRequestsMessage(getPlayer(toName));
	}

	default @NotNull String makeYouHaveNoSentRequestsMessage(@NotNull UUID toUniqueId) {
		return makeYouHaveNoSentRequestsMessage(getPlayer(toUniqueId));
	}
    
	@NotNull String makeYouHaveNoSentRequestsMessage(@NotNull Player to);

	// -- Has No Sent:
    
	default @NotNull String makeHasNoSentRequestsMessage(@NotNull String toName) {
		return makeHasNoSentRequestsMessage(getPlayer(toName));
	}

	default @NotNull String makeHasNoSentRequestsMessage(@NotNull UUID toUniqueId) {
		return makeHasNoSentRequestsMessage(getPlayer(toUniqueId));
	}

	@NotNull String makeHasNoSentRequestsMessage(@NotNull Player to);

	// -- Your Sent Header:
    
	default @NotNull String makeYourSentRequestsHeaderMessage(@NotNull String toName, @NotNull Set<Player> requests) {
		return makeYourSentRequestsHeaderMessage(getPlayer(toName), requests);
	}

	default @NotNull String makeYourSentRequestsHeaderMessage(@NotNull UUID toUniqueId, @NotNull Set<Player> requests) {
		return makeYourSentRequestsHeaderMessage(getPlayer(toUniqueId), requests);
	}

	@NotNull String makeYourSentRequestsHeaderMessage(@NotNull Player from, @NotNull Set<Player> requests);

	// -- Sent Header:

	default @NotNull String makeSentRequestsHeaderMessage(@NotNull String toName, @NotNull Set<Player> requests) {
		return makeSentRequestsHeaderMessage(getPlayer(toName), requests);
	}

	default @NotNull String makeSentRequestsHeaderMessage(@NotNull UUID toUniqueId, @NotNull Set<Player> requests) {
		return makeSentRequestsHeaderMessage(getPlayer(toUniqueId), requests);
	}

	@NotNull String makeSentRequestsHeaderMessage(@NotNull Player from, @NotNull Set<Player> requests);

	// -- Sent Item:
    
	default @NotNull String makeSentRequestsItemMessage(@NotNull String fromName, @NotNull String toName) {
		return makeSentRequestsItemMessage(getPlayer(fromName), getPlayer(toName));
	}
    
	default @NotNull String makeSentRequestsItemMessage(@NotNull String fromName, @NotNull UUID toUniqueId) {
		return makeSentRequestsItemMessage(getPlayer(fromName), getPlayer(toUniqueId));
	}
    
	default @NotNull String makeSentRequestsItemMessage(@NotNull String fromName, @NotNull Player to) {
		return makeSentRequestsItemMessage(getPlayer(fromName), to);
	}

	default @NotNull String makeSentRequestsItemMessage(@NotNull UUID fromUniqueId, @NotNull String toName) {
		return makeSentRequestsItemMessage(getPlayer(fromUniqueId), getPlayer(toName));
	}
    
	default @NotNull String makeSentRequestsItemMessage(@NotNull UUID fromUniqueId, @NotNull UUID toUniqueId) {
		return makeSentRequestsItemMessage(getPlayer(fromUniqueId), getPlayer(toUniqueId));
	}
    
	default @NotNull String makeSentRequestsItemMessage(@NotNull UUID fromUniqueId, @NotNull Player to) {
		return makeSentRequestsItemMessage(getPlayer(fromUniqueId), to);
	}
    
	default @NotNull String makeSentRequestsItemMessage(@NotNull Player from, @NotNull String toName) {
		return makeSentRequestsItemMessage(from, getPlayer(toName));
	}

	@NotNull String makeSentRequestsItemMessage(@NotNull Player from, @NotNull Player to);

    // Teleport:

	// - Cannot Be Teleported:
    
	default @NotNull String makeCannotBeTeleportedNowMessage(@NotNull String fromName, @NotNull String toName) {
		return makeCannotBeTeleportedNowMessage(getPlayer(fromName), getPlayer(toName));
	}
    
	default @NotNull String makeCannotBeTeleportedNowMessage(@NotNull String fromName, @NotNull UUID toUniqueId) {
		return makeCannotBeTeleportedNowMessage(getPlayer(fromName), getPlayer(toUniqueId));
	}
    
	default @NotNull String makeCannotBeTeleportedNowMessage(@NotNull String fromName, @NotNull Player to) {
		return makeCannotBeTeleportedNowMessage(getPlayer(fromName), to);
	}

	default @NotNull String makeCannotBeTeleportedNowMessage(@NotNull UUID fromUniqueId, @NotNull String toName) {
		return makeCannotBeTeleportedNowMessage(getPlayer(fromUniqueId), getPlayer(toName));
	}
    
	default @NotNull String makeCannotBeTeleportedNowMessage(@NotNull UUID fromUniqueId, @NotNull UUID toUniqueId) {
		return makeCannotBeTeleportedNowMessage(getPlayer(fromUniqueId), getPlayer(toUniqueId));
	}
    
	default @NotNull String makeCannotBeTeleportedNowMessage(@NotNull UUID fromUniqueId, @NotNull Player to) {
		return makeCannotBeTeleportedNowMessage(getPlayer(fromUniqueId), to);
	}
    
	default @NotNull String makeCannotBeTeleportedNowMessage(@NotNull Player from, @NotNull String toName) {
		return makeCannotBeTeleportedNowMessage(from, getPlayer(toName));
	}

	@NotNull String makeCannotBeTeleportedNowMessage(@NotNull Player from, @NotNull Player to);

	// - To Location:
    
	default @NotNull String makeTeleportingToLocationMessage(@NotNull String fromName) {
		return makeTeleportingToLocationMessage(getPlayer(fromName));
	}

	default @NotNull String makeTeleportingToLocationMessage(@NotNull UUID fromUniqueId) {
		return makeTeleportingToLocationMessage(getPlayer(fromUniqueId));
	}
    
	@NotNull String makeTeleportingToLocationMessage(@NotNull Player from);

	// - To Named Location:
    
	default @NotNull String makeTeleportingToNamedLocationMessage(@NotNull String fromName, @NotNull String to) {
		return makeTeleportingToNamedLocationMessage(getPlayer(fromName), to);
	}

	default @NotNull String makeTeleportingToNamedLocationMessage(@NotNull UUID fromUniqueId, @NotNull String to) {
		return makeTeleportingToNamedLocationMessage(getPlayer(fromUniqueId), to);
	}

	@NotNull String makeTeleportingToNamedLocationMessage(@NotNull Player from, @NotNull String to);

	// - To Player:
    
	default @NotNull String makeTeleportingToPlayerMessage(@NotNull String fromName, @NotNull String toName) {
		return makeTeleportingToPlayerMessage(getPlayer(fromName), getPlayer(toName));
	}
    
	default @NotNull String makeTeleportingToPlayerMessage(@NotNull String fromName, @NotNull UUID toUniqueId) {
		return makeTeleportingToPlayerMessage(getPlayer(fromName), getPlayer(toUniqueId));
	}
    
	default @NotNull String makeTeleportingToPlayerMessage(@NotNull String fromName, @NotNull Player to) {
		return makeTeleportingToPlayerMessage(getPlayer(fromName), to);
	}

	default @NotNull String makeTeleportingToPlayerMessage(@NotNull UUID fromUniqueId, @NotNull String toName) {
		return makeTeleportingToPlayerMessage(getPlayer(fromUniqueId), getPlayer(toName));
	}
    
	default @NotNull String makeTeleportingToPlayerMessage(@NotNull UUID fromUniqueId, @NotNull UUID toUniqueId) {
		return makeTeleportingToPlayerMessage(getPlayer(fromUniqueId), getPlayer(toUniqueId));
	}
    
	default @NotNull String makeTeleportingToPlayerMessage(@NotNull UUID fromUniqueId, @NotNull Player to) {
		return makeTeleportingToPlayerMessage(getPlayer(fromUniqueId), to);
	}
    
	default @NotNull String makeTeleportingToPlayerMessage(@NotNull Player from, @NotNull String toName) {
		return makeTeleportingToPlayerMessage(from, getPlayer(toName));
	}

	@NotNull String makeTeleportingToPlayerMessage(@NotNull Player from, @NotNull Player to);

	// - To You:
    
	default @NotNull String makeTeleportingToYouMessage(@NotNull String fromName, @NotNull String toName) {
		return makeTeleportingToYouMessage(getPlayer(fromName), getPlayer(toName));
	}
    
	default @NotNull String makeTeleportingToYouMessage(@NotNull String fromName, @NotNull UUID toUniqueId) {
		return makeTeleportingToYouMessage(getPlayer(fromName), getPlayer(toUniqueId));
	}
    
	default @NotNull String makeTeleportingToYouMessage(@NotNull String fromName, @NotNull Player to) {
		return makeTeleportingToYouMessage(getPlayer(fromName), to);
	}

	default @NotNull String makeTeleportingToYouMessage(@NotNull UUID fromUniqueId, @NotNull String toName) {
		return makeTeleportingToYouMessage(getPlayer(fromUniqueId), getPlayer(toName));
	}
    
	default @NotNull String makeTeleportingToYouMessage(@NotNull UUID fromUniqueId, @NotNull UUID toUniqueId) {
		return makeTeleportingToYouMessage(getPlayer(fromUniqueId), getPlayer(toUniqueId));
	}
    
	default @NotNull String makeTeleportingToYouMessage(@NotNull UUID fromUniqueId, @NotNull Player to) {
		return makeTeleportingToYouMessage(getPlayer(fromUniqueId), to);
	}
    
	default @NotNull String makeTeleportingToYouMessage(@NotNull Player from, @NotNull String toName) {
		return makeTeleportingToYouMessage(from, getPlayer(toName));
	}

	@NotNull String makeTeleportingToYouMessage(@NotNull Player from, @NotNull Player to);

	// - Count Down:
    
	@NotNull String makeCountDownMessage(@NotNull Time left, @NotNull Time delay);

	// - Teleported:
    
	default @NotNull String makeTeleportedMessage(@NotNull String fromName) {
		return makeTeleportedMessage(getPlayer(fromName));
	}

	default @NotNull String makeTeleportedMessage(@NotNull UUID fromUniqueId) {
		return makeTeleportedMessage(getPlayer(fromUniqueId));
	}
    
	@NotNull String makeTeleportedMessage(@NotNull Player from);

    // Error:

	// - Cannot Send To Your Self:
    
	default @NotNull String makeCannotSendRequestToYourSelfMessage(@NotNull String fromName) {
		return makeCannotSendRequestToYourSelfMessage(getPlayer(fromName));
	}

	default @NotNull String makeCannotSendRequestToYourSelfMessage(@NotNull UUID fromUniqueId) {
		return makeCannotSendRequestToYourSelfMessage(getPlayer(fromUniqueId));
	}
    
	@NotNull String makeCannotSendRequestToYourSelfMessage(@NotNull Player from);

	// - Wait Until Cool Down Is Over:
    
	@NotNull String makeWaitUntilCoolDownOverMessage(@NotNull Player from, @NotNull Time left, @NotNull Time delay);

	// - Missing Bed:
    
	default @NotNull String makeMissingBedMessage(@NotNull String fromName) {
		return makeMissingBedMessage(getPlayer(fromName));
	}

	default @NotNull String makeMissingBedMessage(@NotNull UUID fromUniqueId) {
		return makeMissingBedMessage(getPlayer(fromUniqueId));
	}
    
	@NotNull String makeMissingBedMessage(@NotNull Player from);

	// - Already:
    
	default @NotNull String makeAlreadyTeleportingMessage(@NotNull String fromName) {
		return makeAlreadyTeleportingMessage(getPlayer(fromName));
	}

	default @NotNull String makeAlreadyTeleportingMessage(@NotNull UUID fromUniqueId) {
		return makeAlreadyTeleportingMessage(getPlayer(fromUniqueId));
	}
    
	@NotNull String makeAlreadyTeleportingMessage(@NotNull Player from);

	// - Player Not Found:

	@Override
	@NotNull String makePlayerNotFoundMessage(@NotNull String player);
	
	// - World Not Found:

	@Override
	@NotNull String makeWorldNotFoundMessage(@NotNull String world);

	// - You Have No Permission:
	
	@Override
	@NotNull String makeMissingPermissionMessage(@NotNull CommandSender sender, @NotNull String permission);

	// - Cannot Run:
	
	@Override
	@NotNull String makeInvalidClassMessage(@NotNull Set<Class<?>> classes);
}
