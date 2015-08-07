package shared.model.board;

import shared.definitions.HexType;
import shared.locations.HexLocation;

@SuppressWarnings("serial")
public class WaterHex extends Hex {

	public WaterHex() {
		
	}
	
	public WaterHex(HexLocation location) {
		super(HexType.WATER, location);
	}
}
