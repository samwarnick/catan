package shared.communication.input;

/**
 * @author isaachartung
 * 
 * this is the input object for an FinishTurn command
 *
 */
public class MoveFinishTurn extends MoveInput {
	
	public MoveFinishTurn(int playerIndex){
		super("/moves/finishTurn", playerIndex);
	}
}
