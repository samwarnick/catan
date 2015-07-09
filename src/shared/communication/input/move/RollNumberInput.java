package shared.communication.input.move;

/**
 * This class contains the rollNumber method name, the number rolled and the playerIndex for the player doing the action.
 * @author Matt
 * 
 */
public class RollNumberInput extends MoveInput {

	private int numberRolled;
	private String type = "rollNumber";

	public RollNumberInput(int playerIndex, int numberRolled) {
		super("/rollNumber", playerIndex);
		this.numberRolled = numberRolled;
	}

	public int getNumberRolled() {
		return numberRolled;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
		
}
