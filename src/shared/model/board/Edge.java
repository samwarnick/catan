package shared.model.board;

import shared.locations.EdgeLocation;

public class Edge {

	private PlayerID owner;
	private EdgeLocation location;
	
	
	public Edge(PlayerID owner, EdgeLocation location) {
		this.owner = owner;
		this.location = location;
	}
	public PlayerID getOwner() {
		return owner;
	}
	public void setOwner(PlayerID owner) {
		this.owner = owner;
	}
	public EdgeLocation getLocation() {
		return location;
	}
	public void setLocation(EdgeLocation location) {
		this.location = location;
	}

}
