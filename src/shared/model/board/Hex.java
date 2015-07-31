package shared.model.board;

import shared.definitions.HexType;
import shared.locations.HexLocation;

public class Hex {

	private HexType landType;
	private HexLocation location;
	
	public Hex(HexType landType, HexLocation location) {
		this.landType = landType;
		this.location = location;
	}

	public HexType getLandType() {
		return landType;
	}

	public void setLandType(HexType landType) {
		this.landType = landType;
	}

	public HexLocation getLocation() {
		return location;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((landType == null) ? 0 : landType.hashCode());
		result = prime * result
				+ ((location == null) ? 0 : location.hashCode());
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
		if (landType != other.landType)
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		return true;
	}
	
	
}
