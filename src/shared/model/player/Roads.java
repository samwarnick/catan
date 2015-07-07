package shared.model.player;




import shared.model.board.Road;


/**
 * 
 * @author Spencer Krieger
 * 
 * Contains a list of Road and keeps track of how many roads the player has left.
 */
public class Roads {
	
	private int roadsLeft;
	
	public Roads(){
		roadsLeft = 15;
	}

	
	/**
	 * @pre none
	 * @param road
	 * @throws NoRoadsLeftException
	 * @throws RoadAlreadyThereException 
	 * @post adds a Road, or throws NoRoadsLeftException.
	 */
	public void addRoad(Road road) throws NoRoadsLeftException {
		if (roadsLeft > 0)
		{
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
