package shared.locations;

import java.util.ArrayList;

/**
 * Represents the location of an edge on a hex map
 */
public class EdgeLocation
{
	
	private HexLocation hexLoc;
	private EdgeDirection dir;
	
	public EdgeLocation(HexLocation hexLoc, EdgeDirection dir)
	{
		setHexLoc(hexLoc);
		setDir(dir);
	}
	
	public HexLocation getHexLoc()
	{
		return hexLoc;
	}
	
	private void setHexLoc(HexLocation hexLoc)
	{
		if(hexLoc == null)
		{
			throw new IllegalArgumentException("hexLoc cannot be null");
		}
		this.hexLoc = hexLoc;
	}
	
	public EdgeDirection getDir()
	{
		return dir;
	}
	
	private void setDir(EdgeDirection dir)
	{
		this.dir = dir;
	}
	
	public EdgeLocation getAmbiguousEdge() {
		HexLocation newLoc;
		switch(dir) {
		case NorthWest: newLoc = new HexLocation(hexLoc.getX()-1, hexLoc.getY());
			break;
		case North: newLoc = new HexLocation(hexLoc.getX(), hexLoc.getY()-1);
			break;
		case NorthEast: newLoc = new HexLocation(hexLoc.getX()+1, hexLoc.getY()-1);
			break;
		case SouthEast: newLoc = new HexLocation(hexLoc.getX()+1, hexLoc.getY());
			break;
		case South: newLoc = new HexLocation(hexLoc.getX(), hexLoc.getY()+1);
			break;
		case SouthWest: newLoc = new HexLocation(hexLoc.getX()-1, hexLoc.getY()+1);
			break;
		default: newLoc = null;
		}
		
		EdgeDirection newDir = dir.getOppositeDirection();
		EdgeLocation ambiguity = new EdgeLocation(newLoc, newDir);
		return ambiguity;
	}
	
