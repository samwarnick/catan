package shared.model.board;
/**
 * 
 * @author jordanJohnson
 *
 */
import shared.locations.EdgeLocation;

public class Edge {

	private PlayerID owner;
	private EdgeLocation location;
	
	public Edge() {
		owner = null;
		location = null;
	}
	
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
