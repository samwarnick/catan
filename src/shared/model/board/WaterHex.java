package shared.model.board;

import shared.definitions.HexType;
import shared.locations.HexLocation;

public class WaterHex extends Hex {

	public WaterHex(HexLocation location) {
		super(HexType.WATER, location);
	}
}
