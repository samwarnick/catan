package shared.model.board;

import java.io.Serializable;

import shared.definitions.HexType;
import shared.locations.HexLocation;

@SuppressWarnings("serial")
public class ResourceHex extends Hex implements Serializable{

	private int numberToken;

	public ResourceHex() {
		super();
		numberToken = 0;
	}
	
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