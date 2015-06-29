package shared.model.board;


import shared.locations.EdgeLocation;
import PlayerID;

public class Road extends Edge{
	
	public Road(PlayerID owner, EdgeLocation location) {
		super(owner, location);
	}

}
