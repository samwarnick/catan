package shared.model.player;

import java.util.ArrayList;

import shared.model.board.Settlement;
/**
 * 
 * @author Spencer Krieger
 *
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
	
	
}
@SuppressWarnings("serial")
class NoSettlementsLeftException extends Exception{
	
}