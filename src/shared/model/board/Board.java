package shared.model.board;

import java.util.ArrayList;
import java.util.List;

public class Board {
	
	private List<Hex> landHexes;
	private List<WaterHex> waterHexes;
	private List<PortHex> ports;
	private BoardFacade boardFacade;
	
	private List<Road> roads;
	private List<Vertex> buildings;
	
	public Board(boolean random) {
		landHexes = new ArrayList<Hex>();
		waterHexes = new ArrayList<WaterHex>();
		ports = new ArrayList<PortHex>();
		generateBoard(random);
		
		roads = new ArrayList<Road>();
		buildings = new ArrayList<Vertex>();
	}
	
	/**
	 * Generates a random or standard board
	 * @param random whether the board should be random or standard
	 */
	private void generateBoard(boolean random) {
		
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
				+ ((landHexes == null) ? 0 : landHexes.hashCode());
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
		if (landHexes == null) {
			if (other.landHexes != null)
				return false;
		} else if (!landHexes.equals(other.landHexes))
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
		return "Board [landHexes=" + landHexes + ", waterHexes=" + waterHexes
				+ ", ports=" + ports + "]";
	}
}
