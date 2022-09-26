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

import static space.moontalk.mc.cpspeed.Utility.*;

public interface MessageProvider extends SecondsMessageProvider,
										 PlayerNotFoundMessageProvider,
										 WorldNotFoundMessageProvider,
										 MissingPermissionMessageProvider,
										 InvalidClassMessageProvider {
    // Request:

	// - Already Sent:

	default @NotNull String makeAlreadySentRequestMessage(@NotNull String fromName, @NotNull String toName) {
		val from = getPlayer(fromName);
		val to   = getPlayer(toName);
		return makeAlreadySentRequestMessage(from, to);
	}
    
	default @NotNull String makeAlreadySentRequestMessage(@NotNull String fromName, @NotNull UUID toUniqueId) {
		val from = getPlayer(fromName);
		val to   = getPlayer(toUniqueId);
		return makeAlreadySentRequestMessage(from, to);
	}
    
	default @NotNull String makeAlreadySentRequestMessage(@NotNull String fromName, @NotNull Player to) {
		val from = getPlayer(fromName);
		return makeAlreadySentRequestMessage(from, to);
	}

	default @NotNull String makeAlreadySentRequestMessage(@NotNull UUID fromUniqueId, @NotNull String toName) {
		val from = getPlayer(fromUniqueId);
		val to   = getPlayer(toName);
		return makeAlreadySentRequestMessage(from, to);
	}
    
	default @NotNull String makeAlreadySentRequestMessage(@NotNull UUID fromUniqueId, @NotNull UUID toUniqueId) {
		val from = getPlayer(fromUniqueId);
		val to   = getPlayer(toUniqueId);
		return makeAlreadySentRequestMessage(from, to);
	}
    
	default @NotNull String makeAlreadySentRequestMessage(@NotNull UUID fromUniqueId, @NotNull Player to) {
		val from = getPlayer(fromUniqueId);
		return makeAlreadySentRequestMessage(from, to);
	}
    
	default @NotNull String makeAlreadySentRequestMessage(@NotNull Player from, @NotNull String toName) {
		val to = getPlayer(toName);
		return makeAlreadySentRequestMessage(from, to);
	}

	default @NotNull String makeAlreadySentRequestMessage(@NotNull Player from, @NotNull UUID toUniqueId) {
		val to = getPlayer(toUniqueId);
		return makeAlreadySentRequestMessage(from, to);
	}

	@NotNull String makeAlreadySentRequestMessage(@NotNull Player from, @NotNull Player to);

	// - Sent:
    
	default @NotNull String makeRequestSentMessage(@NotNull String fromName, @NotNull String toName) {
		val from = getPlayer(fromName);
		val to   = getPlayer(toName);
		return makeRequestSentMessage(from, to);
	}
    
	default @NotNull String makeRequestSentMessage(@NotNull String fromName, @NotNull UUID toUniqueId) {
		val from = getPlayer(fromName);
		val to   = getPlayer(toUniqueId);
		return makeRequestSentMessage(from, to);
	}
    
	default @NotNull String makeRequestSentMessage(@NotNull String fromName, @NotNull Player to) {
		val from = getPlayer(fromName);
		return makeRequestSentMessage(from, to);
	}

	default @NotNull String makeRequestSentMessage(@NotNull UUID fromUniqueId, @NotNull String toName) {
		val from = getPlayer(fromUniqueId);
		val to   = getPlayer(toName);
		return makeRequestSentMessage(from, to);
	}
    
	default @NotNull String makeRequestSentMessage(@NotNull UUID fromUniqueId, @NotNull UUID toUniqueId) {
		val from = getPlayer(fromUniqueId);
		val to   = getPlayer(toUniqueId);
		return makeRequestSentMessage(from, to);
	}
    
	default @NotNull String makeRequestSentMessage(@NotNull UUID fromUniqueId, @NotNull Player to) {
		val from = getPlayer(fromUniqueId);
		return makeRequestSentMessage(from, to);
	}
    
	default @NotNull String makeRequestSentMessage(@NotNull Player from, @NotNull String toName) {
		val to = getPlayer(toName);
		return makeRequestSentMessage(from, to);
	}

	@NotNull String makeRequestSentMessage(@NotNull Player from, @NotNull Player to);

	// - Got:
    
	default @NotNull String makeRequestGotMessage(@NotNull String fromName, @NotNull String toName) {
		val from = getPlayer(fromName);
		val to   = getPlayer(toName);
		return makeRequestGotMessage(from, to);
	}
    
	default @NotNull String makeRequestGotMessage(@NotNull String fromName, @NotNull UUID toUniqueId) {
		val from = getPlayer(fromName);
		val to   = getPlayer(toUniqueId);
		return makeRequestGotMessage(from, to);
	}
    
	default @NotNull String makeRequestGotMessage(@NotNull String fromName, @NotNull Player to) {
		val from = getPlayer(fromName);
		return makeRequestGotMessage(from, to);
	}

	default @NotNull String makeRequestGotMessage(@NotNull UUID fromUniqueId, @NotNull String toName) {
		val from = getPlayer(fromUniqueId);
		val to   = getPlayer(toName);
		return makeRequestGotMessage(from, to);
	}
    
	default @NotNull String makeRequestGotMessage(@NotNull UUID fromUniqueId, @NotNull UUID toUniqueId) {
		val from = getPlayer(fromUniqueId);
		val to   = getPlayer(toUniqueId);
		return makeRequestGotMessage(from, to);
	}
    
	default @NotNull String makeRequestGotMessage(@NotNull UUID fromUniqueId, @NotNull Player to) {
		val from = getPlayer(fromUniqueId);
		return makeRequestGotMessage(from, to);
	}
    
	default @NotNull String makeRequestGotMessage(@NotNull Player from, @NotNull String toName) {
		val to = getPlayer(toName);
		return makeRequestGotMessage(from, to);
	}

	@NotNull String makeRequestGotMessage(@NotNull Player from, @NotNull Player to);

	// - Haven't:
    
	default @NotNull String makeYouHaveNoRequestsMessage(@NotNull String toName) {
		val to = getPlayer(toName);
		return makeYouHaveNoRequestsMessage(to);
	}

	default @NotNull String makeYouHaveNoRequestsMessage(@NotNull UUID toUniqueId) {
		val to = getPlayer(toUniqueId);
		return makeYouHaveNoRequestsMessage(to);
	}

	@NotNull String makeYouHaveNoRequestsMessage(@NotNull Player to);

	// - Haven't From:
    
	default @NotNull String makeYouHaveNoRequestFromMessage(@NotNull String fromName, @NotNull String toName) {
		val from = getPlayer(fromName);
		val to   = getPlayer(toName);
		return makeYouHaveNoRequestFromMessage(from, to);
	}
    
	default @NotNull String makeYouHaveNoRequestFromMessage(@NotNull String fromName, @NotNull UUID toUniqueId) {
		val from = getPlayer(fromName);
		val to   = getPlayer(toUniqueId);
		return makeYouHaveNoRequestFromMessage(from, to);
	}
    
	default @NotNull String makeYouHaveNoRequestFromMessage(@NotNull String fromName, @NotNull Player to) {
		val from = getPlayer(fromName);
		return makeYouHaveNoRequestFromMessage(from, to);
	}

	default @NotNull String makeYouHaveNoRequestFromMessage(@NotNull UUID fromUniqueId, @NotNull String toName) {
		val from = getPlayer(fromUniqueId);
		val to   = getPlayer(toName);
		return makeYouHaveNoRequestFromMessage(from, to);
	}
    
	default @NotNull String makeYouHaveNoRequestFromMessage(@NotNull UUID fromUniqueId, @NotNull UUID toUniqueId) {
		val from = getPlayer(fromUniqueId);
		val to   = getPlayer(toUniqueId);
		return makeYouHaveNoRequestFromMessage(from, to);
	}
    
	default @NotNull String makeYouHaveNoRequestFromMessage(@NotNull UUID fromUniqueId, @NotNull Player to) {
		val from = getPlayer(fromUniqueId);
		return makeYouHaveNoRequestFromMessage(from, to);
	}
    
	default @NotNull String makeYouHaveNoRequestFromMessage(@NotNull Player from, @NotNull String toName) {
		val to = getPlayer(toName);
		return makeYouHaveNoRequestFromMessage(from, to);
	}

	@NotNull String makeYouHaveNoRequestFromMessage(@NotNull Player from, @NotNull Player to);

	// - You Have Denied:
    
	default @NotNull String makeYouHaveDeniedRequestMessage(@NotNull String fromName, @NotNull String toName) {
		val from = getPlayer(fromName);
		val to   = getPlayer(toName);
		return makeYouHaveDeniedRequestMessage(from, to);
	}
    
	default @NotNull String makeYouHaveDeniedRequestMessage(@NotNull String fromName, @NotNull UUID toUniqueId) {
		val from = getPlayer(fromName);
		val to   = getPlayer(toUniqueId);
		return makeYouHaveDeniedRequestMessage(from, to);
	}
    
	default @NotNull String makeYouHaveDeniedRequestMessage(@NotNull String fromName, @NotNull Player to) {
		val from = getPlayer(fromName);
		return makeYouHaveDeniedRequestMessage(from, to);
	}

	default @NotNull String makeYouHaveDeniedRequestMessage(@NotNull UUID fromUniqueId, @NotNull String toName) {
		val from = getPlayer(fromUniqueId);
		val to   = getPlayer(toName);
		return makeYouHaveDeniedRequestMessage(from, to);
	}
    
	default @NotNull String makeYouHaveDeniedRequestMessage(@NotNull UUID fromUniqueId, @NotNull UUID toUniqueId) {
		val from = getPlayer(fromUniqueId);
		val to   = getPlayer(toUniqueId);
		return makeYouHaveDeniedRequestMessage(from, to);
	}
    
	default @NotNull String makeYouHaveDeniedRequestMessage(@NotNull UUID fromUniqueId, @NotNull Player to) {
		val from = getPlayer(fromUniqueId);
		return makeYouHaveDeniedRequestMessage(from, to);
	}
    
	default @NotNull String makeYouHaveDeniedRequestMessage(@NotNull Player from, @NotNull String toName) {
		val to = getPlayer(toName);
		return makeYouHaveDeniedRequestMessage(from, to);
	}

	@NotNull String makeYouHaveDeniedRequestMessage(@NotNull Player from, @NotNull Player to);

	// - Your Has Been Denied:
    
	default @NotNull String makeYourRequestWasDeniedMessage(@NotNull String fromName, @NotNull String toName) {
		val from = getPlayer(fromName);
		val to   = getPlayer(toName);
		return makeYourRequestWasDeniedMessage(from, to);
	}
    
	default @NotNull String makeYourRequestWasDeniedMessage(@NotNull String fromName, @NotNull UUID toUniqueId) {
		val from = getPlayer(fromName);
		val to   = getPlayer(toUniqueId);
		return makeYourRequestWasDeniedMessage(from, to);
	}
    
	default @NotNull String makeYourRequestWasDeniedMessage(@NotNull String fromName, @NotNull Player to) {
		val from = getPlayer(fromName);
		return makeYourRequestWasDeniedMessage(from, to);
	}

	default @NotNull String makeYourRequestWasDeniedMessage(@NotNull UUID fromUniqueId, @NotNull String toName) {
		val from = getPlayer(fromUniqueId);
		val to   = getPlayer(toName);
		return makeYourRequestWasDeniedMessage(from, to);
	}
    
	default @NotNull String makeYourRequestWasDeniedMessage(@NotNull UUID fromUniqueId, @NotNull UUID toUniqueId) {
		val from = getPlayer(fromUniqueId);
		val to   = getPlayer(toUniqueId);
		return makeYourRequestWasDeniedMessage(from, to);
	}
    
	default @NotNull String makeYourRequestWasDeniedMessage(@NotNull UUID fromUniqueId, @NotNull Player to) {
		val from = getPlayer(fromUniqueId);
		return makeYourRequestWasDeniedMessage(from, to);
	}
    
	default @NotNull String makeYourRequestWasDeniedMessage(@NotNull Player from, @NotNull String toName) {
		val to = getPlayer(toName);
		return makeYourRequestWasDeniedMessage(from, to);
	}

	@NotNull String makeYourRequestWasDeniedMessage(@NotNull Player from, @NotNull Player to);

	// - You Have Accepted:
    
	default @NotNull String makeYouHaveAcceptedRequestMessage(@NotNull String fromName, @NotNull String toName) {
		val from = getPlayer(fromName);
		val to   = getPlayer(toName);
		return makeYouHaveAcceptedRequestMessage(from, to);
	}
    
	default @NotNull String makeYouHaveAcceptedRequestMessage(@NotNull String fromName, @NotNull UUID toUniqueId) {
		val from = getPlayer(fromName);
		val to   = getPlayer(toUniqueId);
		return makeYouHaveAcceptedRequestMessage(from, to);
	}
    
	default @NotNull String makeYouHaveAcceptedRequestMessage(@NotNull String fromName, @NotNull Player to) {
		val from = getPlayer(fromName);
		return makeYouHaveAcceptedRequestMessage(from, to);
	}

	default @NotNull String makeYouHaveAcceptedRequestMessage(@NotNull UUID fromUniqueId, @NotNull String toName) {
		val from = getPlayer(fromUniqueId);
		val to   = getPlayer(toName);
		return makeYouHaveAcceptedRequestMessage(from, to);
	}
    
	default @NotNull String makeYouHaveAcceptedRequestMessage(@NotNull UUID fromUniqueId, @NotNull UUID toUniqueId) {
		val from = getPlayer(fromUniqueId);
		val to   = getPlayer(toUniqueId);
		return makeYouHaveAcceptedRequestMessage(from, to);
	}
    
	default @NotNull String makeYouHaveAcceptedRequestMessage(@NotNull UUID fromUniqueId, @NotNull Player to) {
		val from = getPlayer(fromUniqueId);
		return makeYouHaveAcceptedRequestMessage(from, to);
	}
    
	default @NotNull String makeYouHaveAcceptedRequestMessage(@NotNull Player from, @NotNull String toName) {
		val to = getPlayer(toName);
		return makeYouHaveAcceptedRequestMessage(from, to);
	}

	@NotNull String makeYouHaveAcceptedRequestMessage(@NotNull Player from, @NotNull Player to);

	// - Your Has Been Accepted:
    
	default @NotNull String makeYourRequestWasAcceptedMessage(@NotNull String fromName, @NotNull String toName) {
		val from = getPlayer(fromName);
		val to   = getPlayer(toName);
		return makeYourRequestWasAcceptedMessage(from, to);
	}
    
	default @NotNull String makeYourRequestWasAcceptedMessage(@NotNull String fromName, @NotNull UUID toUniqueId) {
		val from = getPlayer(fromName);
		val to   = getPlayer(toUniqueId);
		return makeYourRequestWasAcceptedMessage(from, to);
	}
    
	default @NotNull String makeYourRequestWasAcceptedMessage(@NotNull String fromName, @NotNull Player to) {
		val from = getPlayer(fromName);
		return makeYourRequestWasAcceptedMessage(from, to);
	}

	default @NotNull String makeYourRequestWasAcceptedMessage(@NotNull UUID fromUniqueId, @NotNull String toName) {
		val from = getPlayer(fromUniqueId);
		val to   = getPlayer(toName);
		return makeYourRequestWasAcceptedMessage(from, to);
	}
    
	default @NotNull String makeYourRequestWasAcceptedMessage(@NotNull UUID fromUniqueId, @NotNull UUID toUniqueId) {
		val from = getPlayer(fromUniqueId);
		val to   = getPlayer(toUniqueId);
		return makeYourRequestWasAcceptedMessage(from, to);
	}
    
	default @NotNull String makeYourRequestWasAcceptedMessage(@NotNull UUID fromUniqueId, @NotNull Player to) {
		val from = getPlayer(fromUniqueId);
		return makeYourRequestWasAcceptedMessage(from, to);
	}
    
	default @NotNull String makeYourRequestWasAcceptedMessage(@NotNull Player from, @NotNull String toName) {
		val to = getPlayer(toName);
		return makeYourRequestWasAcceptedMessage(from, to);
	}

	@NotNull String makeYourRequestWasAcceptedMessage(@NotNull Player from, @NotNull Player to);

	// - Your's Time Out:
    
	default @NotNull String makeYourRequestTimeOutMessage(@NotNull String fromName, @NotNull String toName) {
		val from = getPlayer(fromName);
		val to   = getPlayer(toName);
		return makeYourRequestTimeOutMessage(from, to);
	}
    
	default @NotNull String makeYourRequestTimeOutMessage(@NotNull String fromName, @NotNull UUID toUniqueId) {
		val from = getPlayer(fromName);
		val to   = getPlayer(toUniqueId);
		return makeYourRequestTimeOutMessage(from, to);
	}
    
	default @NotNull String makeYourRequestTimeOutMessage(@NotNull String fromName, @NotNull Player to) {
		val from = getPlayer(fromName);
		return makeYourRequestTimeOutMessage(from, to);
	}

	default @NotNull String makeYourRequestTimeOutMessage(@NotNull UUID fromUniqueId, @NotNull String toName) {
		val from = getPlayer(fromUniqueId);
		val to   = getPlayer(toName);
		return makeYourRequestTimeOutMessage(from, to);
	}
    
	default @NotNull String makeYourRequestTimeOutMessage(@NotNull UUID fromUniqueId, @NotNull UUID toUniqueId) {
		val from = getPlayer(fromUniqueId);
		val to   = getPlayer(toUniqueId);
		return makeYourRequestTimeOutMessage(from, to);
	}
    
	default @NotNull String makeYourRequestTimeOutMessage(@NotNull UUID fromUniqueId, @NotNull Player to) {
		val from = getPlayer(fromUniqueId);
		return makeYourRequestTimeOutMessage(from, to);
	}
    
	default @NotNull String makeYourRequestTimeOutMessage(@NotNull Player from, @NotNull String toName) {
		val to = getPlayer(toName);
		return makeYourRequestTimeOutMessage(from, to);
	}

	@NotNull String makeYourRequestTimeOutMessage(@NotNull Player from, @NotNull Player to);

	// - Time Out:
    
	default @NotNull String makeRequestTimeOutMessage(@NotNull String fromName, @NotNull String toName) {
		val from = getPlayer(fromName);
		val to   = getPlayer(toName);
		return makeRequestTimeOutMessage(from, to);
	}
    
	default @NotNull String makeRequestTimeOutMessage(@NotNull String fromName, @NotNull UUID toUniqueId) {
		val from = getPlayer(fromName);
		val to   = getPlayer(toUniqueId);
		return makeRequestTimeOutMessage(from, to);
	}
    
	default @NotNull String makeRequestTimeOutMessage(@NotNull String fromName, @NotNull Player to) {
		val from = getPlayer(fromName);
		return makeRequestTimeOutMessage(from, to);
	}

	default @NotNull String makeRequestTimeOutMessage(@NotNull UUID fromUniqueId, @NotNull String toName) {
		val from = getPlayer(fromUniqueId);
		val to   = getPlayer(toName);
		return makeRequestTimeOutMessage(from, to);
	}
    
	default @NotNull String makeRequestTimeOutMessage(@NotNull UUID fromUniqueId, @NotNull UUID toUniqueId) {
		val from = getPlayer(fromUniqueId);
		val to   = getPlayer(toUniqueId);
		return makeRequestTimeOutMessage(from, to);
	}
    
	default @NotNull String makeRequestTimeOutMessage(@NotNull UUID fromUniqueId, @NotNull Player to) {
		val from = getPlayer(fromUniqueId);
		return makeRequestTimeOutMessage(from, to);
	}
    
	default @NotNull String makeRequestTimeOutMessage(@NotNull Player from, @NotNull String toName) {
		val to = getPlayer(toName);
		return makeRequestTimeOutMessage(from, to);
	}

	@NotNull String makeRequestTimeOutMessage(@NotNull Player from, @NotNull Player to);

	// - Specify:

	default @NotNull String makeSpecifyRequestMessage(@NotNull String toName, @NotNull Set<Player> requests) {
		val to = getPlayer(toName);
		return makeSpecifyRequestMessage(to, requests);
	}

	default @NotNull String makeSpecifyRequestMessage(@NotNull UUID toUniqueId, @NotNull Set<Player> requests) {
		val to = getPlayer(toUniqueId);
		return makeSpecifyRequestMessage(to, requests);
	}

	@NotNull String makeSpecifyRequestMessage(@NotNull Player to, @NotNull Set<Player> requests);

	// - List:

	// -- You Have No Got:
    
	default @NotNull String makeYouHaveNoGotRequestsMessage(@NotNull String toName) {
		val to = getPlayer(toName);
		return makeYouHaveNoGotRequestsMessage(to);
	}

	default @NotNull String makeYouHaveNoGotRequestsMessage(@NotNull UUID toUniqueId) {
		val to = getPlayer(toUniqueId);
		return makeYouHaveNoGotRequestsMessage(to);
	}

	@NotNull String makeYouHaveNoGotRequestsMessage(@NotNull Player to);

	// -- Has No Got:
    
	default @NotNull String makeHasNoGotRequestsMessage(@NotNull String toName) {
		val to = getPlayer(toName);
		return makeHasNoGotRequestsMessage(to);
	}

	default @NotNull String makeHasNoGotRequestsMessage(@NotNull UUID toUniqueId) {
		val to = getPlayer(toUniqueId);
		return makeHasNoGotRequestsMessage(to);
	}

	@NotNull String makeHasNoGotRequestsMessage(@NotNull Player to);

	// -- Your Got Header:
    
	default @NotNull String makeYourGotRequestsHeaderMessage(@NotNull String toName, @NotNull Set<Player> requests) {
		val to = getPlayer(toName);
		return makeYourGotRequestsHeaderMessage(to, requests);
	}

	default @NotNull String makeYourGotRequestsHeaderMessage(@NotNull UUID toUniqueId, @NotNull Set<Player> requests) {
		val to = getPlayer(toUniqueId);
		return makeYourGotRequestsHeaderMessage(to, requests);
	}

	@NotNull String makeYourGotRequestsHeaderMessage(@NotNull Player to, @NotNull Set<Player> requests);

	// -- Got Header:

	default @NotNull String makeGotRequestsHeaderMessage(@NotNull String toName, @NotNull Set<Player> requests) {
		val to = getPlayer(toName);
		return makeGotRequestsHeaderMessage(to, requests);
	}

	default @NotNull String makeGotRequestsHeaderMessage(@NotNull UUID toUniqueId, @NotNull Set<Player> requests) {
		val to = getPlayer(toUniqueId);
		return makeGotRequestsHeaderMessage(to, requests);
	}

	@NotNull String makeGotRequestsHeaderMessage(@NotNull Player to, @NotNull Set<Player> requests);

	// -- Got Item:
    
	default @NotNull String makeGotRequestsItemMessage(@NotNull String fromName, @NotNull String toName) {
		val from = getPlayer(fromName);
		val to   = getPlayer(toName);
		return makeGotRequestsItemMessage(from, to);
	}
    
	default @NotNull String makeGotRequestsItemMessage(@NotNull String fromName, @NotNull UUID toUniqueId) {
		val from = getPlayer(fromName);
		val to   = getPlayer(toUniqueId);
		return makeGotRequestsItemMessage(from, to);
	}
    
	default @NotNull String makeGotRequestsItemMessage(@NotNull String fromName, @NotNull Player to) {
		val from = getPlayer(fromName);
		return makeGotRequestsItemMessage(from, to);
	}

	default @NotNull String makeGotRequestsItemMessage(@NotNull UUID fromUniqueId, @NotNull String toName) {
		val from = getPlayer(fromUniqueId);
		val to   = getPlayer(toName);
		return makeGotRequestsItemMessage(from, to);
	}
    
	default @NotNull String makeGotRequestsItemMessage(@NotNull UUID fromUniqueId, @NotNull UUID toUniqueId) {
		val from = getPlayer(fromUniqueId);
		val to   = getPlayer(toUniqueId);
		return makeGotRequestsItemMessage(from, to);
	}
    
	default @NotNull String makeGotRequestsItemMessage(@NotNull UUID fromUniqueId, @NotNull Player to) {
		val from = getPlayer(fromUniqueId);
		return makeGotRequestsItemMessage(from, to);
	}
    
	default @NotNull String makeGotRequestsItemMessage(@NotNull Player from, @NotNull String toName) {
		val to = getPlayer(toName);
		return makeGotRequestsItemMessage(from, to);
	}

	@NotNull String makeGotRequestsItemMessage(@NotNull Player from, @NotNull Player to);

	// -- You Have No Sent:
    
	default @NotNull String makeYouHaveNoSentRequestsMessage(@NotNull String toName) {
		val to = getPlayer(toName);
		return makeYouHaveNoSentRequestsMessage(to);
	}

	default @NotNull String makeYouHaveNoSentRequestsMessage(@NotNull UUID toUniqueId) {
		val to = getPlayer(toUniqueId);
		return makeYouHaveNoSentRequestsMessage(to);
	}
    
	@NotNull String makeYouHaveNoSentRequestsMessage(@NotNull Player to);

	// -- Has No Sent:
    
	default @NotNull String makeHasNoSentRequestsMessage(@NotNull String toName) {
		val to = getPlayer(toName);
		return makeHasNoSentRequestsMessage(to);
	}

	default @NotNull String makeHasNoSentRequestsMessage(@NotNull UUID toUniqueId) {
		val to = getPlayer(toUniqueId);
		return makeHasNoSentRequestsMessage(to);
	}

	@NotNull String makeHasNoSentRequestsMessage(@NotNull Player to);

	// -- Your Sent Header:
    
	default @NotNull String makeYourSentRequestsHeaderMessage(@NotNull String toName, @NotNull Set<Player> requests) {
		val to = getPlayer(toName);
		return makeYourSentRequestsHeaderMessage(to, requests);
	}

	default @NotNull String makeYourSentRequestsHeaderMessage(@NotNull UUID toUniqueId, @NotNull Set<Player> requests) {
		val to = getPlayer(toUniqueId);
		return makeYourSentRequestsHeaderMessage(to, requests);
	}

	@NotNull String makeYourSentRequestsHeaderMessage(@NotNull Player from, @NotNull Set<Player> requests);

	// -- Sent Header:

	default @NotNull String makeSentRequestsHeaderMessage(@NotNull String toName, @NotNull Set<Player> requests) {
		val to = getPlayer(toName);
		return makeSentRequestsHeaderMessage(to, requests);
	}

	default @NotNull String makeSentRequestsHeaderMessage(@NotNull UUID toUniqueId, @NotNull Set<Player> requests) {
		val to = getPlayer(toUniqueId);
		return makeSentRequestsHeaderMessage(to, requests);
	}

	@NotNull String makeSentRequestsHeaderMessage(@NotNull Player from, @NotNull Set<Player> requests);

	// -- Sent Item:
    
	default @NotNull String makeSentRequestsItemMessage(@NotNull String fromName, @NotNull String toName) {
		val from = getPlayer(fromName);
		val to   = getPlayer(toName);
		return makeSentRequestsItemMessage(from, to);
	}
    
	default @NotNull String makeSentRequestsItemMessage(@NotNull String fromName, @NotNull UUID toUniqueId) {
		val from = getPlayer(fromName);
		val to   = getPlayer(toUniqueId);
		return makeSentRequestsItemMessage(from, to);
	}
    
	default @NotNull String makeSentRequestsItemMessage(@NotNull String fromName, @NotNull Player to) {
		val from = getPlayer(fromName);
		return makeSentRequestsItemMessage(from, to);
	}

	default @NotNull String makeSentRequestsItemMessage(@NotNull UUID fromUniqueId, @NotNull String toName) {
		val from = getPlayer(fromUniqueId);
		val to   = getPlayer(toName);
		return makeSentRequestsItemMessage(from, to);
	}
    
	default @NotNull String makeSentRequestsItemMessage(@NotNull UUID fromUniqueId, @NotNull UUID toUniqueId) {
		val from = getPlayer(fromUniqueId);
		val to   = getPlayer(toUniqueId);
		return makeSentRequestsItemMessage(from, to);
	}
    
	default @NotNull String makeSentRequestsItemMessage(@NotNull UUID fromUniqueId, @NotNull Player to) {
		val from = getPlayer(fromUniqueId);
		return makeSentRequestsItemMessage(from, to);
	}
    
	default @NotNull String makeSentRequestsItemMessage(@NotNull Player from, @NotNull String toName) {
		val to = getPlayer(toName);
		return makeSentRequestsItemMessage(from, to);
	}

	@NotNull String makeSentRequestsItemMessage(@NotNull Player from, @NotNull Player to);

    // Teleport:

	// - Cannot Be Teleported:
    
	default @NotNull String makeCannotBeTeleportedNowMessage(@NotNull String fromName, @NotNull String toName) {
		val from = getPlayer(fromName);
		val to   = getPlayer(toName);
		return makeCannotBeTeleportedNowMessage(from, to);
	}
    
	default @NotNull String makeCannotBeTeleportedNowMessage(@NotNull String fromName, @NotNull UUID toUniqueId) {
		val from = getPlayer(fromName);
		val to   = getPlayer(toUniqueId);
		return makeCannotBeTeleportedNowMessage(from, to);
	}
    
	default @NotNull String makeCannotBeTeleportedNowMessage(@NotNull String fromName, @NotNull Player to) {
		val from = getPlayer(fromName);
		return makeCannotBeTeleportedNowMessage(from, to);
	}

	default @NotNull String makeCannotBeTeleportedNowMessage(@NotNull UUID fromUniqueId, @NotNull String toName) {
		val from = getPlayer(fromUniqueId);
		val to   = getPlayer(toName);
		return makeCannotBeTeleportedNowMessage(from, to);
	}
    
	default @NotNull String makeCannotBeTeleportedNowMessage(@NotNull UUID fromUniqueId, @NotNull UUID toUniqueId) {
		val from = getPlayer(fromUniqueId);
		val to   = getPlayer(toUniqueId);
		return makeCannotBeTeleportedNowMessage(from, to);
	}
    
	default @NotNull String makeCannotBeTeleportedNowMessage(@NotNull UUID fromUniqueId, @NotNull Player to) {
		val from = getPlayer(fromUniqueId);
		return makeCannotBeTeleportedNowMessage(from, to);
	}
    
	default @NotNull String makeCannotBeTeleportedNowMessage(@NotNull Player from, @NotNull String toName) {
		val to = getPlayer(toName);
		return makeCannotBeTeleportedNowMessage(from, to);
	}

	@NotNull String makeCannotBeTeleportedNowMessage(@NotNull Player from, @NotNull Player to);

	// - To Location:
    
	default @NotNull String makeTeleportingToLocationMessage(@NotNull String fromName) {
		val from = getPlayer(fromName);
		return makeTeleportingToLocationMessage(from);
	}

	default @NotNull String makeTeleportingToLocationMessage(@NotNull UUID fromUniqueId) {
		val from = getPlayer(fromUniqueId);
		return makeTeleportingToLocationMessage(from);
	}
    
	@NotNull String makeTeleportingToLocationMessage(@NotNull Player from);

	// - To Named Location:
    
	default @NotNull String makeTeleportingToNamedLocationMessage(@NotNull String fromName, @NotNull String to) {
		val from = getPlayer(fromName);
		return makeTeleportingToNamedLocationMessage(from, to);
	}

	default @NotNull String makeTeleportingToNamedLocationMessage(@NotNull UUID fromUniqueId, @NotNull String to) {
		val from = getPlayer(fromUniqueId);
		return makeTeleportingToNamedLocationMessage(from, to);
	}

	@NotNull String makeTeleportingToNamedLocationMessage(@NotNull Player from, @NotNull String to);

	// - To Player:
    
	default @NotNull String makeTeleportingToPlayerMessage(@NotNull String fromName, @NotNull String toName) {
		val from = getPlayer(fromName);
		val to   = getPlayer(toName);
		return makeTeleportingToPlayerMessage(from, to);
	}
    
	default @NotNull String makeTeleportingToPlayerMessage(@NotNull String fromName, @NotNull UUID toUniqueId) {
		val from = getPlayer(fromName);
		val to   = getPlayer(toUniqueId);
		return makeTeleportingToPlayerMessage(from, to);
	}
    
	default @NotNull String makeTeleportingToPlayerMessage(@NotNull String fromName, @NotNull Player to) {
		val from = getPlayer(fromName);
		return makeTeleportingToPlayerMessage(from, to);
	}

	default @NotNull String makeTeleportingToPlayerMessage(@NotNull UUID fromUniqueId, @NotNull String toName) {
		val from = getPlayer(fromUniqueId);
		val to   = getPlayer(toName);
		return makeTeleportingToPlayerMessage(from, to);
	}
    
	default @NotNull String makeTeleportingToPlayerMessage(@NotNull UUID fromUniqueId, @NotNull UUID toUniqueId) {
		val from = getPlayer(fromUniqueId);
		val to   = getPlayer(toUniqueId);
		return makeTeleportingToPlayerMessage(from, to);
	}
    
	default @NotNull String makeTeleportingToPlayerMessage(@NotNull UUID fromUniqueId, @NotNull Player to) {
		val from = getPlayer(fromUniqueId);
		return makeTeleportingToPlayerMessage(from, to);
	}
    
	default @NotNull String makeTeleportingToPlayerMessage(@NotNull Player from, @NotNull String toName) {
		val to = getPlayer(toName);
		return makeTeleportingToPlayerMessage(from, to);
	}

	@NotNull String makeTeleportingToPlayerMessage(@NotNull Player from, @NotNull Player to);

	// - To You:
    
	default @NotNull String makeTeleportingToYouMessage(@NotNull String fromName, @NotNull String toName) {
		val from = getPlayer(fromName);
		val to   = getPlayer(toName);
		return makeTeleportingToYouMessage(from, to);
	}
    
	default @NotNull String makeTeleportingToYouMessage(@NotNull String fromName, @NotNull UUID toUniqueId) {
		val from = getPlayer(fromName);
		val to   = getPlayer(toUniqueId);
		return makeTeleportingToYouMessage(from, to);
	}
    
	default @NotNull String makeTeleportingToYouMessage(@NotNull String fromName, @NotNull Player to) {
		val from = getPlayer(fromName);
		return makeTeleportingToYouMessage(from, to);
	}

	default @NotNull String makeTeleportingToYouMessage(@NotNull UUID fromUniqueId, @NotNull String toName) {
		val from = getPlayer(fromUniqueId);
		val to   = getPlayer(toName);
		return makeTeleportingToYouMessage(from, to);
	}
    
	default @NotNull String makeTeleportingToYouMessage(@NotNull UUID fromUniqueId, @NotNull UUID toUniqueId) {
		val from = getPlayer(fromUniqueId);
		val to   = getPlayer(toUniqueId);
		return makeTeleportingToYouMessage(from, to);
	}
    
	default @NotNull String makeTeleportingToYouMessage(@NotNull UUID fromUniqueId, @NotNull Player to) {
		val from = getPlayer(fromUniqueId);
		return makeTeleportingToYouMessage(from, to);
	}
    
	default @NotNull String makeTeleportingToYouMessage(@NotNull Player from, @NotNull String toName) {
		val to = getPlayer(toName);
		return makeTeleportingToYouMessage(from, to);
	}

	@NotNull String makeTeleportingToYouMessage(@NotNull Player from, @NotNull Player to);

	// - Count Down:
    
	@NotNull String makeCountDownMessage(@NotNull Time left, @NotNull Time delay);

	// - Teleported:
    
	default @NotNull String makeTeleportedMessage(@NotNull String fromName) {
		val from = getPlayer(fromName);
		return makeTeleportedMessage(from);
	}

	default @NotNull String makeTeleportedMessage(@NotNull UUID fromUniqueId) {
		val from = getPlayer(fromUniqueId);
		return makeTeleportedMessage(from);
	}
    
	@NotNull String makeTeleportedMessage(@NotNull Player from);

    // Error:

	// - Cannot Send To Your Self:
    
	default @NotNull String makeCannotSendRequestToYourSelfMessage(@NotNull String fromName) {
		val from = getPlayer(fromName);
		return makeCannotSendRequestToYourSelfMessage(from);
	}

	default @NotNull String makeCannotSendRequestToYourSelfMessage(@NotNull UUID fromUniqueId) {
		val from = getPlayer(fromUniqueId);
		return makeCannotSendRequestToYourSelfMessage(from);
	}
    
	@NotNull String makeCannotSendRequestToYourSelfMessage(@NotNull Player from);

	// - Wait Until Cool Down Is Over:
    
	@NotNull String makeWaitUntilCoolDownOverMessage(@NotNull Player from, @NotNull Time left, @NotNull Time delay);

	// - Missing Bed:
    
	default @NotNull String makeMissingBedMessage(@NotNull String fromName) {
		val from = getPlayer(fromName);
		return makeMissingBedMessage(from);
	}

	default @NotNull String makeMissingBedMessage(@NotNull UUID fromUniqueId) {
		val from = getPlayer(fromUniqueId);
		return makeMissingBedMessage(from);
	}
    
	@NotNull String makeMissingBedMessage(@NotNull Player from);

	// - Already:
    
	default @NotNull String makeAlreadyTeleportingMessage(@NotNull String fromName) {
		val from = getPlayer(fromName);
		return makeAlreadyTeleportingMessage(from);
	}

	default @NotNull String makeAlreadyTeleportingMessage(@NotNull UUID fromUniqueId) {
		val from = getPlayer(fromUniqueId);
		return makeAlreadyTeleportingMessage(from);
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
