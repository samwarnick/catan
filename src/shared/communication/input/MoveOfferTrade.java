package shared.communication.input;

import java.util.ArrayList;

import shared.model.bank.ResourceCard;

public class MoveOfferTrade extends MoveInput {
	
	private int receiver;
	private ArrayList<ResourceCard> cards;
	
	public MoveOfferTrade(int playerID, int receiver, ArrayList<ResourceCard> cards){
		super("/moves/offerTrade", playerID);
		this.receiver = receiver;
		this.cards = cards;
	}

	public int getReceiver() {
		return receiver;
	}

	public ArrayList<ResourceCard> getCards() {
		return cards;
	}

	public void setReceiver(int receiver) {
		this.receiver = receiver;
	}

	public void setCards(ArrayList<ResourceCard> cards) {
		this.cards = cards;
	}

}
