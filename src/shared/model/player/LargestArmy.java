package shared.model.player;

public class LargestArmy {
	
	private int numSoldiers;
	private boolean hasLargestArmy;
	
	public LargestArmy(){
		numSoldiers = 0;
		hasLargestArmy = false;
	}

	public int getNumSoldiers() {
		return numSoldiers;
	}

	public void addSoldiers() {
		++numSoldiers;
	}

	public boolean isHasLargestArmy() {
		return hasLargestArmy;
	}

	public void setHasLargestArmy(boolean hasLargestArmy) {
		this.hasLargestArmy = hasLargestArmy;
	}
	
	

}
