package shared.communication.input;

/**
 * @author isaachartung
 *
 */
public class MoveBuyDevCard extends MoveInput{
	
	public MoveBuyDevCard(int playerIndex){
		super("/moves/buyDevCard", playerIndex);
	}

}
