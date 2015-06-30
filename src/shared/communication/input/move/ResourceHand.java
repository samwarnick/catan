package shared.communication.input.move;

/**
 * 
 * @author Matt
 * This class represents a collection of resource cards, each resource type represented by an integer.
 * 0 means the card amount will not change. A negative value means the player is gaining one or more of that card. A positive number means the player is losing one of more of that card.
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
	
}
