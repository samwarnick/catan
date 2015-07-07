package shared.model.player;




import shared.model.board.Settlement;

/**
 * 
 * @author Spencer Krieger
 * 
 *Contains a list of Settlement and keeps track of how many settlements the player has left.
 */
public class Settlements {

	
	private int settlementsLeft;
	
	public Settlements(){
		settlementsLeft = 5;
	}


	/**
	 * @pre none
	 * @param settlement
	 * @throws NoSettlementsLeftException
	 * @throws SettlementAlreadyThereException 
	 * @post adds a Settlement, or throws NoSettlementsLeftException.
	 */
	public void buildSettlement(Settlement settlement) throws NoSettlementsLeftException {
		if(settlementsLeft > 0){
				settlementsLeft--;
		}
		else
			throw new NoSettlementsLeftException();
	}

	public int getSettlementsLeft() {
		return settlementsLeft;
	}
	
	/**
	 * @pre settlement(passed in as a parameter) must have been previously added to Settlements
	 * @param settlement
	 * @throws TooManySettlementsException 
	 * @throws NoSettlementFoundException 
	 * @post removes the Settlement specified in the parameter.
	 */
	public void subtractSettlement(Settlement settlement) throws TooManySettlementsException {
		if (settlementsLeft < 6){
			settlementsLeft++;
		}
		else
			throw new TooManySettlementsException();
				
	}
	
	
	
	
}
@SuppressWarnings("serial")
class NoSettlementsLeftException extends Exception{
	
}
@SuppressWarnings("serial")
class TooManySettlementsException extends Exception{
	
}
