package shared.locations;

import java.io.Serializable;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnore;;

/**
 * Represents the location of a vertex on a hex map
 */
@SuppressWarnings("serial")
public class VertexLocation implements Serializable
{
	
	private HexLocation hexLoc;
	private VertexDirection dir;
	
	public VertexLocation() {
		hexLoc = null;
		dir = null;
	}
	
	public VertexLocation(HexLocation hexLoc, VertexDirection dir)
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
	
	public VertexDirection getDir()
	{
		return dir;
	}
	
	private void setDir(VertexDirection direction)
	{
		this.dir = direction;
	}
	
	/**
	 * 
	 * @return List of VertexLocations that indicate the same location on the board as this VertexLocation
	 */
	@JsonIgnore public ArrayList<VertexLocation> getAmbiguousVertices() {
		HexLocation newLoc1;
		HexLocation newLoc2;
		VertexDirection newDir1;
		VertexDirection newDir2;
		
		switch(dir) {
		case NorthWest: newLoc1 = new HexLocation(hexLoc.getX()-1, hexLoc.getY());
						newDir1 = VertexDirection.East;
						newLoc2 = new HexLocation(hexLoc.getX(), hexLoc.getY()-1);
						newDir2 = VertexDirection.SouthWest;
						break;
						
		case NorthEast: newLoc1 = new HexLocation(hexLoc.getX(), hexLoc.getY()-1);
						newDir1 = VertexDirection.SouthEast;
						newLoc2 = new HexLocation(hexLoc.getX()+1, hexLoc.getY()-1);
						newDir2 = VertexDirection.West;
						break;
						
		case East: 		newLoc1 = new HexLocation(hexLoc.getX()+1, hexLoc.getY()-1);
						newDir1 = VertexDirection.SouthWest;
						newLoc2 = new HexLocation(hexLoc.getX()+1, hexLoc.getY());
						newDir2 = VertexDirection.NorthWest;
						break;
						
		case SouthEast: newLoc1 = new HexLocation(hexLoc.getX()+1, hexLoc.getY());
						newDir1 = VertexDirection.West;
						newLoc2 = new HexLocation(hexLoc.getX(), hexLoc.getY()+1);
						newDir2 = VertexDirection.NorthEast;
						break;
						
		case SouthWest: newLoc1 = new HexLocation(hexLoc.getX(), hexLoc.getY()+1);
						newDir1 = VertexDirection.NorthWest;
						newLoc2 = new HexLocation(hexLoc.getX()-1, hexLoc.getY()+1);
						newDir2 = VertexDirection.East;
						break;
						
		case West:		newLoc1 = new HexLocation(hexLoc.getX()-1, hexLoc.getY()+1);
						newDir1 = VertexDirection.NorthEast;
						newLoc2 = new HexLocation(hexLoc.getX()-1, hexLoc.getY());
						newDir2 = VertexDirection.SouthEast;
						break;
						
		default:		newLoc1 = null;
						newDir1 = null;
						newLoc2 = null;
						newDir2 = null;		
		}
		
		VertexLocation ambiguity1 = new VertexLocation(newLoc1, newDir1);
		VertexLocation ambiguity2 = new VertexLocation(newLoc2, newDir2);
		
		ArrayList<VertexLocation> ambiguities = new ArrayList<VertexLocation>();
		ambiguities.add(ambiguity1);
		ambiguities.add(ambiguity2);
		
		return ambiguities;
	}
	
