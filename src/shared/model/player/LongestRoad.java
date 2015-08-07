package shared.model.player;

import java.io.Serializable;

/**
 * 
 * @author Spencer Krieger
 * keeps track of the number of roads a player has and who has the longest road
 */
@SuppressWarnings("serial")
public class LongestRoad implements Serializable{
	
	private int numRoads;
	private boolean hasLongestRoad;
	
	public LongestRoad(){
		numRoads = 0;
		hasLongestRoad = false;
	}
	
	public LongestRoad(int numRoads) {
		this.numRoads = numRoads;
	}
	/**
	 * @pre none
	 * @post increases numRoads.
	 */
	public void addRoad(){
		numRoads++;
	}
	
	public int getNumRoads(){
		return numRoads;
	}

	public boolean isHasLongestRoad() {
		return hasLongestRoad;
	}

	public void setHasLongestRoad(boolean hasLongestRoad) {
		this.hasLongestRoad = hasLongestRoad;
	}

}
