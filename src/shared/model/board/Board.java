package shared.model.board;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import shared.definitions.HexType;
import shared.definitions.PortType;
import shared.locations.EdgeDirection;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexDirection;
import shared.locations.VertexLocation;

public class Board {
	
	private List<ResourceHex> resourceHexes;
	private List<WaterHex> waterHexes;
	private List<PortHex> ports;
	private Hex desertHex;
	private BoardFacade boardFacade;
	
	private List<Road> roads;
	private List<Vertex> buildings;
	
	public Board(boolean randHexes, boolean randPorts, boolean randNums) {
		resourceHexes = new ArrayList<ResourceHex>();
		waterHexes = new ArrayList<WaterHex>();
		ports = new ArrayList<PortHex>();
		desertHex = null;
		generateBoard(randHexes, randPorts, randNums);
		
		roads = new ArrayList<Road>();
		buildings = new ArrayList<Vertex>();
	}
	
	/**
	 * Generates a random or standard board
	 * @param random whether the board should be random or standard
	 */
	private void generateBoard(boolean randHexes, boolean randPorts, boolean randNums) {
		ArrayList<HexType> landTypes = new ArrayList<HexType>();
		landTypes.add(HexType.ORE);
		landTypes.add(HexType.WHEAT);
		landTypes.add(HexType.WOOD);
		landTypes.add(HexType.BRICK);
		landTypes.add(HexType.SHEEP);
		landTypes.add(HexType.SHEEP);
		landTypes.add(HexType.ORE);
		landTypes.add(HexType.DESERT);
		landTypes.add(HexType.WOOD);
		landTypes.add(HexType.WHEAT);
		landTypes.add(HexType.WOOD);
		landTypes.add(HexType.WHEAT);
		landTypes.add(HexType.BRICK);
		landTypes.add(HexType.ORE);
		landTypes.add(HexType.BRICK);
		landTypes.add(HexType.SHEEP);
		landTypes.add(HexType.WOOD);
		landTypes.add(HexType.SHEEP);
		landTypes.add(HexType.WHEAT);
		
		ArrayList<PortType> portTypes = new ArrayList<PortType>();
		portTypes.add(PortType.THREE);
		portTypes.add(PortType.WOOD);
		portTypes.add(PortType.BRICK);
		portTypes.add(PortType.WHEAT);
		portTypes.add(PortType.THREE);
		portTypes.add(PortType.ORE);
		portTypes.add(PortType.THREE);
		portTypes.add(PortType.THREE);
		portTypes.add(PortType.SHEEP);
		
		ArrayList<Integer> numTokens = new ArrayList<Integer>();
		numTokens.add(5);
		numTokens.add(2);
		numTokens.add(6);
		numTokens.add(8);
		numTokens.add(10);
		numTokens.add(9);
		numTokens.add(3);
		numTokens.add(3);
		numTokens.add(11);
		numTokens.add(4);
		numTokens.add(8);
		numTokens.add(4);
		numTokens.add(9);
		numTokens.add(5);
		numTokens.add(10);
		numTokens.add(11);
		numTokens.add(12);
		numTokens.add(6);
		
		if(randHexes) {
			Collections.shuffle(landTypes);
		}
		if(randPorts) {
			Collections.shuffle(portTypes);			
		}
		if(randNums) {
			// need to implement this so that 6s and 8s are not next to each other
		}
		
		Iterator<HexType> landIt = landTypes.iterator();
		Iterator<PortType> portIt = portTypes.iterator();
		Iterator<Integer> numIt = numTokens.iterator();
		
		int x = -3;
		int y = 0;
		HexLocation hexLoc = new HexLocation(x, y++);
		VertexLocation vertex1 = new VertexLocation(hexLoc, VertexDirection.SouthEast);
		VertexLocation vertex2 = new VertexLocation(hexLoc, VertexDirection.East);
		EdgeLocation orientation = new EdgeLocation(hexLoc, EdgeDirection.SouthEast);
		ports.add(createPortHex(hexLoc, portIt.next(), vertex1, vertex2, orientation));
		waterHexes.add(new WaterHex(new HexLocation(x, y++)));
		hexLoc = new HexLocation(x, y++);
		vertex1 = new VertexLocation(hexLoc, VertexDirection.NorthEast);
		vertex2 = new VertexLocation(hexLoc, VertexDirection.East);
		orientation = new EdgeLocation(hexLoc, EdgeDirection.NorthEast);
		ports.add(createPortHex(hexLoc, portIt.next(), vertex1, vertex2, orientation));
		waterHexes.add(new WaterHex(new HexLocation(x, y)));
		
		x = -2;
		y = -1;
		waterHexes.add(new WaterHex(new HexLocation(x, y)));
		for (++y; y <= 2; y++) {
			HexType temp = landIt.next();
			if(temp != HexType.DESERT) {
				resourceHexes.add(new ResourceHex(temp, new HexLocation(x,y), numIt.next()));
			}
			else {
				desertHex = new Hex(temp, new HexLocation(x,y));
			}
		}
		hexLoc = new HexLocation(x, y++);
		vertex1 = new VertexLocation(hexLoc, VertexDirection.NorthEast);
		vertex2 = new VertexLocation(hexLoc, VertexDirection.East);
		orientation = new EdgeLocation(hexLoc, EdgeDirection.NorthEast);
		ports.add(createPortHex(hexLoc, portIt.next(), vertex1, vertex2, orientation));
		
		x = -1;
		y = -2;
		hexLoc = new HexLocation(x, y++);
		vertex1 = new VertexLocation(hexLoc, VertexDirection.SouthWest);
		vertex2 = new VertexLocation(hexLoc, VertexDirection.SouthEast);
		orientation = new EdgeLocation(hexLoc, EdgeDirection.South);
		ports.add(createPortHex(hexLoc, portIt.next(), vertex1, vertex2, orientation));
		for (++y; y <= 2; y++) {
			HexType temp = landIt.next();
			if(temp != HexType.DESERT) {
				resourceHexes.add(new ResourceHex(temp, new HexLocation(x,y), numIt.next()));
			}
			else {
				desertHex = new Hex(temp, new HexLocation(x,y));
			}
		}
		waterHexes.add(new WaterHex(new HexLocation(x, y)));
		
		x = 0;
		y = -3;
		waterHexes.add(new WaterHex(new HexLocation(x, y)));
		for (++y; y <= 2; y++) {
			HexType temp = landIt.next();
			if(temp != HexType.DESERT) {
				resourceHexes.add(new ResourceHex(temp, new HexLocation(x,y), numIt.next()));
			}
			else {
				desertHex = new Hex(temp, new HexLocation(x,y));
			}
		}
		hexLoc = new HexLocation(x, y++);
		vertex1 = new VertexLocation(hexLoc, VertexDirection.NorthEast);
		vertex2 = new VertexLocation(hexLoc, VertexDirection.NorthWest);
		orientation = new EdgeLocation(hexLoc, EdgeDirection.North);
		ports.add(createPortHex(hexLoc, portIt.next(), vertex1, vertex2, orientation));
		
		x = 1;
		y = -3;
		hexLoc = new HexLocation(x, y++);
		vertex1 = new VertexLocation(hexLoc, VertexDirection.SouthWest);
		vertex2 = new VertexLocation(hexLoc, VertexDirection.SouthEast);
		orientation = new EdgeLocation(hexLoc, EdgeDirection.South);
		ports.add(createPortHex(hexLoc, portIt.next(), vertex1, vertex2, orientation));
		for (++y; y <= 1; y++) {
			HexType temp = landIt.next();
			if(temp != HexType.DESERT) {
				resourceHexes.add(new ResourceHex(temp, new HexLocation(x,y), numIt.next()));
			}
			else {
				desertHex = new Hex(temp, new HexLocation(x,y));
			}
		}
		waterHexes.add(new WaterHex(new HexLocation(x, y)));
		
		x = 2;
		y = -3;
		waterHexes.add(new WaterHex(new HexLocation(x, y)));
		for (++y; y <= 0; y++) {
			HexType temp = landIt.next();
			if(temp != HexType.DESERT) {
				resourceHexes.add(new ResourceHex(temp, new HexLocation(x,y), numIt.next()));
			}
			else {
				desertHex = new Hex(temp, new HexLocation(x,y));
			}
		}
		hexLoc = new HexLocation(x, y++);
		vertex1 = new VertexLocation(hexLoc, VertexDirection.NorthWest);
		vertex2 = new VertexLocation(hexLoc, VertexDirection.West);
		orientation = new EdgeLocation(hexLoc, EdgeDirection.NorthWest);
		ports.add(createPortHex(hexLoc, portIt.next(), vertex1, vertex2, orientation));
		
		x = 3;
		y = -3;
		hexLoc = new HexLocation(x, y++);
		vertex1 = new VertexLocation(hexLoc, VertexDirection.West);
		vertex2 = new VertexLocation(hexLoc, VertexDirection.SouthWest);
		orientation = new EdgeLocation(hexLoc, EdgeDirection.SouthWest);
		ports.add(createPortHex(hexLoc, portIt.next(), vertex1, vertex2, orientation));
		waterHexes.add(new WaterHex(new HexLocation(x, y++)));
		hexLoc = new HexLocation(x, y++);
		vertex1 = new VertexLocation(hexLoc, VertexDirection.NorthWest);
		vertex2 = new VertexLocation(hexLoc, VertexDirection.West);
		orientation = new EdgeLocation(hexLoc, EdgeDirection.NorthWest);
		ports.add(createPortHex(hexLoc, portIt.next(), vertex1, vertex2, orientation));
		waterHexes.add(new WaterHex(new HexLocation(x, y)));
		
		assert !landIt.hasNext();
		assert !portIt.hasNext();
		assert !numIt.hasNext();
		assert resourceHexes.size() == 18;
		assert ports.size() == 9;
		assert waterHexes.size() == 9;
		assert desertHex != null;
	}

