package shared.model.board;

import java.util.ArrayList;
import java.util.List;

public class Board {
	
	private List<Hex> landHexes;
	private List<WaterHex> waterHexes;
	private List<PortHex> ports;
	private BoardFacade boardFacade;
	
	public Board() {
		landHexes = new ArrayList<Hex>();
		waterHexes = new ArrayList<WaterHex>();
		ports = new ArrayList<PortHex>();
		generateBoard();
	}
	
	/**
	 * Randomly generate a random board
	 */
	private void generateBoard() {
		
	}
	
	// TODO: hashcode, equals, toString
}
