package shared.model.board;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import shared.definitions.HexType;
import shared.definitions.PortType;
import shared.locations.EdgeDirection;
import shared.locations.HexLocation;
import shared.locations.VertexDirection;
import shared.locations.VertexLocation;

public class Board {
	
	private List<ResourceHex> resourceHexes;
	private transient Map<HexLocation,ResourceHex> resourceHexesMap = new HashMap<HexLocation, ResourceHex>();
	private List<WaterHex> waterHexes;
	private List<PortHex> ports;
	private Hex desertHex;
	private transient BoardFacade boardFacade;
	private Robber robber;
	
	private List<Road> roads;
	private List<Vertex> buildings;
	
	public Board() {
		resourceHexes = new ArrayList<ResourceHex>();
		ports = new ArrayList<PortHex>();
		waterHexes = new ArrayList<WaterHex>();
		waterHexes.add(new WaterHex(new HexLocation(-3, 0)));
		waterHexes.add(new WaterHex(new HexLocation(-3, 1)));
		waterHexes.add(new WaterHex(new HexLocation(-3, 2)));
		waterHexes.add(new WaterHex(new HexLocation(-3, 3)));
		waterHexes.add(new WaterHex(new HexLocation(-2, -1)));
		waterHexes.add(new WaterHex(new HexLocation(-2, 3)));
		waterHexes.add(new WaterHex(new HexLocation(-1, -2)));
		waterHexes.add(new WaterHex(new HexLocation(-1, 3)));
		waterHexes.add(new WaterHex(new HexLocation(0, -3)));
		waterHexes.add(new WaterHex(new HexLocation(0, 3)));
		waterHexes.add(new WaterHex(new HexLocation(1, -3)));
		waterHexes.add(new WaterHex(new HexLocation(1, 2)));
		waterHexes.add(new WaterHex(new HexLocation(2, -3)));
		waterHexes.add(new WaterHex(new HexLocation(2, 1)));
		waterHexes.add(new WaterHex(new HexLocation(3, -3)));
		waterHexes.add(new WaterHex(new HexLocation(3, -2)));
		waterHexes.add(new WaterHex(new HexLocation(3, -1)));
		waterHexes.add(new WaterHex(new HexLocation(3, 0)));
		
		boardFacade = new BoardFacade(this);
	}
	
	public Board(boolean randHexes, boolean randPorts, boolean randNums) {
		resourceHexes = new ArrayList<ResourceHex>();
		ports = new ArrayList<PortHex>();
		waterHexes = new ArrayList<WaterHex>();
		desertHex = null;
		generateBoard(randHexes, randPorts, randNums);
		
		roads = new ArrayList<Road>();
		buildings = new ArrayList<Vertex>();
		boardFacade = new BoardFacade(this);
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
			Collections.shuffle(numTokens);
		}
		
		Iterator<HexType> landIt = landTypes.iterator();
		Iterator<PortType> portIt = portTypes.iterator();
		Iterator<Integer> numIt = numTokens.iterator();
		
		int x = -3;
		int y = 0;
		HexLocation hexLoc = new HexLocation(x, y++);
		VertexLocation vertex1 = new VertexLocation(hexLoc, VertexDirection.SouthEast);
		VertexLocation vertex2 = new VertexLocation(hexLoc, VertexDirection.East);
		ports.add(createPortHex(hexLoc, portIt.next(), EdgeDirection.SouthEast));
		waterHexes.add(new WaterHex(hexLoc));
		waterHexes.add(new WaterHex(new HexLocation(x, y++)));
		hexLoc = new HexLocation(x, y++);
		vertex1 = new VertexLocation(hexLoc, VertexDirection.NorthEast);
		vertex2 = new VertexLocation(hexLoc, VertexDirection.East);
		ports.add(createPortHex(hexLoc, portIt.next(), EdgeDirection.NorthEast));
		waterHexes.add(new WaterHex(hexLoc));
		waterHexes.add(new WaterHex(new HexLocation(x, y)));
		
