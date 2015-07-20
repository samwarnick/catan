package shared.model.board;

import java.util.ArrayList;
import java.util.List;

import shared.definitions.PortType;
import shared.locations.EdgeDirection;
import shared.locations.HexLocation;
import shared.locations.VertexDirection;
import shared.locations.VertexLocation;

public class PortHex extends WaterHex {

	private PortType portType;
	private int ratio;
	private EdgeDirection orientation;
	
	public PortHex(HexLocation location, PortType portType, int ratio, EdgeDirection orientation) {
		super(location);
		this.portType = portType;
		this.ratio = ratio;
		this.orientation = orientation;
	}
	
	public PortType getPortType() {
		return portType;
	}

	public void setPortType(PortType portType) {
		this.portType = portType;
	}

	public int getRatio() {
		return ratio;
	}

	public void setRatio(int ratio) {
		this.ratio = ratio;
	}

	public EdgeDirection getOrientation() {
		return orientation;
	}

	public void setOrientation(EdgeDirection orientation) {
		this.orientation = orientation;
	}
	
	public List<VertexLocation> getVertices() {
		ArrayList<VertexLocation> vertices = new ArrayList<VertexLocation>();
		VertexLocation loc;
		switch (orientation) {
		case North:
			loc = new VertexLocation(getLocation(), VertexDirection.NorthWest);
			vertices.add(loc);
			vertices.addAll(loc.getAmbiguousVertices());
			loc = new VertexLocation(getLocation(), VertexDirection.NorthEast);
			vertices.add(loc);
			vertices.addAll(loc.getAmbiguousVertices());
			return vertices;
		case NorthEast:
			loc = new VertexLocation(getLocation(), VertexDirection.NorthEast);
			vertices.add(loc);
			vertices.addAll(loc.getAmbiguousVertices());
			loc = new VertexLocation(getLocation().getNeighborLoc(EdgeDirection.SouthEast), VertexDirection.NorthWest);
			vertices.add(loc);
			vertices.addAll(loc.getAmbiguousVertices());
			return vertices;
		case NorthWest:
			loc = new VertexLocation(getLocation(), VertexDirection.NorthWest);
			vertices.add(loc);
			vertices.addAll(loc.getAmbiguousVertices());
			loc = new VertexLocation(getLocation().getNeighborLoc(EdgeDirection.SouthWest), VertexDirection.NorthEast);
			vertices.add(loc);
			vertices.addAll(loc.getAmbiguousVertices());
			return vertices;
		case South:
			loc = new VertexLocation(getLocation().getNeighborLoc(EdgeDirection.South), VertexDirection.NorthEast);
			vertices.add(loc);
			vertices.addAll(loc.getAmbiguousVertices());
			loc = new VertexLocation(getLocation().getNeighborLoc(EdgeDirection.South), VertexDirection.NorthWest);
			vertices.add(loc);
			vertices.addAll(loc.getAmbiguousVertices());
			return vertices;
		case SouthEast:
			loc = new VertexLocation(getLocation().getNeighborLoc(EdgeDirection.South), VertexDirection.NorthEast);
			vertices.add(loc);
			vertices.addAll(loc.getAmbiguousVertices());
			loc = new VertexLocation(getLocation().getNeighborLoc(EdgeDirection.SouthEast), VertexDirection.NorthWest);
			vertices.add(loc);
			vertices.addAll(loc.getAmbiguousVertices());
			return vertices;
		case SouthWest:
			loc = new VertexLocation(getLocation().getNeighborLoc(EdgeDirection.SouthWest), VertexDirection.NorthEast);
			vertices.add(loc);
			vertices.addAll(loc.getAmbiguousVertices());
			loc = new VertexLocation(getLocation().getNeighborLoc(EdgeDirection.South), VertexDirection.NorthWest);
			vertices.add(loc);
			vertices.addAll(loc.getAmbiguousVertices());
			return vertices;
		default:
			return null;
		}
	}
	
}
