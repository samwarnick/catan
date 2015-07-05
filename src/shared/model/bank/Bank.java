package shared.model.bank;

import java.util.ArrayList;

import shared.communication.input.move.ResourceHand;
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
	protected DevelopmentCard soldier;
	protected DevelopmentCard yearOfPlenty;
	protected DevelopmentCard roadBuild;
	protected DevelopmentCard monopoly;
	protected DevelopmentCard monument;
	private boolean largestArmyCard = false;
	private boolean longestRoadCard = false;
	
	public boolean hasLargestArmyCard() {
		return largestArmyCard;
	}
	public boolean hasLongestRoadCard() {
		return longestRoadCard;
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
	 * @throws Exception 
	 * @pre RC is not null
	 * @post RC is added to the Bank's Array
	 * 
	 */
	
	public void addRC(ResourceHand rh) throws Exception{
		brick.modify(rh.getBrick());
		wheat.modify(rh.getWheat());
		ore.modify(rh.getOre());
		wood.modify(rh.getWood());
		sheep.modify(rh.getSheep());
	}
	
	/**
	 * adds a single development card to the bank.
	 * 
	 * @param DC is a DevelopmentCard Object
	 * @throws Exception 
	 * @pre DC is not null
	 * @post DC is added to the Bank's Array of DC Cards
	 */
	
	public void addDC(DevCardType card) throws Exception{
		switch(card){
		case SOLDIER: soldier.modify(1);
			break;
		case MONOPOLY: monopoly.modify(1);
			break;
		case YEAR_OF_PLENTY: yearOfPlenty.modify(1);
			break;
		case ROAD_BUILD: roadBuild.modify(1);
			break;
		case MONUMENT: monument.modify(1);
			break;
		default:
			throw new Exception("bad type parameter");
		}
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
	
	public boolean hasRC(ResourceHand rh){
		if(ore.getQuantity()<rh.getOre()) return false;
		if(wood.getQuantity()<rh.getWood()) return false;
		if(sheep.getQuantity()<rh.getSheep()) return false;
		if(wheat.getQuantity()<rh.getWheat()) return false;
		if(brick.getQuantity()<rh.getBrick()) return false;
		return true;
	}
	
	/**
	 * returns true if the player possesses the given quantity of the
	 * specified development card.
	 * 
	 * @param dt is a DevCardType
	 * @param quantity is the number of cards we want to find
	 * @throws Exception 
	 * @pre a valid DevCardType and a non-negative quantity
	 * @post returns true if the player has the specified number of the specified type, false otherwise.
	 * 
	 */
	
	public boolean hasDC(DevCardType dt, int quantity) throws Exception{
		int temp = 0;
		switch(dt){
		case SOLDIER: temp = soldier.getQuantity();
			break;
		case MONOPOLY: temp = monopoly.getQuantity();
			break;
		case YEAR_OF_PLENTY: yearOfPlenty.modify(1);
			break;
		case ROAD_BUILD: roadBuild.modify(1);
			break;
		case MONUMENT: monument.modify(1);
			break;
		default:
			throw new Exception("bad type parameter");
		}
		
		if(temp<quantity) return false;
		return true;
	}
	
	
	
	
	

}
