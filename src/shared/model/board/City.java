package shared.model.board;

import PlayerID;
import shared.locations.VertexLocation;

public class City extends Vertex{
	
	public City(PlayerID owner, VertexLocation location)
	{
		super(owner, location);
	}

}
