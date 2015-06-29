package shared.model.player;

import java.util.ArrayList;

public class Settlements {

	
	private List<Settlement> settlements;
	private int settlementsLeft;
	
	public Settlements(){
		settlements = new ArrayList<Settlement>();
		settlementsLeft = 5;
	}

	public List<Settlement> getSettlements() {
		return settlements;
	}

	public void buildSettlement(Settlement settlement) {
		if(settlementsLeft > 0){
			settlements.Add(settlement);
			settlementsLeft--;
		}
		else
			throw new NoSettlementsLeftException();
	}

	public int getSettlementsLeft() {
		return settlementsLeft;
	}
	
	public void subtractSettlement(Settlement settlement){
		settlements.Remove(settlement);
		settlementsLeft++;
	}
	
	
	
	
}
@SuppressWarnings("serial")
class NoSettlementsLeftException extends Exception{
	
}