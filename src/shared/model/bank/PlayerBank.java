package shared.model.bank;

import java.util.ArrayList;

public class PlayerBank extends Bank {
	
	private ArrayList<DevelopmentCard> newDevelopmentCards;

	public ArrayList<DevelopmentCard> getNewDevelopmentCards() {
		return newDevelopmentCards;
	}
	
	/**
	 * adds the Development Card parameter to the array.
	 * 
	 */

	public void addDC(DevelopmentCard DC) {
		newDevelopmentCards.add(DC);
	}
	
	/**
	 * empties the array.
	 */
	
	public void clear(){
		newDevelopmentCards.clear();
	}

}
