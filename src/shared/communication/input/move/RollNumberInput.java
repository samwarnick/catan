package shared.communication.input.move;

/**
 * This class contains the rollNumber method name, the number rolled and the playerIndex for the player doing the action.
 * @author Matt
 * 
 */
public class RollNumberInput extends MoveInput {

	private int number;
	private String type = "rollNumber";

	public RollNumberInput(int playerIndex, int number) {
		super("/rollNumber", playerIndex);
		this.number = number;
	}
	
	public RollNumberInput() {
		super("/rollNumber", -1);
		this.number = -1;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
		
}
