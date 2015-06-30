package shared.model.board;

import java.util.List;

import shared.definitions.HexType;
import shared.locations.HexLocation;

public class Hex {

	private HexType landType;
	private HexLocation location;
	private List<Vertex> vertices;
	private List<Edge> edges;
	
	public Hex(HexType landType, HexLocation location) {
		this.landType = landType;
		this.location = location;
	}
	
	// TODO: hashcode, equals, toString
}
