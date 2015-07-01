package shared.model.board;
/**
 * 
 * @author jordanJohnson
 *
 */
import shared.locations.VertexLocation;

public class City extends Vertex{
	
	public City(PlayerID owner, VertexLocation location)
	{
		super(owner, location);
	}

}
