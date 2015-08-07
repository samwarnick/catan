package shared.model.player;

import java.io.Serializable;

/**
 * 
 * @author Spencer Krieger
 * keeps track of the number of soldiers a player has and who has the largest army
 */
@SuppressWarnings("serial")
public class LargestArmy implements Serializable{
	
	private int numSoldiers;
	private boolean hasLargestArmy;
	
	public LargestArmy(){
		numSoldiers = 0;
		hasLargestArmy = false;
	}

	public LargestArmy(int numSoldiers) {
		this.numSoldiers = numSoldiers;
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
