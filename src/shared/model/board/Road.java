package shared.model.board;
/**
 * 
 * @author jordanJohnson
 *
 */

import java.io.Serializable;

import shared.locations.EdgeLocation;

@SuppressWarnings("serial")
public class Road extends Edge implements Serializable{
	
	public Road() {
		
	}
	
	public Road(PlayerID owner, EdgeLocation location) {
		super(owner, location);
	}

}