	public ArrayList<EdgeLocation> getAdjacentEdges() {
		HexLocation newLoc1;
		HexLocation newLoc2;
		HexLocation newLoc3;
		HexLocation newLoc4;
		EdgeDirection newDir1;
		EdgeDirection newDir2;
		EdgeDirection newDir3;
		EdgeDirection newDir4;
		
		switch(dir) {
		case NorthWest: newLoc1 = new HexLocation(hexLoc.getX()-1, hexLoc.getY());
						newDir1 = EdgeDirection.South;
						newLoc2 = new HexLocation(hexLoc.getX(), hexLoc.getY());
						newDir2 = EdgeDirection.SouthWest;
						newLoc3 = new HexLocation(hexLoc.getX(), hexLoc.getY());
						newDir3 = EdgeDirection.North;
						newLoc4 = new HexLocation(hexLoc.getX()-1, hexLoc.getY());
						newDir4 = EdgeDirection.NorthEast;
						break;
						
		case North: 	newLoc1 = new HexLocation(hexLoc.getX(), hexLoc.getY()-1);
						newDir1 = EdgeDirection.SouthWest;
						newLoc2 = new HexLocation(hexLoc.getX(), hexLoc.getY());
						newDir2 = EdgeDirection.NorthWest;
						newLoc3 = new HexLocation(hexLoc.getX(), hexLoc.getY());
						newDir3 = EdgeDirection.NorthEast;
						newLoc4 = new HexLocation(hexLoc.getX(), hexLoc.getY()-1);
						newDir4 = EdgeDirection.SouthEast;
						break;
						
		case NorthEast:	newLoc1 = new HexLocation(hexLoc.getX()+1, hexLoc.getY()-1);
						newDir1 = EdgeDirection.NorthWest;
						newLoc2 = new HexLocation(hexLoc.getX(), hexLoc.getY());
						newDir2 = EdgeDirection.North;
						newLoc3 = new HexLocation(hexLoc.getX(), hexLoc.getY());
						newDir3 = EdgeDirection.SouthEast;
						newLoc4 = new HexLocation(hexLoc.getX()+1, hexLoc.getY()-1);
						newDir4 = EdgeDirection.South;
						break;
						
		case SouthEast: newLoc1 = new HexLocation(hexLoc.getX()+1, hexLoc.getY());
						newDir1 = EdgeDirection.North;
						newLoc2 = new HexLocation(hexLoc.getX(), hexLoc.getY());
						newDir2 = EdgeDirection.NorthEast;
						newLoc3 = new HexLocation(hexLoc.getX(), hexLoc.getY());
						newDir3 = EdgeDirection.South;
						newLoc4 = new HexLocation(hexLoc.getX()+1, hexLoc.getY());
						newDir4 = EdgeDirection.SouthWest;
						break;
						
		case South:		newLoc1 = new HexLocation(hexLoc.getX(), hexLoc.getY()+1);
						newDir1 = EdgeDirection.NorthEast;
						newLoc2 = new HexLocation(hexLoc.getX(), hexLoc.getY());
						newDir2 = EdgeDirection.SouthEast;
						newLoc3 = new HexLocation(hexLoc.getX(), hexLoc.getY());
						newDir3 = EdgeDirection.SouthWest;
						newLoc4 = new HexLocation(hexLoc.getX(), hexLoc.getY()+1);
						newDir4 = EdgeDirection.NorthWest;
						break;
						
		case SouthWest:	newLoc1 = new HexLocation(hexLoc.getX()-1, hexLoc.getY()+1);
						newDir1 = EdgeDirection.SouthEast;
						newLoc2 = new HexLocation(hexLoc.getX(), hexLoc.getY());
						newDir2 = EdgeDirection.South;
						newLoc3 = new HexLocation(hexLoc.getX(), hexLoc.getY());
						newDir3 = EdgeDirection.NorthWest;
						newLoc4 = new HexLocation(hexLoc.getX()-1, hexLoc.getY()+1);
						newDir4 = EdgeDirection.North;
						break;
						
		default:		newLoc1 = null;
						newDir1 = null;
						newLoc2 = null;
						newDir2 = null;
						newLoc3 = null;
						newDir3 = null;
						newLoc4 = null;
						newDir4 = null;
		}
		
		EdgeLocation adjacent1 = new EdgeLocation(newLoc1, newDir1);
		EdgeLocation adjacent2 = new EdgeLocation(newLoc2, newDir2);
		EdgeLocation adjacent3 = new EdgeLocation(newLoc3, newDir3);
		EdgeLocation adjacent4 = new EdgeLocation(newLoc4, newDir4);
		
		ArrayList<EdgeLocation> adjacentEdges = new ArrayList<EdgeLocation>();
		adjacentEdges.add(adjacent1);
		adjacentEdges.add(adjacent2);
		adjacentEdges.add(adjacent3);
		adjacentEdges.add(adjacent4);
		
		return adjacentEdges;
	}
	
	@Override
	public String toString()
	{
		return "EdgeLocation [hexLoc=" + hexLoc + ", dir=" + dir + "]";
	}
	
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dir == null) ? 0 : dir.hashCode());
		result = prime * result + ((hexLoc == null) ? 0 : hexLoc.hashCode());
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
		EdgeLocation other = (EdgeLocation)obj;
		if(dir != other.dir)
			return false;
		if(hexLoc == null)
		{
			if(other.hexLoc != null)
				return false;
		}
		else if(!hexLoc.equals(other.hexLoc))
			return false;
		return true;
	}
	
	/**
	 * Returns a canonical (i.e., unique) value for this edge location. Since
	 * each edge has two different locations on a map, this method converts a
	 * hex location to a single canonical form. This is useful for using hex
	 * locations as map keys.
	 * 
	 * @return Normalized hex location
	 */
	public EdgeLocation getNormalizedLocation()
	{
		
		// Return an EdgeLocation that has direction NW, N, or NE
		
		switch (dir)
		{
			case NorthWest:
			case North:
			case NorthEast:
				return this;
			case SouthWest:
			case South:
			case SouthEast:
				return new EdgeLocation(hexLoc.getNeighborLoc(dir),
										dir.getOppositeDirection());
			default:
				assert false;
				return null;
		}
	}
}

