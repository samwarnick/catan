package shared.model.board;

import shared.definitions.PortType;
import shared.locations.EdgeDirection;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
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
	
	
}
