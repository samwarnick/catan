package shared.model.board;

import shared.locations.HexLocation;

public class Robber {

	private HexLocation location;
	
	public Robber(HexLocation location) {
		this.location = location;
	}
	
	/**
	 * Moves the robber to a new hex, if that hex is not the same hex it is already on
	 * @param newLocation
	 * @throws InvalidRobberLocationException
	 */
	public void moveRobber(HexLocation newLocation) throws InvalidRobberLocationException{
		if (location != newLocation) {
			location = newLocation;
		} else {
			throw new InvalidRobberLocationException();
		}
	}

	public HexLocation getLocation() {
		return location;
	}

	@Override
	public String toString() {
		return "Robber [location=" + location + "]";
	}
}

class InvalidRobberLocationException extends Exception {
	
	public InvalidRobberLocationException() {
		super("Cannot move robber to same hex");
	}
	
}
