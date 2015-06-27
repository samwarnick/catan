package shared.model;

import java.util.ArrayList;

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
			throw new Exception();
		
	}

	public int getRoadsLeft() {
		return roadsLeft;
	}

}
