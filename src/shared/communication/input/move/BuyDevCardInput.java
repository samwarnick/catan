package shared.communication.input.move;

/**
 * 
 * @author Matt
 * This class contains the buyDevCard method name and the playerIndex for the player doing the action.
 * 
 */
public class BuyDevCardInput extends MoveInput {

	public BuyDevCardInput(int playerIndex) {
		super("buyDevCard", playerIndex);
	}

}
