package shared.model.player;
/**
 * 
 * @author Spencer Krieger
 * keeps track of the number of roads a player has and who has the longest road
 */
public class LongestRoad {
	
	private int numRoads;
	private boolean hasLongestRoad;
	
	public LongestRoad(){
		numRoads = 0;
		hasLongestRoad = false;
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
