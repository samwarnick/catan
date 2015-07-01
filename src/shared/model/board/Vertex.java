package shared.model.board;
/**
 * 
 * @author jordanJohnson
 *
 */
import shared.locations.VertexLocation;

public class Vertex {
	private PlayerID owner;
	private VertexLocation location;

	
	public Vertex(PlayerID owner, VertexLocation location) {
		this.owner = owner;
		this.location = location;
	}
	public PlayerID getOwner() {
		return owner;
	}
	public void setOwner(PlayerID owner) {
		this.owner = owner;
	}
	public VertexLocation getLocation() {
		return location;
	}
	public void setLocation(VertexLocation location) {
		this.location = location;
	}

}
