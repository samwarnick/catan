package shared.model.board;
/**
 * 
 * @author jordanJohnson
 *
 */
import shared.locations.VertexLocation;

public class Settlement extends Vertex{
	
	public Settlement() {
		
	}
	
	public Settlement(PlayerID owner, VertexLocation location)
	{
		super(owner, location);
	}

}
