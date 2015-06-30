package shared.model.bank;

import java.util.ArrayList;

/**
 * @author isaachartung
 *
 *The PlayerBank is a Bank with an extra Array of DevelopmentCards.
 *This is necessary due to the game constraint that DevelopmentCards cannot
 *be played on the same turn they are drawn.  This class contains methods to 
 *manipulate this array.
 *
 */
public class PlayerBank extends Bank {
	
	private ArrayList<DevelopmentCard> newDevelopmentCards;

	public ArrayList<DevelopmentCard> getNewDevelopmentCards() {
		return newDevelopmentCards;
	}
	
	/**
	 * adds the Development Card parameter to the array.
	 * 
	 * @param DC is a DevelopmentCard object
	 * @pre DC is not null
	 * @post DC is add to the newDevelopmentCards array.
	 * 
	 */

	public void addDC(DevelopmentCard DC) {
		if(DC == null) return;
		newDevelopmentCards.add(DC);
	}
	
	/**
	 * empties the array.
	 * 
	 * @pre no preconditions
	 * @post the newDevelopmentCards Array is emptied.
	 */
	
	public void clear(){
		newDevelopmentCards.clear();
	}
	
	/**
	 * @pre no preconditions
	 * @post any DevelopmentCard objects contained in this classes array
	 *  is transfered to the D. Card array of the super class.
	 */
	
	public void transfer(){
		for(int i = 0; i<newDevelopmentCards.size(); i++){
			super.addDC(newDevelopmentCards.get(i));
		}
		clear();
	}

}
