package shared.model.board;
import java.io.Serializable;


/**
 * 
 * @author jordanJohnson
 *
 */
import shared.locations.VertexLocation;

@SuppressWarnings("serial")
public class City extends Vertex implements Serializable{
	
	public City () {
		
	}
	
	public City(PlayerID owner, VertexLocation location)
	{
		super(owner, location);
	}

}
