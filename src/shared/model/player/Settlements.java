package shared.model.player;

import java.util.ArrayList;



import shared.model.board.Settlement;

/**
 * 
 * @author Spencer Krieger
 * 
 *Contains a list of Settlement and keeps track of how many settlements the player has left.
 */
public class Settlements {

	
	private ArrayList<Settlement> settlements;
	private int settlementsLeft;
	
	public Settlements(){
		settlements = new ArrayList<Settlement>();
		settlementsLeft = 5;
	}

	public ArrayList<Settlement> getSettlements() {
		return settlements;
	}

	/**
	 * @pre none
	 * @param settlement
	 * @throws NoSettlementsLeftException
	 * @post adds a Settlement, or throws NoSettlementsLeftException.
	 */
	public void buildSettlement(Settlement settlement) throws NoSettlementsLeftException {
		if(settlementsLeft > 0){
			settlements.add(settlement);
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
	 * @post removes the Settlement specified in the parameter.
	 */
	public void subtractSettlement(Settlement settlement){
		settlements.remove(settlement);
		settlementsLeft++;
	}
	
	
	
	
}
@SuppressWarnings("serial")
class NoSettlementsLeftException extends Exception{
	
}