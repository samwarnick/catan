package shared.communication.input.move;

/**
 * This class contains the finishTurn method name and the playerIndex for the player doing the action.
 * @author Matt
 * 
 */
public class FinishTurnInput extends MoveInput {

	private String type = "finishTurn";

	public FinishTurnInput(int playerIndex) {
		super("/finishTurn", playerIndex);
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}
