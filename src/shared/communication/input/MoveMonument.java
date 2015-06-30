package shared.communication.input;

/**
 * @author isaachartung
 *
 */
public class MoveMonument extends MoveInput {
	
	public MoveMonument(int playerIndex){
		super("/moves/Monument", playerIndex);
	}

}
