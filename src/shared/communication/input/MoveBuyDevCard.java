package shared.communication.input;

/**
 * @author isaachartung
 *
 *this is the input object for an BuyDevCard command
 *
 */
public class MoveBuyDevCard extends MoveInput{
	
	public MoveBuyDevCard(int playerIndex){
		super("/moves/buyDevCard", playerIndex);
	}

}
