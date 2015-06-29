package shared.model.board;

import shared.definitions.PortType;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;
import shared.model.ratios.TradeRatio;

public class PortHex extends WaterHex {

	private PortType portType;
	private TradeRatio ratio;
	private VertexLocation validVertex1;
	private VertexLocation validVertex2;
	private EdgeLocation orientation;
	
	public PortHex(HexLocation location, PortType portType, TradeRatio ratio, 
			VertexLocation validVertex1, VertexLocation validVertex2, EdgeLocation orientation) {
		super(location);
		this.portType = portType;
		this.ratio = ratio;
		this.validVertex1 = validVertex1;
		this.validVertex2 = validVertex2;
		this.orientation = orientation;
	}
	
	// TODO: hashcode, equals, toString
}
