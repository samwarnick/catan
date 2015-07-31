package shared.locations;

import com.fasterxml.jackson.annotation.JsonIgnore;

public enum EdgeDirection
{
	
	NorthWest, North, NorthEast, SouthEast, South, SouthWest;
	
	private EdgeDirection opposite;
	
	static
	{
		NorthWest.opposite = SouthEast;
		North.opposite = South;
		NorthEast.opposite = SouthWest;
		SouthEast.opposite = NorthWest;
		South.opposite = North;
		SouthWest.opposite = NorthEast;
	}
	
	@JsonIgnore public EdgeDirection getOppositeDirection()
	{
		return opposite;
	}
	
	@JsonIgnore public String getAbbreviation() {
		switch (this) {
		case North:
			return "N";
		case NorthEast:
			return "NE";
		case NorthWest:
			return "NW";
		case South:
			return "S";
		case SouthEast:
			return "SE";
		case SouthWest:
			return "SW";
		default:
			return null;
		
		}
	}
}

