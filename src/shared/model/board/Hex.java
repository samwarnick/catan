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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((edges == null) ? 0 : edges.hashCode());
		result = prime * result
				+ ((landType == null) ? 0 : landType.hashCode());
		result = prime * result
				+ ((location == null) ? 0 : location.hashCode());
		result = prime * result
				+ ((vertices == null) ? 0 : vertices.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Hex other = (Hex) obj;
		if (edges == null) {
			if (other.edges != null)
				return false;
		} else if (!edges.equals(other.edges))
			return false;
		if (landType != other.landType)
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (vertices == null) {
			if (other.vertices != null)
				return false;
		} else if (!vertices.equals(other.vertices))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Hex [landType=" + landType + ", location=" + location
				+ ", vertices=" + vertices + ", edges=" + edges + "]";
	}
}
