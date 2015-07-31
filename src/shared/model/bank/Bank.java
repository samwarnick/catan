package shared.model.bank;


import com.fasterxml.jackson.annotation.JsonIgnore;

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
	
	protected ResourceCard brick;
	protected ResourceCard wood;
	protected ResourceCard sheep;
	protected ResourceCard wheat;
	protected ResourceCard ore;
	protected DevelopmentCard soldier;
	protected DevelopmentCard yearOfPlenty;
	protected DevelopmentCard roadBuild;
	protected DevelopmentCard monopoly;
	protected DevelopmentCard monument;
	protected boolean largestArmyCard = true;
	protected boolean longestRoadCard = true;
	

	public Bank(){

		try {
			brick = new ResourceCard(19, ResourceType.BRICK);
			wood = new ResourceCard(19, ResourceType.WOOD);
			sheep = new ResourceCard(19, ResourceType.SHEEP);
			wheat = new ResourceCard(19, ResourceType.WHEAT);
			ore = new ResourceCard(19, ResourceType.ORE);
			soldier = new DevelopmentCard(14, DevCardType.SOLDIER);
			yearOfPlenty = new DevelopmentCard(2, DevCardType.YEAR_OF_PLENTY);
			roadBuild = new DevelopmentCard(2, DevCardType.ROAD_BUILD);
			monument = new DevelopmentCard(5, DevCardType.MONUMENT);
			monopoly = new DevelopmentCard(2, DevCardType.MONOPOLY);

		} catch (BankException e) {

			e.printStackTrace();
		}
		
	}
	
	
	
	public boolean isLargestArmyCard() {
		return largestArmyCard;
	}



	public boolean isLongestRoadCard() {
		return longestRoadCard;
	}



	public void setBrick(ResourceCard brick) {
		this.brick = brick;
	}



	public void setWood(ResourceCard wood) {
		this.wood = wood;
	}



	public void setSheep(ResourceCard sheep) {
		this.sheep = sheep;
	}



	public void setWheat(ResourceCard wheat) {
		this.wheat = wheat;
	}



	public void setOre(ResourceCard ore) {
		this.ore = ore;
	}



	public void setSoldier(DevelopmentCard soldier) {
		this.soldier = soldier;
	}



	public void setYearOfPlenty(DevelopmentCard yearOfPlenty) {
		this.yearOfPlenty = yearOfPlenty;
	}



	public void setRoadBuild(DevelopmentCard roadBuild) {
		this.roadBuild = roadBuild;
	}



	public void setMonopoly(DevelopmentCard monopoly) {
		this.monopoly = monopoly;
	}



	public void setMonument(DevelopmentCard monument) {
		this.monument = monument;
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
	
	@JsonIgnore public ResourceCard getResourceStack(ResourceType type) {
		switch (type) {
		case BRICK:
			return brick;
		case WOOD:
			return wood;
		case SHEEP:
			return sheep;
		case WHEAT:
			return wheat;
		case ORE:
			return ore;
		default:
			return null;
		}
	}
	
	@JsonIgnore public DevelopmentCard getDevStack(DevCardType type) {
		switch(type){
		case SOLDIER:
			return soldier;
		case MONOPOLY:
			return monopoly;
		case YEAR_OF_PLENTY:
			return yearOfPlenty;
		case ROAD_BUILD: 
			return roadBuild;
		case MONUMENT:
			return monument;
		default:
			return null;
		}
	}
	

	/**
	 * 
	 * @param rh a ResourceHand object
	 * @throws BankException
	 * @pre all attributes of the rh are non negative and do not exceed limits
	 * @post all resource attributes are increased by their corresponding rh attribute.
	 */
	public void modifyRC(ResourceHand rh) throws BankException{
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
	
	public void modifyDC(DevelopmentHand dh) throws BankException{
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
	
	@JsonIgnore public void setRC(int quantity) throws BankException{
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
	@JsonIgnore public void setRC(ResourceHand rh) throws BankException{
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
	
	@JsonIgnore public void setDC(DevelopmentHand dh) throws BankException{
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
	
	@JsonIgnore public int getNumDevCards() {
		return soldier.getQuantity() + monument.getQuantity() + monopoly.getQuantity() + yearOfPlenty.getQuantity() + roadBuild.getQuantity();
	}
	
	@JsonIgnore public int getNumResourceCards(){
		return wood.getQuantity() + brick.getQuantity() + sheep.getQuantity() + wheat.getQuantity() + ore.getQuantity();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((brick == null) ? 0 : brick.hashCode());
		result = prime * result + (largestArmyCard ? 1231 : 1237);
		result = prime * result + (longestRoadCard ? 1231 : 1237);
		result = prime * result
				+ ((monopoly == null) ? 0 : monopoly.hashCode());
		result = prime * result
				+ ((monument == null) ? 0 : monument.hashCode());
		result = prime * result + ((ore == null) ? 0 : ore.hashCode());
		result = prime * result
				+ ((roadBuild == null) ? 0 : roadBuild.hashCode());
		result = prime * result + ((sheep == null) ? 0 : sheep.hashCode());
		result = prime * result + ((soldier == null) ? 0 : soldier.hashCode());
		result = prime * result + ((wheat == null) ? 0 : wheat.hashCode());
		result = prime * result + ((wood == null) ? 0 : wood.hashCode());
		result = prime * result
				+ ((yearOfPlenty == null) ? 0 : yearOfPlenty.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bank other = (Bank) obj;
		if (brick == null) {
			if (other.brick != null)
				return false;
		} else if (!brick.equals(other.brick))
			return false;
		if (largestArmyCard != other.largestArmyCard)
			return false;
		if (longestRoadCard != other.longestRoadCard)
			return false;
		if (monopoly == null) {
			if (other.monopoly != null)
				return false;
		} else if (!monopoly.equals(other.monopoly))
			return false;
		if (monument == null) {
			if (other.monument != null)
				return false;
		} else if (!monument.equals(other.monument))
			return false;
		if (ore == null) {
			if (other.ore != null)
				return false;
		} else if (!ore.equals(other.ore))
			return false;
		if (roadBuild == null) {
			if (other.roadBuild != null)
				return false;
		} else if (!roadBuild.equals(other.roadBuild))
			return false;
		if (sheep == null) {
			if (other.sheep != null)
				return false;
		} else if (!sheep.equals(other.sheep))
			return false;
		if (soldier == null) {
			if (other.soldier != null)
				return false;
		} else if (!soldier.equals(other.soldier))
			return false;
		if (wheat == null) {
			if (other.wheat != null)
				return false;
		} else if (!wheat.equals(other.wheat))
			return false;
		if (wood == null) {
			if (other.wood != null)
				return false;
		} else if (!wood.equals(other.wood))
			return false;
		if (yearOfPlenty == null) {
			if (other.yearOfPlenty != null)
				return false;
		} else if (!yearOfPlenty.equals(other.yearOfPlenty))
			return false;
		return true;
	}

	public ResourceCard getBrick() {
		return brick;
	}

	public ResourceCard getWood() {
		// TODO Auto-generated method stub
		return wood;
	}

	public ResourceCard getSheep() {
		// TODO Auto-generated method stub
		return sheep;
	}

	public ResourceCard getWheat() {
		// TODO Auto-generated method stub
		return wheat;
	}

	public ResourceCard getOre() {
		// TODO Auto-generated method stub
		return ore;
	}

	public DevelopmentCard getSoldier() {
		// TODO Auto-generated method stub
		return soldier;
	}

	public DevelopmentCard getYearOfPlenty() {
		// TODO Auto-generated method stub
		return yearOfPlenty;
	}

	public DevelopmentCard getRoadBuild() {
		// TODO Auto-generated method stub
		return roadBuild;
	}

	public DevelopmentCard getMonopoly() {
		// TODO Auto-generated method stub
		return monopoly;
	}

	public DevelopmentCard getMonument() {
		// TODO Auto-generated method stub
		return monument;
	}
	
	
}
