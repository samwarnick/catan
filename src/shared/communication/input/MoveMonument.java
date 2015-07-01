package shared.communication.input;

/**
 * @author isaachartung
 *this is the input object for an Monument command
 */
public class MoveMonument extends MoveInput {
	
	public MoveMonument(int playerIndex){
		super("/moves/Monument", playerIndex);
	}

}
