package shared.model.player;

import java.io.Serializable;

/**
 * 
 * @author Spencer Krieger
 * 
 * Contains a list of Road and keeps track of how many roads the player has left.
 */
@SuppressWarnings("serial")
public class Roads implements Serializable{
	
	private int roadsLeft;
	
	public Roads(){
		roadsLeft = 15;
	}
	
	public Roads(int roadsLeft) {
		this.roadsLeft = roadsLeft;
	}

	
	/**
	 * @pre none
	 * @param road
	 * @throws NoRoadsLeftException
	 * @post adds a Road, or throws NoRoadsLeftException.
	 */

	public void buildRoad() throws Exception {

		if (roadsLeft > 0)
		{
			roadsLeft--;
		}
		else
			throw new Exception("NoRoadsLeft");
		
	}

	public int getRoadsLeft() {
		return roadsLeft;
	}

}
