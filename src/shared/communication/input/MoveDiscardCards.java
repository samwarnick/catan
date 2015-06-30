package shared.communication.input;

import java.util.ArrayList;

import shared.model.bank.ResourceCard;

/**
 * @author isaachartung
 *
 */
public class MoveDiscardCards extends MoveInput{
	
	private ArrayList<ResourceCard> cards;
	
	public MoveDiscardCards(int playerID, ArrayList<ResourceCard> cards){
		super("/moves/discardCards", playerID);
		this.cards = cards;
	}

	public ArrayList<ResourceCard> getCards() {
		return cards;
	}

	public void setCards(ArrayList<ResourceCard> cards) {
		this.cards = cards;
	}

}
