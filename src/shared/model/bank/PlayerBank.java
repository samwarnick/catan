package shared.model.bank;

import java.util.ArrayList;

import shared.definitions.DevCardType;

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
	
	private DevelopmentCard newSoldier;
	private DevelopmentCard newMonopoly;
	private DevelopmentCard newMonument;
	private DevelopmentCard newYearOfPlenty;
	private DevelopmentCard newRoadBuild;
	

	
	/**
	 * adds the Development Card parameter to the array.
	 * 
	 * @param DC is a DevelopmentCard object
	 * @throws Exception 
	 * @pre DC is not null
	 * @post DC is add to the newDevelopmentCards array.
	 * 
	 */

	public void addDC(DevCardType card) throws Exception {
		switch(card){
		case SOLDIER: newSoldier.modify(1);
			break;
		case MONOPOLY: newMonopoly.modify(1);
			break;
		case YEAR_OF_PLENTY: newYearOfPlenty.modify(1);
			break;
		case ROAD_BUILD: newRoadBuild.modify(1);
			break;
		case MONUMENT: newMonument.modify(1);
			break;
		default:
			throw new Exception("bad type parameter");
		}
	}
	
	/**
	 * empties the array.
	 * 
	 * @pre no preconditions
	 * @post the newDevelopmentCards Array is emptied.
	 */
	
	public void clear(){
		try {
			newSoldier.setQuantity(0);
			newMonopoly.setQuantity(0);
			newMonument.setQuantity(0);
			newRoadBuild.setQuantity(0);
			newYearOfPlenty.setQuantity(0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * @throws Exception 
	 * @pre no preconditions
	 * @post any DevelopmentCard objects contained in this classes array
	 *  is transfered to the D. Card array of the super class.
	 */
	
	public void transfer() throws Exception{
		soldier.modify(newSoldier.getQuantity());
		monopoly.modify(newMonopoly.getQuantity());
		yearOfPlenty.modify(newYearOfPlenty.getQuantity());
		roadBuild.modify(newRoadBuild.getQuantity());
		monument.modify(newMonument.getQuantity());
		clear();
	}

}
