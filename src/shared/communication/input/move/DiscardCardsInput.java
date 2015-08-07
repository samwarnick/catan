package shared.communication.input.move;

import shared.model.bank.ResourceHand;

/**
 * This class contains the discardCards method name, the resources being discarded and the playerIndex for the player doing the action.
 * @author Matt
 * 
 */
public class DiscardCardsInput extends MoveInput {

	private ResourceHand discardedCards;
	private String type = "discardCards";

	public DiscardCardsInput(int playerIndex, ResourceHand discardedCards) {
		super("/discardCards", playerIndex);
		this.discardedCards = discardedCards;
	}
	
	public DiscardCardsInput() {
		super("/discardCards", -1);
		this.discardedCards = null;
	}

	public ResourceHand getDiscardedCards() {
		return discardedCards;
	}

	public String getType() {
		return type;
	}

	public void setDiscardedCards(ResourceHand discardedCards) {
		this.discardedCards = discardedCards;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}
