package shared.model.player;

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
	
	public Settlements(int settlementsLeft) {
		this.settlementsLeft = settlementsLeft;
	}


	/**
	 * @pre none
	 * @param settlement
	 * @throws NoSettlementsLeftException
	 * @throws SettlementAlreadyThereException 
	 * @post adds a Settlement, or throws NoSettlementsLeftException.
	 */

	public void buildSettlement() throws Exception {
		if(settlementsLeft > 0){
				settlementsLeft--;
		}
		else
			throw new Exception("NoSettlementsLeft");
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
	public void subtractSettlement() throws Exception {
		if (settlementsLeft < 5){
			settlementsLeft++;
		}
		else
			throw new Exception("TooManySettlements");
				
	}
	
	
	
	
}
