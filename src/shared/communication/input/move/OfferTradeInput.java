package shared.communication.input.move;

import shared.model.bank.ResourceHand;

/**
 * This class contains the offerTrade method name, the resources offered and the playerIndexes for the player offering and receiving the trade.
 * @author Matt
 * 
 */
public class OfferTradeInput extends MoveInput {

	private ResourceHand offer;
	private int receiver;
	private String type = "OfferTrade";
	
	public String getType() {
		return type;
	}

	public void setOffer(ResourceHand offer) {
		this.offer = offer;
	}

	public void setReceiver(int receiver) {
		this.receiver = receiver;
	}

	public void setType(String type) {
		this.type = type;
	}

	public OfferTradeInput(int playerIndex, ResourceHand offer, int receiverIndex) {
		super("/offerTrade", playerIndex);
		this.offer = offer;
		this.receiver = receiverIndex;
	}

	public ResourceHand getOffer() {
		return offer;
	}

	public int getReceiver() {
		return receiver;
	}
	
}
