package shared.model.bank;


import shared.communication.input.move.ResourceHand;
import shared.definitions.DevCardType;


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
	
	public Bank() throws Exception{
		brick = new ResourceCard(0);
		wood = new ResourceCard(0);
		sheep = new ResourceCard(0);
		wheat = new ResourceCard(0);
		ore = new ResourceCard(0);
		soldier = new DevelopmentCard(0, DevCardType.SOLDIER);
		yearOfPlenty = new DevelopmentCard(0, DevCardType.YEAR_OF_PLENTY);
		roadBuild = new DevelopmentCard(0, DevCardType.ROAD_BUILD);
		monument = new DevelopmentCard(0, DevCardType.MONUMENT);
		monopoly = new DevelopmentCard(0, DevCardType.MONOPOLY);
	}
	
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
	 * 
	 * @param rh
	 * @throws Exception
	 */
	public void addRC(ResourceHand rh) throws Exception{
		brick.modify(rh.getBrick());
		wheat.modify(rh.getWheat());
		ore.modify(rh.getOre());
		wood.modify(rh.getWood());
		sheep.modify(rh.getSheep());
	}
	

	/**
	 * 
	 * @param card
	 * @throws Exception
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
	
	public void addDC(DevelopmentHand dh) throws Exception{
		soldier.modify(dh.getSoldier());
		monopoly.modify(dh.getMonopoly());
		yearOfPlenty.modify(dh.getYearOfPlenty());
		monument.modify(dh.getMonument());
		roadBuild.modify(dh.getRoadBuild());
	}
	

	/**
	 * 	
	 * @param rh
	 * @return
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
		case YEAR_OF_PLENTY: temp = yearOfPlenty.getQuantity();
			break;
		case ROAD_BUILD: temp = roadBuild.getQuantity();
			break;
		case MONUMENT: temp = monument.getQuantity();
			break;
		default:
			throw new Exception("type does not exist");
		}
		
		if(temp<quantity) return false;
		return true;
	}
	
	
	
	
	
	public void initRC(int quantity) throws Exception{
		brick.setQuantity(quantity);
		ore.setQuantity(quantity);
		wheat.setQuantity(quantity);
		wood.setQuantity(quantity);
		sheep.setQuantity(quantity);
	}
	
	public void initRC(ResourceHand rh) throws Exception{
		brick.setQuantity(rh.getBrick());
		ore.setQuantity(rh.getOre());
		wheat.setQuantity(rh.getWheat());
		wood.setQuantity(rh.getWood());
		sheep.setQuantity(rh.getSheep());
	}
	
	public void initDC(DevelopmentHand dh) throws Exception{
		soldier.setQuantity(dh.getSoldier());
		monument.setQuantity(dh.getMonument());
		monopoly.setQuantity(dh.getMonopoly());
		yearOfPlenty.setQuantity(dh.getYearOfPlenty());
		roadBuild.setQuantity(dh.getRoadBuild());
	}
	
	
	
	
	

}
