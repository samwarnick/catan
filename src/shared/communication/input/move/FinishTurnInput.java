package shared.communication.input.move;

/**
 * This class contains the finishTurn method name and the playerIndex for the player doing the action.
 * @author Matt
 * 
 */
public class FinishTurnInput extends MoveInput {

	public FinishTurnInput(int playerIndex) {
		super("finishTurn", playerIndex);
	}
	
}
