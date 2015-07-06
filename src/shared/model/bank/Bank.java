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
	

	public Bank(){

		try {
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



		} catch (BankException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
	 * @param rh a ResourceHand object
	 * @throws BankException
	 * @pre all attributes of the rh are non negative and do not exceed limits
	 * @post all resource attributes are increased by their corresponding rh attribute.
	 */
	public void addRC(ResourceHand rh) throws BankException{
		brick.modify(rh.getBrick());
		wheat.modify(rh.getWheat());
		ore.modify(rh.getOre());
		wood.modify(rh.getWood());
		sheep.modify(rh.getSheep());
	}
	

	/**
	 * 
	 * @param card
	 * @throws BankException
	 */
	public void addDC(DevCardType card) throws BankException{
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
			throw new BankException("bad type parameter");
		}
	}
	
	/**
	 * 
	 * @param dh
	 * @throws BankException
	 */
	
	public void addDC(DevelopmentHand dh) throws BankException{
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
	 * @throws BankException 
	 * @pre a valid DevCardType and a non-negative quantity
	 * @post returns true if the player has the specified number of the specified type, false otherwise.
	 * 
	 */
	
	public boolean hasDC(DevCardType dt, int quantity) throws BankException{
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
			throw new BankException("type does not exist");
		}
		
		if(temp<quantity) return false;
		return true;
	}
	
	
	
	/**
	 * 
	 * @param quantity
	 * @throws BankException
	 */
	
	public void setRC(int quantity) throws BankException{
		brick.setQuantity(quantity);
		ore.setQuantity(quantity);
		wheat.setQuantity(quantity);
		wood.setQuantity(quantity);
		sheep.setQuantity(quantity);
	}
	
	
	/**
	 * 
	 * @param rh
	 * @throws BankException
	 */
	public void setRC(ResourceHand rh) throws BankException{
		brick.setQuantity(rh.getBrick());
		ore.setQuantity(rh.getOre());
		wheat.setQuantity(rh.getWheat());
		wood.setQuantity(rh.getWood());
		sheep.setQuantity(rh.getSheep());
	}
	
	/**
	 * 
	 * @param dh
	 * @throws BankException
	 */
	
	public void setDC(DevelopmentHand dh) throws BankException{
		soldier.setQuantity(dh.getSoldier());
		monument.setQuantity(dh.getMonument());
		monopoly.setQuantity(dh.getMonopoly());
		yearOfPlenty.setQuantity(dh.getYearOfPlenty());
		roadBuild.setQuantity(dh.getRoadBuild());
	}
	

	/**
	 * returns true if Bank has any DC's, false otherwise.
	 * @return
	 */
	
	public boolean hasAnyDC(){
		if(soldier.getQuantity()>0) return true;
		if(monument.getQuantity()>0) return true;
		if(monopoly.getQuantity()>0) return true;
		if(yearOfPlenty.getQuantity()>0) return true;
		if(roadBuild.getQuantity()>0) return true;
		
		return false;

	}
	public int getNumResourceCards(){
		return wood.getQuantity() + brick.getQuantity() + sheep.getQuantity() + wheat.getQuantity() + ore.getQuantity();
	}
	

	
	
	

}
