package shared.communication.input;

/**
 * @author isaachartung
 *
 */
public class MoveOfferTrade extends MoveInput {
	
	private int receiver;
	private ResourceHand cards;
	
	public MoveOfferTrade(int playerID, int receiver, ResourceHand cards){
		super("/moves/offerTrade", playerID);
		this.receiver = receiver;
		this.cards = cards;
	}

	public int getReceiver() {
		return receiver;
	}

	public ResourceHand getCards() {
		return cards;
	}

	public void setReceiver(int receiver) {
		this.receiver = receiver;
	}

	public void setCards(ResourceHand cards) {
		this.cards = cards;
	}

}
