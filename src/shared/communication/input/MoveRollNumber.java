package shared.communication.input;

/**
 * @author isaachartung
 * this is the input object for an RollNumber command
 *
 */
public class MoveRollNumber extends MoveInput{
	
	private int rollNumber;

	public MoveRollNumber(int playerID, int rollNumber){
		super("/moves/rollNumber", playerID);
		this.rollNumber = rollNumber;
	}

	public int getRollNumber() {
		return rollNumber;
	}

	public void setRollNumber(int rollNumber) {
		this.rollNumber = rollNumber;
	}

}
