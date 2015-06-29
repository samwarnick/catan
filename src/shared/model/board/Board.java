package shared.model.board;

import java.util.ArrayList;
import java.util.List;

public class Board {
	
	private List<Hex> landHexes;
	private List<WaterHex> waterHexes;
	private List<PortHex> ports;
	
	public Board() {
		landHexes = new ArrayList<Hex>();
		waterHexes = new ArrayList<WaterHex>();
		ports = new ArrayList<PortHex>();
	}
	
	// TODO: hashcode, equals, toString
}
