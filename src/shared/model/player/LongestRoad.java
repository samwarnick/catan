package shared.model.player;
/**
 * 
 * @author Spencer Krieger
 *
 */
public class LongestRoad {
	
	private int numRoads;
	private boolean hasLongestRoad;
	
	public LongestRoad(){
		numRoads = 0;
		hasLongestRoad = false;
	}
	
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
