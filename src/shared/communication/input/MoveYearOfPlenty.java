package shared.communication.input;

import java.util.ArrayList;

import shared.model.bank.ResourceCard;

/**
 * @author isaachartung
 *
 */
public class MoveYearOfPlenty extends MoveInput {
	
	private ArrayList<ResourceCard> first;
	private ArrayList<ResourceCard> second;
	
	public MoveYearOfPlenty(int playerIndex, ArrayList<ResourceCard> first,
			ArrayList<ResourceCard> second) {
		super("/moves/Year_Of_Plenty", playerIndex);
		this.first = first;
		this.second = second;
	}

	public ArrayList<ResourceCard> getFirst() {
		return first;
	}

	public ArrayList<ResourceCard> getSecond() {
		return second;
	}

	public void setFirst(ArrayList<ResourceCard> first) {
		this.first = first;
	}

	public void setSecond(ArrayList<ResourceCard> second) {
		this.second = second;
	}
	

}
