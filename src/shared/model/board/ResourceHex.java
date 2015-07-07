package shared.model.board;

import shared.definitions.HexType;
import shared.locations.HexLocation;

public class ResourceHex extends Hex{

	private int numberToken;

	public ResourceHex(HexType landType, HexLocation location, int numberToken) {
		super(landType, location);
		this.numberToken = numberToken;
	}
	
	public int getNumberToken() {
		return numberToken;
	}

	public void setNumberToken(int numberToken) {
		this.numberToken = numberToken;
	}
}