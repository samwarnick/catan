package shared.model.board;
import java.io.Serializable;


/**
 * 
 * @author jordanJohnson
 *
 */
import shared.locations.VertexLocation;

@SuppressWarnings("serial")
public class Settlement extends Vertex implements Serializable{
	
	
	public Settlement() {
		
	}
	
	public Settlement(PlayerID owner, VertexLocation location)
	{
		super(owner, location);
	}

}
