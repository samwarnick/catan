package shared.model.bank;

import java.util.ArrayList;

import shared.definitions.DevCardType;
import shared.definitions.ResourceType;


/**
 * @author isaachartung
 *
 *The Bank Object holds ResourceCard Objects and DevelopmentCard Objects.
 *It contains methods to add and remove these objects, as well as to query
 *information concerning them.  It also contains two boolean data members:
 *one which tells us whether this bank holds the largestArmyCard and another
 *which indicates whether this bank has the longestRoadCard.  These also have
 *setters and methods to query their value.
 *
 */
public class Bank {
	
	private ResourceCard brick;
	private ResourceCard wood;
	private ResourceCard sheep;
	private ResourceCard wheat;
	private ResourceCard ore;
	private DevelopmentCard soldier;
	private DevelopmentCard yearOfPlenty;
	private DevelopmentCard buildRoad;
	private DevelopmentCard monopoly;
	private DevelopmentCard monument;
	private boolean largestArmyCard = false;
	private boolean longestRoadCard = false;
	
	public ArrayList<ResourceCard> getResourceCards() {
		return resourceCards;
	}
	public ArrayList<DevelopmentCard> getDevelopmentCards() {
		return developmentCards;
	}
	public boolean hasLargestArmyCard() {
		return largestArmyCard;
	}
	public boolean hasLongestRoadCard() {
		return longestRoadCard;
	}
	public void setResourceCards(ArrayList<ResourceCard> resourceCards) {
		this.resourceCards = resourceCards;
	}
	public void setDevelopmentCards(ArrayList<DevelopmentCard> developmentCards) {
		this.developmentCards = developmentCards;
	}
	public void setLargestArmyCard(boolean largestArmyCard) {
		this.largestArmyCard = largestArmyCard;
	}
	public void setLongestRoadCard(boolean longestRoadCard) {
		this.longestRoadCard = longestRoadCard;
	}
	
	/**
	 * adds a single resource card to the bank.
	 * 
	 * @param RC is a ResourceCard object
	 * @pre RC is not null
	 * @post RC is added to the Bank's Array
	 * 
	 */
	
	public void addRC(ResourceCard RC){
		if(RC == null) return;
		resourceCards.add(RC);
	}
	
	/**
	 * adds a single development card to the bank.
	 * 
	 * @param DC is a DevelopmentCard Object
	 * @pre DC is not null
	 * @post DC is added to the Bank's Array of DC Cards
	 */
	
	public void addDC(DevelopmentCard DC){
		if(DC == null) return;
		developmentCards.add(DC);
	}
	
	/**
	 * returns a resource card of the type specified by the 
	 * parameter. if not found, it returns null
	 * 
	 * @param rt is a ResouceType
	 * @pre rt is a valid ResourceType, namely Grain, Ore, etc.
	 * @post a ResourceCard Object of the parameter type is returned, null if the bank has none.
	 */
	
	public ResourceCard getRC(ResourceType rt){
		return null;
	}
	
	/**
	 * returns a Development card of the type specified by the 
	 * parameter. if not found, it returns null
	 * 
	 * @param dt is a DevCardType 
	 * @pre dt is valid DevCardType, namely Soldier, Monopoly, etc.
	 * @post a DevelopmentCard Object of the parameter type is returned, null if the bank has none.
	 */
	
	public DevelopmentCard getDC(DevCardType dt){
		return null;
	}
	
	/**
	 * returns true if the player possesses the given quantity of the
	 * specified resource card.
	 * 
	 * @param rt is a ResourceType
	 * @param quantity is the number of cards we want to find
	 * @pre a valid ResourceType is given and a non-negative quantity
	 * @post returns true if the player has the specified number of the specified type, false otherwise. 
	 */
	
	public boolean hasRC(ResourceType rt, int quantity){
		return false;
	}
	
	/**
	 * returns true if the player possesses the given quantity of the
	 * specified development card.
	 * 
	 * @param dt is a DevCardType
	 * @param quantity is the number of cards we want to find
	 * @pre a valid DevCardType and a non-negative quantity
	 * @post returns true if the player has the specified number of the specified type, false otherwise.
	 * 
	 */
	
	public boolean hasDC(DevCardType dt, int quantity){
		return false;
	}
	
	
	
	
	

}
