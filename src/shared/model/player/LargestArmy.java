package shared.model.player;

/**
 * 
 * @author Spencer Krieger
 *
 */
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

	public void addSoldier() {
		++numSoldiers;
	}

	public boolean getHasLargestArmy() {
		return hasLargestArmy;
	}

	public void setHasLargestArmy(boolean hasLargestArmy) {
		this.hasLargestArmy = hasLargestArmy;
	}
	
	

}
