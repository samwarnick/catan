package shared.model.board;
/**
 * 
 * @author jordanJohnson
 *
 */

import shared.locations.EdgeLocation;

public class Road extends Edge{
	
	public Road(PlayerID owner, EdgeLocation location) {
		super(owner, location);
	}

}
