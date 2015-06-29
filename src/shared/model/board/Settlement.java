package shared.model.board;

import PlayerID;
import shared.locations.VertexLocation;

public class Settlement extends Vertex{
	
	public Settlement(PlayerID owner, VertexLocation location)
	{
		super(owner, location);
	}

}
