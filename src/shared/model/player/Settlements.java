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
	 * @throws SettlementAlreadyThereException 
	 * @post adds a Settlement, or throws NoSettlementsLeftException.
	 */
	public void buildSettlement(Settlement settlement) throws NoSettlementsLeftException, SettlementAlreadyThereException {
		if(settlementsLeft > 0){
			boolean alreadythere = false;
			for (Settlement s : settlements){
				if (s.getLocation().equals(settlement.getLocation()))
					alreadythere = true;
			}
			if (!alreadythere){
				settlements.add(settlement);
				settlementsLeft--;
			}
			else
				throw new SettlementAlreadyThereException();
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
	 * @throws NoSettlementFoundException 
	 * @post removes the Settlement specified in the parameter.
	 */
	public void subtractSettlement(Settlement settlement) throws NoSettlementFoundException{
		boolean removed = false;
		for (int i = 0;i < settlements.size();i++){
			if (settlements.get(i).getLocation().equals(settlement.getLocation()) && !removed){
				settlements.remove(i);
				settlementsLeft++;
				removed = true;
			}
		}
		if(removed){
			throw new NoSettlementFoundException();
		}
		
	}
	
	
	
	
}
@SuppressWarnings("serial")
class NoSettlementsLeftException extends Exception{
	
}
@SuppressWarnings("serial")
class NoSettlementFoundException extends Exception{
	
}
@SuppressWarnings("serial")
class SettlementAlreadyThereException extends Exception{
	
}