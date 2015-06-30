package shared.model.player;

import java.util.ArrayList;


import shared.model.board.Road;


/**
 * 
 * @author Spencer Krieger
 *
 */
public class Roads {
	
	private ArrayList<Road> roads;
	private int roadsLeft;
	
	public Roads(){
		roads = new ArrayList<Road>();
		roadsLeft = 15;
	}

	public ArrayList<Road> getRoads() {
		return roads;
	}

	public void addRoad(Road road) throws NoRoadsLeftException {
		if (roadsLeft > 0)
		{
			roads.add(road);
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