		x = -2;
		y = -1;
		waterHexes.add(new WaterHex(new HexLocation(x, y)));
		for (++y; y <= 2; y++) {
			HexType temp = landIt.next();
			HexLocation newLoc = new HexLocation(x,y);
			if(temp != HexType.DESERT) {
				int num = numIt.next();
				ResourceHex newHex = new ResourceHex(temp, newLoc, num);
				resourceHexes.add(newHex);
				resourceHexesMap.put(newLoc, newHex);
			}
			else {
				desertHex = new Hex(temp, newLoc);
			}
		}
		hexLoc = new HexLocation(x, y++);
		vertex1 = new VertexLocation(hexLoc, VertexDirection.NorthEast);
		vertex2 = new VertexLocation(hexLoc, VertexDirection.East);
		ports.add(createPortHex(hexLoc, portIt.next(), EdgeDirection.NorthEast));
		waterHexes.add(new WaterHex(hexLoc));
		
		x = -1;
		y = -2;
		hexLoc = new HexLocation(x, y);
		vertex1 = new VertexLocation(hexLoc, VertexDirection.SouthWest);
		vertex2 = new VertexLocation(hexLoc, VertexDirection.SouthEast);
		ports.add(createPortHex(hexLoc, portIt.next(), EdgeDirection.South));
		waterHexes.add(new WaterHex(hexLoc));
		for (++y; y <= 2; y++) {
			HexType temp = landIt.next();
			HexLocation newLoc = new HexLocation(x,y);
			if(temp != HexType.DESERT) {
				int num = numIt.next();
				ResourceHex newHex = new ResourceHex(temp, newLoc, num);
				resourceHexes.add(newHex);
				resourceHexesMap.put(newLoc, newHex);
			}
			else {
				desertHex = new Hex(temp, newLoc);
			}
		}
		waterHexes.add(new WaterHex(new HexLocation(x, y)));
		
		x = 0;
		y = -3;
		waterHexes.add(new WaterHex(new HexLocation(x, y)));
		for (++y; y <= 2; y++) {
			HexType temp = landIt.next();
			HexLocation newLoc = new HexLocation(x,y);
			if(temp != HexType.DESERT) {
				int num = numIt.next();
				ResourceHex newHex = new ResourceHex(temp, newLoc, num);
				resourceHexes.add(newHex);
				resourceHexesMap.put(newLoc, newHex);
			}
			else {
				desertHex = new Hex(temp, newLoc);
			}
		}
		hexLoc = new HexLocation(x, y++);
		vertex1 = new VertexLocation(hexLoc, VertexDirection.NorthEast);
		vertex2 = new VertexLocation(hexLoc, VertexDirection.NorthWest);
		ports.add(createPortHex(hexLoc, portIt.next(), EdgeDirection.North));
		waterHexes.add(new WaterHex(hexLoc));
		
		x = 1;
		y = -3;
		hexLoc = new HexLocation(x, y);
		vertex1 = new VertexLocation(hexLoc, VertexDirection.SouthWest);
		vertex2 = new VertexLocation(hexLoc, VertexDirection.SouthEast);
		ports.add(createPortHex(hexLoc, portIt.next(), EdgeDirection.South));
		waterHexes.add(new WaterHex(hexLoc));
		for (++y; y <= 1; y++) {
			HexType temp = landIt.next();
			HexLocation newLoc = new HexLocation(x,y);
			if(temp != HexType.DESERT) {
				int num = numIt.next();
				ResourceHex newHex = new ResourceHex(temp, newLoc, num);
				resourceHexes.add(newHex);
				resourceHexesMap.put(newLoc, newHex);
			}
			else {
				desertHex = new Hex(temp, newLoc);
			}
		}
		waterHexes.add(new WaterHex(new HexLocation(x, y)));
		
		x = 2;
		y = -3;
		waterHexes.add(new WaterHex(new HexLocation(x, y)));
		for (++y; y <= 0; y++) {
			HexType temp = landIt.next();
			HexLocation newLoc = new HexLocation(x,y);
			if(temp != HexType.DESERT) {
				int num = numIt.next();
				ResourceHex newHex = new ResourceHex(temp, newLoc, num);
				resourceHexes.add(newHex);
				resourceHexesMap.put(newLoc, newHex);
			}
			else {
				desertHex = new Hex(temp, newLoc);
			}
		}
		hexLoc = new HexLocation(x, y++);
		vertex1 = new VertexLocation(hexLoc, VertexDirection.NorthWest);
		vertex2 = new VertexLocation(hexLoc, VertexDirection.West);
		ports.add(createPortHex(hexLoc, portIt.next(), EdgeDirection.NorthWest));
		waterHexes.add(new WaterHex(hexLoc));
		