	private PortHex createPortHex(HexLocation location, PortType type,
			VertexLocation vertex1, VertexLocation vertex2, EdgeLocation orientation) {
		if(type == PortType.THREE) {
			return new PortHex(location, type, 3, vertex1, vertex2, orientation);
		}
		else {
			return new PortHex(location, type, 2, vertex1, vertex2, orientation);
		}
	}

	public List<Road> getRoads() {
		return roads;
	}

	public void setRoads(List<Road> roads) {
		this.roads = roads;
	}

	public List<Vertex> getBuildings() {
		return buildings;
	}

	public void setBuildings(List<Vertex> buildings) {
		this.buildings = buildings;
	}
	
	public Hex getDesertHex() {
		return desertHex;
	}

	public BoardFacade getBoardFacade() {
		return boardFacade;
	}

	public void setBoardFacade(BoardFacade boardFacade) {
		this.boardFacade = boardFacade;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((resourceHexes == null) ? 0 : resourceHexes.hashCode());
		result = prime * result + ((ports == null) ? 0 : ports.hashCode());
		result = prime * result
				+ ((waterHexes == null) ? 0 : waterHexes.hashCode());
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
		Board other = (Board) obj;
		if (resourceHexes == null) {
			if (other.resourceHexes != null)
				return false;
		} else if (!resourceHexes.equals(other.resourceHexes))
			return false;
		if (ports == null) {
			if (other.ports != null)
				return false;
		} else if (!ports.equals(other.ports))
			return false;
		if (waterHexes == null) {
			if (other.waterHexes != null)
				return false;
		} else if (!waterHexes.equals(other.waterHexes))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Board [resourceHexes=" + resourceHexes + ", waterHexes=" + waterHexes
				+ ", ports=" + ports + "]";
	}
}