	@JsonIgnore public ArrayList<VertexLocation> getAdjacentVertices() {
		HexLocation newLoc1;
		HexLocation newLoc2;
		HexLocation newLoc3;
		VertexDirection newDir1;
		VertexDirection newDir2;
		VertexDirection newDir3;
		
		switch(dir) {
		case NorthWest: newLoc1 = new HexLocation(hexLoc.getX()-1, hexLoc.getY());
						newDir1 = VertexDirection.NorthEast;
						newLoc2 = new HexLocation(hexLoc.getX(), hexLoc.getY());
						newDir2 = VertexDirection.West;
						newLoc3 = new HexLocation(hexLoc.getX(), hexLoc.getY());
						newDir3 = VertexDirection.NorthEast;
						break;
						
		case NorthEast: newLoc1 = new HexLocation(hexLoc.getX(), hexLoc.getY()-1);
						newDir1 = VertexDirection.East;
						newLoc2 = new HexLocation(hexLoc.getX(), hexLoc.getY());
						newDir2 = VertexDirection.NorthWest;
						newLoc3 = new HexLocation(hexLoc.getX(), hexLoc.getY());
						newDir3 = VertexDirection.East;
						break;
						
		case East: 		newLoc1 = new HexLocation(hexLoc.getX()+1, hexLoc.getY()-1);
						newDir1 = VertexDirection.SouthEast;
						newLoc2 = new HexLocation(hexLoc.getX(), hexLoc.getY());
						newDir2 = VertexDirection.NorthEast;
						newLoc3 = new HexLocation(hexLoc.getX(), hexLoc.getY());
						newDir3 = VertexDirection.SouthEast;
						break;
						
		case SouthEast: newLoc1 = new HexLocation(hexLoc.getX()+1, hexLoc.getY());
						newDir1 = VertexDirection.SouthWest;
						newLoc2 = new HexLocation(hexLoc.getX(), hexLoc.getY());
						newDir2 = VertexDirection.East;
						newLoc3 = new HexLocation(hexLoc.getX(), hexLoc.getY());
						newDir3 = VertexDirection.SouthWest;
						break;
						
		case SouthWest: newLoc1 = new HexLocation(hexLoc.getX(), hexLoc.getY()+1);
						newDir1 = VertexDirection.West;
						newLoc2 = new HexLocation(hexLoc.getX(), hexLoc.getY());
						newDir2 = VertexDirection.SouthEast;
						newLoc3 = new HexLocation(hexLoc.getX(), hexLoc.getY());
						newDir3 = VertexDirection.West;
						break;
						
		case West:		newLoc1 = new HexLocation(hexLoc.getX()-1, hexLoc.getY()+1);
						newDir1 = VertexDirection.NorthWest;
						newLoc2 = new HexLocation(hexLoc.getX(), hexLoc.getY());
						newDir2 = VertexDirection.SouthWest;
						newLoc3 = new HexLocation(hexLoc.getX(), hexLoc.getY());
						newDir3 = VertexDirection.NorthWest;
						break;
						
		default:		newLoc1 = null;
						newDir1 = null;
						newLoc2 = null;
						newDir2 = null;
						newLoc3 = null;
						newDir3 = null;
		}
		
		VertexLocation adjacent1 = new VertexLocation(newLoc1, newDir1);
		VertexLocation adjacent2 = new VertexLocation(newLoc2, newDir2);
		VertexLocation adjacent3 = new VertexLocation(newLoc3, newDir3);
		
		ArrayList<VertexLocation> adjacentHexes = new ArrayList<VertexLocation>();
		adjacentHexes.add(adjacent1);
		adjacentHexes.add(adjacent2);
		adjacentHexes.add(adjacent3);
		
		return adjacentHexes;
	}
	
