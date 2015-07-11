package shared.communication.input.move;

/**
 * This class contains the buyDevCard method name and the playerIndex for the player doing the action.
 * @author Matt
 * 
 */
public class BuyDevCardInput extends MoveInput {
	
	private String type = "buyDevCard";

	public BuyDevCardInput(int playerIndex) {
		super("/buyDevCard", playerIndex);
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
