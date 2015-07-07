package shared.model.board;

import shared.definitions.PortType;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;
import shared.model.ratios.TradeRatio;

public class PortHex extends WaterHex {

	private PortType portType;
	private int ratio;
	private VertexLocation validVertex1;
	private VertexLocation validVertex2;
	private EdgeLocation orientation;
	
	public PortHex(HexLocation location, PortType portType, int ratio, 
			VertexLocation validVertex1, VertexLocation validVertex2, EdgeLocation orientation) {
		super(location);
		this.portType = portType;
		this.ratio = ratio;
		this.validVertex1 = validVertex1;
		this.validVertex2 = validVertex2;
		this.orientation = orientation;
	}

	// TODO: hascode, equals
	
	@Override
	public String toString() {
		return "PortHex [portType=" + portType + ", ratio=" + ratio
				+ ", validVertex1=" + validVertex1 + ", validVertex2="
				+ validVertex2 + ", orientation=" + orientation + "]";
	}
}
