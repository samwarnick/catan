package shared.model.bank;

import shared.definitions.ResourceType;

/**
 * This class represents a collection of resource cards, each resource type represented by an integer.
 * 0 means the card amount will not change. A negative value means the player is gaining one or more of that card. A positive number means the player is losing one of more of that card.
 * @author Matt
 * 
 */
public class ResourceHand {

	private int brick;
	private int wood;
	private int sheep;
	private int wheat;
	private int ore;
	
	public ResourceHand() {
		brick = 0;
		wood = 0;
		sheep = 0;
		wheat = 0;
		ore = 0;
	}
	
	public ResourceHand(int brick, int wood, int sheep, int wheat, int ore){
		this.wood = wood;
		this.brick = brick;
		this.sheep = sheep;
		this.wheat = wheat;
		this.ore = ore;
	}
	
	public ResourceHand(ResourceType type){
		brick = 0;
		wood = 0;
		sheep = 0;
		wheat = 0;
		ore = 0;
		switch (type){
		case BRICK:
			brick = 1;
			break;
		case ORE:
			ore = 1;
			break;
		case SHEEP:
			sheep = 1;
			break;
		case WHEAT:
			wheat = 1;
			break;
		case WOOD:
			wood = 1;
			break;
		default:
			break;
			
		
		}
	}

	public int getBrick() {
		return brick;
	}

	public void setBrick(int brick) {
		this.brick = brick;
	}

	public int getWood() {
		return wood;
	}

	public void setWood(int wood) {
		this.wood = wood;
	}

	public int getSheep() {
		return sheep;
	}

	public void setSheep(int sheep) {
		this.sheep = sheep;
	}

	public int getWheat() {
		return wheat;
	}

	public void setWheat(int wheat) {
		this.wheat = wheat;
	}

	public int getOre() {
		return ore;
	}

	public void setOre(int ore) {
		this.ore = ore;
	}

	@Override
	public String toString() {
		return "ResourceHand [brick=" + brick + ", wood=" + wood + ", sheep="
				+ sheep + ", wheat=" + wheat + ", ore=" + ore + "]";
	}
	
	
}
