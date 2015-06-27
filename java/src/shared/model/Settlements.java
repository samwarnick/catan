package shared.model;

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
			throw new Exception();
	}

	public int getSettlementsLeft() {
		return settlementsLeft;
	}
	
	
}
