package shared.communication.input;

/**
 * @author isaachartung
 *
 */
public class MoveFinishTurn extends MoveInput {
	
	public MoveFinishTurn(int playerIndex){
		super("/moves/finishTurn", playerIndex);
	}
}