		x = 3;
		y = -3;
		hexLoc = new HexLocation(x, y++);
		vertex1 = new VertexLocation(hexLoc, VertexDirection.West);
		vertex2 = new VertexLocation(hexLoc, VertexDirection.SouthWest);
		ports.add(createPortHex(hexLoc, portIt.next(), EdgeDirection.SouthWest));
		waterHexes.add(new WaterHex(hexLoc));
		waterHexes.add(new WaterHex(new HexLocation(x, y++)));
		hexLoc = new HexLocation(x, y++);
		vertex1 = new VertexLocation(hexLoc, VertexDirection.NorthWest);
		vertex2 = new VertexLocation(hexLoc, VertexDirection.West);
		ports.add(createPortHex(hexLoc, portIt.next(), EdgeDirection.NorthWest));
		waterHexes.add(new WaterHex(hexLoc));
		waterHexes.add(new WaterHex(new HexLocation(x, y)));
		
		robber = new Robber(desertHex.getLocation());
		
		assert !landIt.hasNext();
		assert !portIt.hasNext();
		assert !numIt.hasNext();
		assert resourceHexes.size() == 18;
		assert ports.size() == 9;
		assert waterHexes.size() == 18;
		assert desertHex != null;
		
		
		// will loop until no changes in numbering are made
		while(numberArranger()) {}
	}
	
	// moves any 8s and 6s that are adjacent. returns true if change made
	private boolean numberArranger() {
		List<ResourceHex> sixAndEights = new ArrayList<ResourceHex>();
		for(ResourceHex hex : resourceHexes) {
			if(hex.getNumberToken() == 6 || hex.getNumberToken() == 8) {
				sixAndEights.add(hex);
			}
		}
		
		assert sixAndEights.size() == 4; 
		
		for(ResourceHex hex1 : sixAndEights) {
			HexLocation hex1Loc = hex1.getLocation();
			for(ResourceHex hex2 : sixAndEights) {
				HexLocation hex2Loc = hex2.getLocation();
				if(hex1Loc.isAdjacent(hex2Loc)) {
					List<HexLocation> newLocs = hex2Loc.getLocsNotAdjacentTo(hex1Loc);
					for(HexLocation newLoc : newLocs) {
						if(!newLoc.equals(desertHex.getLocation())) {
							boolean adjacentTo68 = false;
							for(ResourceHex hex : sixAndEights){
								if(newLoc.isAdjacent(hex.getLocation())) adjacentTo68 = true;
							}
							int temp = resourceHexesMap.get(newLoc).getNumberToken();
							if (!(temp == 6 || temp == 8) && !adjacentTo68) {
								resourceHexesMap.get(newLoc).setNumberToken(hex2.getNumberToken());
								hex2.setNumberToken(temp);
								return true;								
							}
						}
					}
				}
			}
		}
		return false;
	}

	private PortHex createPortHex(HexLocation location, PortType type,
			 EdgeDirection orientation) {
		if(type == PortType.THREE) {
			return new PortHex(location, type, 3, orientation);
		}
		else {
			return new PortHex(location, type, 2, orientation);
		}
	}

	public List<ResourceHex> getResourceHexes() {
		return resourceHexes;
	}

	public void setResourceHexes(List<ResourceHex> resourceHexes) {
		this.resourceHexes = resourceHexes;
	}

	public Map<HexLocation, ResourceHex> getResourceHexesMap() {
		return resourceHexesMap;
	}

	public List<WaterHex> getWaterHexes() {
		return waterHexes;
	}

	public void setWaterHexes(List<WaterHex> waterHexes) {
		this.waterHexes = waterHexes;
	}

	public List<PortHex> getPorts() {
		return ports;
	}

	public void setPorts(List<PortHex> ports) {
		this.ports = ports;
	}

	public void setDesertHex(Hex desertHex) {
		this.desertHex = desertHex;
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

	public Robber getRobber() {
		return robber;
	}

	public void setRobber(Robber robber) {
		this.robber = robber;
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
