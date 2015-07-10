package shared.locations;

import java.util.ArrayList;

/**
 * Represents the location of a hex on a hex map
 */
public class HexLocation
{
	
	private int x;
	private int y;
	
	public HexLocation(int x, int y)
	{
		setX(x);
		setY(y);
	}
	
	public int getX()
	{
		return x;
	}
	
	private void setX(int x)
	{
		this.x = x;
	}
	
	public int getY()
	{
		return y;
	}
	
	private void setY(int y)
	{
		this.y = y;
	}
	
	@Override
	public String toString()
	{
		return "HexLocation [x=" + x + ", y=" + y + "]";
	}
	
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}
	
	@Override
	public boolean equals(Object obj)
	{
		if(this == obj)
			return true;
		if(obj == null)
			return false;
		if(getClass() != obj.getClass())
			return false;
		HexLocation other = (HexLocation)obj;
		if(x != other.x)
			return false;
		if(y != other.y)
			return false;
		return true;
	}
	
	public HexLocation getNeighborLoc(EdgeDirection dir)
	{
		switch (dir)
		{
			case NorthWest:
				return new HexLocation(x - 1, y);
			case North:
				return new HexLocation(x, y - 1);
			case NorthEast:
				return new HexLocation(x + 1, y - 1);
			case SouthWest:
				return new HexLocation(x - 1, y + 1);
			case South:
				return new HexLocation(x, y + 1);
			case SouthEast:
				return new HexLocation(x + 1, y);
			default:
				assert false;
				return null;
		}
	}
	
	public boolean isAdjacent(HexLocation loc) {
		if(this.equals(loc)) return false;
		if(Math.abs(this.x - loc.x) <= 1 && this.y - loc.y == 0) return true;
		if(Math.abs(this.y - loc.y) <= 1 && this.x - loc.x == 0) return true;
		if(this.x - loc.x == 1 && this.y - loc.y == -1) return true;
		if(this.x - loc.x == -1 && this.y - loc.y == 1) return true;
		
		return false;
	}
	
	/**
	 * 
	 * @param loc
	 * @return a list of HexLocations that are not adjacent to loc and are valid landHex locations, list can be empty if there are no such locations
	 */
	public ArrayList<HexLocation> getLocsNotAdjacentTo(HexLocation loc) {
		ArrayList<HexLocation> newLocs = new ArrayList<HexLocation>();
		int dx = this.x - loc.x;
		int dy = this.y - loc.y;
		HexLocation newLoc;
		
		if(dx == 0 && dy == 1) {
			newLoc = new HexLocation(this.x - 1, this.y + 1);
			if(newLoc.isValidLandHexLocation()) {
				newLocs.add(newLoc);
			}
			newLoc = new HexLocation(this.x + 1, this.y);
			if(newLoc.isValidLandHexLocation()) {
				newLocs.add(newLoc);
			}
			newLoc = new HexLocation(this.x, this.y + 1);
			if(newLoc.isValidLandHexLocation()) {
				newLocs.add(newLoc);
			}
		}
		else if(dx == 0 && dy == -1) {
			newLoc = new HexLocation(this.x - 1, this.y);
			if(newLoc.isValidLandHexLocation()) {
				newLocs.add(newLoc);
			}
			newLoc = new HexLocation(this.x + 1, this.y - 1);
			if(newLoc.isValidLandHexLocation()) {
				newLocs.add(newLoc);
			}
			newLoc = new HexLocation(this.x, this.y - 1);
			if(newLoc.isValidLandHexLocation()) {
				newLocs.add(newLoc);
			}
		}
		else if(dx == 1 && dy == 0) {
			newLoc = new HexLocation(this.x, this.y + 1);
			if(newLoc.isValidLandHexLocation()) {
				newLocs.add(newLoc);
			}
			newLoc = new HexLocation(this.x + 1, this.y - 1);
			if(newLoc.isValidLandHexLocation()) {
				newLocs.add(newLoc);
			}
			newLoc = new HexLocation(this.x + 1, this.y);
			if(newLoc.isValidLandHexLocation()) {
				newLocs.add(newLoc);
			}
		}
		else if(dx == -1 && dy == 0) {
			newLoc = new HexLocation(this.x, this.y - 1);
			if(newLoc.isValidLandHexLocation()) {
				newLocs.add(newLoc);
			}
			newLoc = new HexLocation(this.x - 1, this.y + 1);
			if(newLoc.isValidLandHexLocation()) {
				newLocs.add(newLoc);
			}
			newLoc = new HexLocation(this.x - 1, this.y);
			if(newLoc.isValidLandHexLocation()) {
				newLocs.add(newLoc);
			}
		}
		else if(dx == 1 && dy == -1) {
			newLoc = new HexLocation(this.x, this.y - 1);
			if(newLoc.isValidLandHexLocation()) {
				newLocs.add(newLoc);
			}
			newLoc = new HexLocation(this.x + 1, this.y);
			if(newLoc.isValidLandHexLocation()) {
				newLocs.add(newLoc);
			}
			newLoc = new HexLocation(this.x + 1, this.y - 1);
			if(newLoc.isValidLandHexLocation()) {
				newLocs.add(newLoc);
			}
		}
		else if(dx == -1 && dy == 1) {
			newLoc = new HexLocation(this.x, this.y + 1);
			if(newLoc.isValidLandHexLocation()) {
				newLocs.add(newLoc);
			}
			newLoc = new HexLocation(this.x - 1, this.y);
			if(newLoc.isValidLandHexLocation()) {
				newLocs.add(newLoc);
			}
			newLoc = new HexLocation(this.x - 1, this.y + 1);
			if(newLoc.isValidLandHexLocation()) {
				newLocs.add(newLoc);
			}
		}
		
		return newLocs;
	}
	
	public boolean isValidLandHexLocation() {
		if(x > 2 || x < -2) return false;
		if(y > 2 || y < -2) return false;
		
		if(x == 0 || y == 0) return true;
		if(x == -2 && y >= 0) return true;
		if(x == -1 && y >= -1) return true;
		if(x == 1 && y <= 1) return true;
		if(x == 2 && y <= 0) return true;
		
		return false;
	}
}

