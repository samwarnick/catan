package shared.communication.input.move;

/**
 * 
 * @author Matt
 * This class contains the rollNumber method name, the number rolled and the playerIndex for the player doing the action.
 * 
 */
public class RollNumberInput extends MoveInput {

	private int numberRolled;

	public RollNumberInput(int playerIndex, int numberRolled) {
		super("rollNumber", playerIndex);
		this.numberRolled = numberRolled;
	}

	public int getNumberRolled() {
		return numberRolled;
	}
		
}
