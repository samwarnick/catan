package shared.model.player;

import java.util.ArrayList;

/**
 * 
 * @author Spencer Krieger
 *
 */
public class Roads {
	
	private List<Road> roads;
	private int roadsLeft;
	
	public Roads(){
		roads = new ArrayList<Road>();
		roadsLeft = 15;
	}

	public List<Road> getRoads() {
		return roads;
	}

	public void addRoad(Road road) {
		if (roadsLeft > 0)
		{
			roads.Add(road);
			roadsLeft--;
		}
		else
			throw new NoRoadsLeftException();
		
	}

	public int getRoadsLeft() {
		return roadsLeft;
	}

}

@SuppressWarnings("serial")
class NoRoadsLeftException extends Exception{
	
}