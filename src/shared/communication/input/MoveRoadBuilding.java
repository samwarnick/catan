package shared.communication.input;

import shared.locations.EdgeLocation;

/**
 * @author isaachartung
 *this is the input object for an RoadBuilding command
 */
public class MoveRoadBuilding extends MoveInput{

	private EdgeLocation first;
	private EdgeLocation second;
	
	public MoveRoadBuilding(int ID, EdgeLocation first,
			EdgeLocation second) {
		super("/moves/Road_Building", ID);
		this.first = first;
		this.second = second;
	}

	public EdgeLocation getFirst() {
		return first;
	}

	public EdgeLocation getSecond() {
		return second;
	}

	public void setFirst(EdgeLocation first) {
		this.first = first;
	}

	public void setSecond(EdgeLocation second) {
		this.second = second;
	}
	
	
}