	@JsonIgnore public ArrayList<EdgeLocation> getAdjacentEdges() {
		HexLocation newLoc1;
		HexLocation newLoc2;
		HexLocation newLoc3;
		EdgeDirection newDir1;
		EdgeDirection newDir2;
		EdgeDirection newDir3;
		
		switch(dir) {
		case NorthWest: newLoc1 = new HexLocation(hexLoc.getX()-1, hexLoc.getY());
						newDir1 = EdgeDirection.NorthEast;
						newLoc2 = new HexLocation(hexLoc.getX(), hexLoc.getY());
						newDir2 = EdgeDirection.NorthWest;
						newLoc3 = new HexLocation(hexLoc.getX(), hexLoc.getY());
						newDir3 = EdgeDirection.North;
						break;
						
		case NorthEast: newLoc1 = new HexLocation(hexLoc.getX(), hexLoc.getY()-1);
						newDir1 = EdgeDirection.SouthEast;
						newLoc2 = new HexLocation(hexLoc.getX(), hexLoc.getY());
						newDir2 = EdgeDirection.North;
						newLoc3 = new HexLocation(hexLoc.getX(), hexLoc.getY());
						newDir3 = EdgeDirection.NorthEast;
						break;
						
		case East: 		newLoc1 = new HexLocation(hexLoc.getX()+1, hexLoc.getY()-1);
						newDir1 = EdgeDirection.South;
						newLoc2 = new HexLocation(hexLoc.getX(), hexLoc.getY());
						newDir2 = EdgeDirection.NorthEast;
						newLoc3 = new HexLocation(hexLoc.getX(), hexLoc.getY());
						newDir3 = EdgeDirection.SouthEast;
						break;
						
		case SouthEast: newLoc1 = new HexLocation(hexLoc.getX()+1, hexLoc.getY());
						newDir1 = EdgeDirection.SouthWest;
						newLoc2 = new HexLocation(hexLoc.getX(), hexLoc.getY());
						newDir2 = EdgeDirection.SouthEast;
						newLoc3 = new HexLocation(hexLoc.getX(), hexLoc.getY());
						newDir3 = EdgeDirection.South;
						break;
						
		case SouthWest: newLoc1 = new HexLocation(hexLoc.getX(), hexLoc.getY()+1);
						newDir1 = EdgeDirection.NorthWest;
						newLoc2 = new HexLocation(hexLoc.getX(), hexLoc.getY());
						newDir2 = EdgeDirection.South;
						newLoc3 = new HexLocation(hexLoc.getX(), hexLoc.getY());
						newDir3 = EdgeDirection.SouthWest;
						break;
						
		case West:		newLoc1 = new HexLocation(hexLoc.getX()-1, hexLoc.getY()+1);
						newDir1 = EdgeDirection.North;
						newLoc2 = new HexLocation(hexLoc.getX(), hexLoc.getY());
						newDir2 = EdgeDirection.SouthWest;
						newLoc3 = new HexLocation(hexLoc.getX(), hexLoc.getY());
						newDir3 = EdgeDirection.NorthWest;
						break;
						
		default:		newLoc1 = null;
						newDir1 = null;
						newLoc2 = null;
						newDir2 = null;
						newLoc3 = null;
						newDir3 = null;
		}
		
		EdgeLocation adjacent1 = new EdgeLocation(newLoc1, newDir1);
		EdgeLocation adjacent2 = new EdgeLocation(newLoc2, newDir2);
		EdgeLocation adjacent3 = new EdgeLocation(newLoc3, newDir3);
		
		ArrayList<EdgeLocation> adjacentEdges = new ArrayList<EdgeLocation>();
		adjacentEdges.add(adjacent1);
		adjacentEdges.add(adjacent2);
		adjacentEdges.add(adjacent3);
		
		return adjacentEdges;
	}
	
	@Override
	public String toString()
	{
		return "VertexLocation [hexLoc=" + hexLoc + ", dir=" + dir + "]";
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
		VertexLocation other = (VertexLocation)obj;
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
	 * Returns a canonical (i.e., unique) value for this vertex location. Since
	 * each vertex has three different locations on a map, this method converts
	 * a vertex location to a single canonical form. This is useful for using
	 * vertex locations as map keys.
	 * 
	 * @return Normalized vertex location
	 */
	@JsonIgnore public VertexLocation getNormalizedLocation()
	{
		
		// Return location that has direction NW or NE
		
		switch (dir)
		{
			case NorthWest:
			case NorthEast:
				return this;
			case West:
				return new VertexLocation(
										  hexLoc.getNeighborLoc(EdgeDirection.SouthWest),
										  VertexDirection.NorthEast);
			case SouthWest:
				return new VertexLocation(
										  hexLoc.getNeighborLoc(EdgeDirection.South),
										  VertexDirection.NorthWest);
			case SouthEast:
				return new VertexLocation(
										  hexLoc.getNeighborLoc(EdgeDirection.South),
										  VertexDirection.NorthEast);
			case East:
				return new VertexLocation(
										  hexLoc.getNeighborLoc(EdgeDirection.SouthEast),
										  VertexDirection.NorthWest);
			default:
				assert false;
				return null;
		}
	}
}

