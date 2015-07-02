package shared.communication.input.move;

/**
 * This class contains the offerTrade method name, the resources offered and the playerIndexes for the player offering and receiving the trade.
 * @author Matt
 * 
 */
public class OfferTradeInput extends MoveInput {

	private ResourceHand offer;
	private int receiverIndex;
	
	public OfferTradeInput(int playerIndex, ResourceHand offer, int receiverIndex) {
		super("offerTrade", playerIndex);
		this.offer = offer;
		this.receiverIndex = receiverIndex;
	}

	public ResourceHand getOffer() {
		return offer;
	}

	public int getReceiverIndex() {
		return receiverIndex;
	}
	
}
