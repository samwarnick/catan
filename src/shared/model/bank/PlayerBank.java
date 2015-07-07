package shared.model.bank;


import shared.definitions.DevCardType;
import shared.definitions.ResourceType;

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
	

	public PlayerBank(){

		super();
		try {
			
			brick = new ResourceCard(0, ResourceType.BRICK);
			wood = new ResourceCard(0, ResourceType.WOOD);
			sheep = new ResourceCard(0, ResourceType.SHEEP);
			wheat = new ResourceCard(0, ResourceType.WHEAT);
			ore = new ResourceCard(0, ResourceType.ORE);
			
			soldier = new DevelopmentCard(0, DevCardType.SOLDIER);
			yearOfPlenty = new DevelopmentCard(0, DevCardType.YEAR_OF_PLENTY);
			roadBuild = new DevelopmentCard(0, DevCardType.ROAD_BUILD);
			monument = new DevelopmentCard(0, DevCardType.MONUMENT);
			monopoly = new DevelopmentCard(0, DevCardType.MONOPOLY);
			
			newSoldier = new DevelopmentCard(0, DevCardType.SOLDIER);
			newMonopoly = new DevelopmentCard(0, DevCardType.MONOPOLY);
			newMonument = new DevelopmentCard(0, DevCardType.MONUMENT);
			newYearOfPlenty = new DevelopmentCard(0, DevCardType.YEAR_OF_PLENTY);
			newRoadBuild = new DevelopmentCard(0, DevCardType.ROAD_BUILD);
			
			largestArmyCard = false;
			longestRoadCard = false;

		} catch (BankException e) {

			e.printStackTrace();
		}
	}
	
	public DevelopmentCard getNewDevStack(DevCardType type) {
		switch(type){
		case SOLDIER:
			return newSoldier;
		case MONOPOLY:
			return newMonopoly;
		case YEAR_OF_PLENTY:
			return newYearOfPlenty;
		case ROAD_BUILD: 
			return newRoadBuild;
		case MONUMENT:
			return newMonument;
		default:
			return null;
		}
	}

	/**
	 * 
	 * increments a single attribute of the player bank; which attribute
	 * is decided by the DevCardType parameter.
	 * 
	 * @param card
	 * @throws BankException
	 */
	public void addNewDC(DevCardType card) throws BankException {
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
			throw new BankException("bad type parameter");
		}
	}
	
	
	/**
	 * 
	 * adds corresponding dh attribute values to player bank attributes
	 * 
	 * @param dh
	 * @throws BankException
	 */
	public void addNewDC(DevelopmentHand dh) throws BankException{
		newSoldier.modify(dh.getSoldier());
		newMonopoly.modify(dh.getMonopoly());
		newYearOfPlenty.modify(dh.getYearOfPlenty());
		newMonument.modify(dh.getMonument());
		newRoadBuild.modify(dh.getRoadBuild());
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
		} catch (BankException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * @throws BankException 
	 * @pre no preconditions
	 * @post any DevelopmentCard objects contained in this classes array
	 *  is transfered to the D. Card array of the super class.
	 */
	
	public void transfer() throws BankException{
		soldier.modify(newSoldier.getQuantity());
		monopoly.modify(newMonopoly.getQuantity());
		yearOfPlenty.modify(newYearOfPlenty.getQuantity());
		roadBuild.modify(newRoadBuild.getQuantity());
		monument.modify(newMonument.getQuantity());
		clear();
	}
	
	/**
	 * sets player bank attributes to their corresponding values in 
	 * the parameter dh
	 * 
	 * @param dh
	 * @throws BankException
	 */
	public void initNewDC(DevelopmentHand dh) throws BankException{
		newSoldier.setQuantity(dh.getSoldier());
		newMonopoly.setQuantity(dh.getMonopoly());
		newYearOfPlenty.setQuantity(dh.getYearOfPlenty());
		newMonument.setQuantity(dh.getMonument());
		newRoadBuild.setQuantity(dh.getRoadBuild());
	}
	
	

}
