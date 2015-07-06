package shared.communication.input.move;

import shared.model.bank.ResourceHand;

/**
 * This class contains the discardCards method name, the resources being discarded and the playerIndex for the player doing the action.
 * @author Matt
 * 
 */
public class DiscardCardsInput extends MoveInput {

	private ResourceHand discardedCards;

	public DiscardCardsInput(int playerIndex, ResourceHand discardedCards) {
		super("dicardCards", playerIndex);
		this.discardedCards = discardedCards;
	}

	public ResourceHand getDiscardedCards() {
		return discardedCards;
	}
	
}
