package shared.communication.input.move;

/**
 * 
 * @author Matt
 * This class contains the finishTurn method name and the playerIndex for the player doing the action.
 * 
 */
public class FinishTurnInput extends MoveInput {

	public FinishTurnInput(int playerIndex) {
		super("finishTurn", playerIndex);
	}
	
}
