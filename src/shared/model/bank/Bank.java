package shared.model.bank;

import java.util.ArrayList;

import shared.definitions.DevCardType;
import shared.definitions.ResourceType;

public class Bank {
	
	private ArrayList<ResourceCard> resourceCards = new ArrayList<ResourceCard>();
	private ArrayList<DevelopmentCard> developmentCards = new ArrayList<DevelopmentCard>();;
	private boolean longestArmyCard = false;
	private boolean longestRoadCard = false;
	
	public ArrayList<ResourceCard> getResourceCards() {
		return resourceCards;
	}
	public ArrayList<DevelopmentCard> getDevelopmentCards() {
		return developmentCards;
	}
	public boolean hasLongestArmyCard() {
		return longestArmyCard;
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
	public void setLongestArmyCard(boolean longestArmyCard) {
		this.longestArmyCard = longestArmyCard;
	}
	public void setLongestRoadCard(boolean longestRoadCard) {
		this.longestRoadCard = longestRoadCard;
	}
	
	/**
	 * adds a single resource card to the bank.
	 * 
	 * @param RC
	 */
	
	public void addRC(ResourceCard RC){
		resourceCards.add(RC);
	}
	
	/**
	 * adds a single development card to the bank.
	 * 
	 * @param DC
	 */
	
	public void addDC(DevelopmentCard DC){
		developmentCards.add(DC);
	}
	
	/**
	 * returns a resource card of the type specified by the 
	 * parameter. if not found, it returns null
	 * 
	 * @return
	 */
	
	public ResourceCard getRC(ResourceType rt){
		return null;
	}
	
	/**
	 * returns a Development card of the type specified by the 
	 * parameter. if not found, it returns null
	 * 
	 * @return
	 */
	
	public DevelopmentCard getDC(DevCardType dt){
		return null;
	}
	
	/**
	 * returns true if the player possesses the given quantity of the
	 * specified resource card.
	 * 
	 * @param rt
	 * @param quantity
	 * @return
	 */
	
	public boolean hasRC(ResourceType rt, int quantity){
		return false;
	}
	
	/**
	 * returns true if the player possesses the given quantity of the
	 * specified development card.
	 * 
	 * 
	 * @return
	 */
	
	public boolean hasDC(DevCardType dt, int quantity){
		return false;
	}
	
	
	
	
	

}
