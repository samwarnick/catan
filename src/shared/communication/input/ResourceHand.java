package shared.communication.input;

/**
 * @author isaachartung
 *This class keeps track of the number of each resource card a player has.
 */
public class ResourceHand {
	
	private int ore = 0;
	private int sheep = 0;
	private int wood = 0;
	private int brick = 0;
	private int wheat = 0;
	
	public int getOre() {
		return ore;
	}
	public int getSheep() {
		return sheep;
	}
	public int getWood() {
		return wood;
	}
	public int getBrick() {
		return brick;
	}
	public int getWheat() {
		return wheat;
	}
	public void setOre(int ore) {
		this.ore = ore;
	}
	public void setSheep(int sheep) {
		this.sheep = sheep;
	}
	public void setWood(int wood) {
		this.wood = wood;
	}
	public void setBrick(int brick) {
		this.brick = brick;
	}
	public void setWheat(int wheat) {
		this.wheat = wheat;
	}	
	

}
