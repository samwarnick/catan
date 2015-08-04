package shared.communication.input.move;

/**
 * This class contains the buyDevCard method name and the playerIndex for the player doing the action.
 * @author Matt
 * 
 */
public class BuyDevCardInput extends MoveInput {

	public BuyDevCardInput(int playerIndex) {
		super("/buyDevCard", playerIndex);
		super.setType("buyDevCard");
	}
	
	public BuyDevCardInput() {
		super("/buyDevCard", -1);
	}

}
