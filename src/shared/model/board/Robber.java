package shared.model.board;

import shared.locations.HexLocation;

/**
 * 
 * @author samwarnick
 *
 */
public class Robber {

	private HexLocation location;
	
	public Robber(HexLocation location) {
		this.location = location;
	}
	
	/**
	 * Moves the robber to a new hex, if that hex is not the same hex it is already on
	 * @param newLocation the new location to move the robber to
	 * @exception InvalidRobberLocationException if newLocation is an invalid hex (i.e. the same hex the robber is on)
	 * @pre newLocation is not the desert hex
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

	public void setLocation(HexLocation location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "Robber [location=" + location + "]";
	}
}

/**
 * 
 * @author samwarnick
 *
 */
@SuppressWarnings("serial")
class InvalidRobberLocationException extends Exception {
	
	public InvalidRobberLocationException() {
		super("Cannot move robber to same hex");
	}
	
	public InvalidRobberLocationException(String message) {
		super(message);
	}
	
}
