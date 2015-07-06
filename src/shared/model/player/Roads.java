package shared.model.player;

import java.util.ArrayList;



import shared.model.board.Road;


/**
 * 
 * @author Spencer Krieger
 * 
 * Contains a list of Road and keeps track of how many roads the player has left.
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
	
	/**
	 * @pre none
	 * @param road
	 * @throws NoRoadsLeftException
	 * @throws RoadAlreadyThereException 
	 * @post adds a Road, or throws NoRoadsLeftException.
	 */
	public void addRoad(Road road) throws NoRoadsLeftException, RoadAlreadyThereException {
		if (roadsLeft > 0)
		{
			boolean alreadyThere = false;
			for (Road r : roads){
				if (r.getLocation().equals(road.getLocation()))
					alreadyThere = true;
			}
			if (!alreadyThere){
				roads.add(road);
				roadsLeft--;
			}
			else 
				throw new RoadAlreadyThereException();
			
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
@SuppressWarnings("serial")
class RoadAlreadyThereException extends Exception{
	
}