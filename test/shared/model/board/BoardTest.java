package shared.model.board;



import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import shared.definitions.PortType;
import shared.definitions.HexType;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexDirection;
import shared.locations.VertexLocation;
import shared.model.board.*;


public class BoardTest {
	
	private Board board;

	@Test
	public void testGenerateBoard() {
		board = new Board(false, false, false);

		Hex desertHex = board.getDesertHex();
		assertEquals(desertHex.getLocation().getX(), 0);
		assertEquals(desertHex.getLocation().getY(), -2);
		
		List<ResourceHex> resourceHexes = board.getResourceHexes();
		int i = 0;
		assertEquals(resourceHexes.get(i).getLocation().getX(), -2);
		assertEquals(resourceHexes.get(i).getLocation().getY(), 0);
		assertEquals(resourceHexes.get(i).getLandType(), HexType.ORE);
		i++;
		assertEquals(resourceHexes.get(i).getLocation().getX(), -2);
		assertEquals(resourceHexes.get(i).getLocation().getY(), 1);
		assertEquals(resourceHexes.get(i).getLandType(), HexType.WHEAT);
		i++;
		assertEquals(resourceHexes.get(i).getLocation().getX(), -2);
		assertEquals(resourceHexes.get(i).getLocation().getY(), 2);
		assertEquals(resourceHexes.get(i).getLandType(), HexType.WOOD);
		i++;
		assertEquals(resourceHexes.get(i).getLocation().getX(), -1);
		assertEquals(resourceHexes.get(i).getLocation().getY(), -1);
		assertEquals(resourceHexes.get(i).getLandType(), HexType.BRICK);
		i++;
		assertEquals(resourceHexes.get(i).getLocation().getX(), -1);
		assertEquals(resourceHexes.get(i).getLocation().getY(), 0);
		assertEquals(resourceHexes.get(i).getLandType(), HexType.SHEEP);
		i++;
		assertEquals(resourceHexes.get(i).getLocation().getX(), -1);
		assertEquals(resourceHexes.get(i).getLocation().getY(), 1);
		assertEquals(resourceHexes.get(i).getLandType(), HexType.SHEEP);
		i++;
		assertEquals(resourceHexes.get(i).getLocation().getX(), -1);
		assertEquals(resourceHexes.get(i).getLocation().getY(), 2);
		assertEquals(resourceHexes.get(i).getLandType(), HexType.ORE);
		i++;
		assertEquals(resourceHexes.get(i).getLocation().getX(), 0);
		assertEquals(resourceHexes.get(i).getLocation().getY(), -1);
		assertEquals(resourceHexes.get(i).getLandType(), HexType.WOOD);
		i++;
		assertEquals(resourceHexes.get(i).getLocation().getX(), 0);
		assertEquals(resourceHexes.get(i).getLocation().getY(), 0);
		assertEquals(resourceHexes.get(i).getLandType(), HexType.WHEAT);
		i++;
		assertEquals(resourceHexes.get(i).getLocation().getX(), 0);
		assertEquals(resourceHexes.get(i).getLocation().getY(), 1);
		assertEquals(resourceHexes.get(i).getLandType(), HexType.WOOD);
		i++;
		assertEquals(resourceHexes.get(i).getLocation().getX(), 0);
		assertEquals(resourceHexes.get(i).getLocation().getY(), 2);
		assertEquals(resourceHexes.get(i).getLandType(), HexType.WHEAT);
		i++;
		assertEquals(resourceHexes.get(i).getLocation().getX(), 1);
		assertEquals(resourceHexes.get(i).getLocation().getY(), -2);
		assertEquals(resourceHexes.get(i).getLandType(), HexType.BRICK);
		i++;
		assertEquals(resourceHexes.get(i).getLocation().getX(), 1);
		assertEquals(resourceHexes.get(i).getLocation().getY(), -1);
		assertEquals(resourceHexes.get(i).getLandType(), HexType.ORE);
		i++;
		assertEquals(resourceHexes.get(i).getLocation().getX(), 1);
		assertEquals(resourceHexes.get(i).getLocation().getY(), 0);
		assertEquals(resourceHexes.get(i).getLandType(), HexType.BRICK);
		i++;
		assertEquals(resourceHexes.get(i).getLocation().getX(), 1);
		assertEquals(resourceHexes.get(i).getLocation().getY(), 1);
		assertEquals(resourceHexes.get(i).getLandType(), HexType.SHEEP);
		i++;
		assertEquals(resourceHexes.get(i).getLocation().getX(), 2);
		assertEquals(resourceHexes.get(i).getLocation().getY(), -2);
		assertEquals(resourceHexes.get(i).getLandType(), HexType.WOOD);
		i++;
		assertEquals(resourceHexes.get(i).getLocation().getX(), 2);
		assertEquals(resourceHexes.get(i).getLocation().getY(), -1);
		assertEquals(resourceHexes.get(i).getLandType(), HexType.SHEEP);
		i++;
		assertEquals(resourceHexes.get(i).getLocation().getX(), 2);
		assertEquals(resourceHexes.get(i).getLocation().getY(), 0);
		assertEquals(resourceHexes.get(i).getLandType(), HexType.WHEAT);
		
		List<WaterHex> waterHex = board.getWaterHexes();
		i = 0;
		assertEquals(waterHex.get(i).getLocation().getX(), -3);
		assertEquals(waterHex.get(i).getLocation().getY(), 1);
		i++;
		assertEquals(waterHex.get(i).getLocation().getX(), -3);
		assertEquals(waterHex.get(i).getLocation().getY(), 3);
		i++;
		assertEquals(waterHex.get(i).getLocation().getX(), -2);
		assertEquals(waterHex.get(i).getLocation().getY(), -1);
		i++;
		assertEquals(waterHex.get(i).getLocation().getX(), -1);
		assertEquals(waterHex.get(i).getLocation().getY(), 3);
		i++;
		assertEquals(waterHex.get(i).getLocation().getX(), 0);
		assertEquals(waterHex.get(i).getLocation().getY(), -3);
		i++;
		assertEquals(waterHex.get(i).getLocation().getX(), 1);
		assertEquals(waterHex.get(i).getLocation().getY(), 2);
		i++;
		assertEquals(waterHex.get(i).getLocation().getX(), 2);
		assertEquals(waterHex.get(i).getLocation().getY(), -3);
		i++;
		assertEquals(waterHex.get(i).getLocation().getX(), 3);
		assertEquals(waterHex.get(i).getLocation().getY(), -2);
		i++;
		assertEquals(waterHex.get(i).getLocation().getX(), 3);
		assertEquals(waterHex.get(i).getLocation().getY(), 0);
		
		List<PortHex> ports = board.getPorts();
		i = 0;
		assertEquals(ports.get(i).getLocation().getX(), -3);
		assertEquals(ports.get(i).getLocation().getY(), 0);
		assertEquals(ports.get(i).getRatio(), 3);
		assertEquals(ports.get(i).getPortType(), PortType.THREE);
		i++;
		assertEquals(ports.get(i).getLocation().getX(), -3);
		assertEquals(ports.get(i).getLocation().getY(), 2);
		assertEquals(ports.get(i).getRatio(), 2);
		assertEquals(ports.get(i).getPortType(), PortType.WOOD);
		i++;
		assertEquals(ports.get(i).getLocation().getX(), -2);
		assertEquals(ports.get(i).getLocation().getY(), 3);
		assertEquals(ports.get(i).getRatio(), 2);
		assertEquals(ports.get(i).getPortType(), PortType.BRICK);
		i++;
		assertEquals(ports.get(i).getLocation().getX(), -1);
		assertEquals(ports.get(i).getLocation().getY(), -2);
		assertEquals(ports.get(i).getRatio(), 2);
		assertEquals(ports.get(i).getPortType(), PortType.WHEAT);
		i++;
		assertEquals(ports.get(i).getLocation().getX(), 0);
		assertEquals(ports.get(i).getLocation().getY(), 3);
		assertEquals(ports.get(i).getRatio(), 3);
		assertEquals(ports.get(i).getPortType(), PortType.THREE);
		i++;
		assertEquals(ports.get(i).getLocation().getX(), 1);
		assertEquals(ports.get(i).getLocation().getY(), -3);
		assertEquals(ports.get(i).getRatio(), 2);
		assertEquals(ports.get(i).getPortType(), PortType.ORE);
		i++;
		assertEquals(ports.get(i).getLocation().getX(), 2);
		assertEquals(ports.get(i).getLocation().getY(), 1);
		assertEquals(ports.get(i).getRatio(), 3);
		assertEquals(ports.get(i).getPortType(), PortType.THREE);
		i++;
		assertEquals(ports.get(i).getLocation().getX(), 3);
		assertEquals(ports.get(i).getLocation().getY(), -3);
		assertEquals(ports.get(i).getRatio(), 3);
		assertEquals(ports.get(i).getPortType(), PortType.THREE);
		i++;
		assertEquals(ports.get(i).getLocation().getX(), 3);
		assertEquals(ports.get(i).getLocation().getY(), -1);
		assertEquals(ports.get(i).getRatio(), 2);
		assertEquals(ports.get(i).getPortType(), PortType.SHEEP);

	}
	
//	@Test
//	public void testGenerateRandomBoard() {
//		board = new Board(true, true, true);
//		
//		List<ResourceHex> resourceHexes = board.getResourceHexes();
//		int i = 0;
//		assertEquals(resourceHexes.get(i).getLocation().getX(), -2);
//		assertEquals(resourceHexes.get(i).getLocation().getY(), 0);
//		i++;
//		assertEquals(resourceHexes.get(i).getLocation().getX(), -2);
//		assertEquals(resourceHexes.get(i).getLocation().getY(), 1);
//		i++;
//		assertEquals(resourceHexes.get(i).getLocation().getX(), -2);
//		assertEquals(resourceHexes.get(i).getLocation().getY(), 2);
//		i++;
//		assertEquals(resourceHexes.get(i).getLocation().getX(), -1);
//		assertEquals(resourceHexes.get(i).getLocation().getY(), -1);
//		i++;
//		assertEquals(resourceHexes.get(i).getLocation().getX(), -1);
//		assertEquals(resourceHexes.get(i).getLocation().getY(), 0);
//		i++;
//		assertEquals(resourceHexes.get(i).getLocation().getX(), -1);
//		assertEquals(resourceHexes.get(i).getLocation().getY(), 1);
//		i++;
//		assertEquals(resourceHexes.get(i).getLocation().getX(), -1);
//		assertEquals(resourceHexes.get(i).getLocation().getY(), 2);
//		i++;
//		assertEquals(resourceHexes.get(i).getLocation().getX(), 0);
//		assertEquals(resourceHexes.get(i).getLocation().getY(), -1);
//		i++;
//		assertEquals(resourceHexes.get(i).getLocation().getX(), 0);
//		assertEquals(resourceHexes.get(i).getLocation().getY(), 0);
//		i++;
//		assertEquals(resourceHexes.get(i).getLocation().getX(), 0);
//		assertEquals(resourceHexes.get(i).getLocation().getY(), 1);
//		i++;
//		assertEquals(resourceHexes.get(i).getLocation().getX(), 0);
//		assertEquals(resourceHexes.get(i).getLocation().getY(), 2);
//		i++;
//		assertEquals(resourceHexes.get(i).getLocation().getX(), 1);
//		assertEquals(resourceHexes.get(i).getLocation().getY(), -2);
//		i++;
//		assertEquals(resourceHexes.get(i).getLocation().getX(), 1);
//		assertEquals(resourceHexes.get(i).getLocation().getY(), -1);
//		i++;
//		assertEquals(resourceHexes.get(i).getLocation().getX(), 1);
//		assertEquals(resourceHexes.get(i).getLocation().getY(), 0);
//		i++;
//		assertEquals(resourceHexes.get(i).getLocation().getX(), 1);
//		assertEquals(resourceHexes.get(i).getLocation().getY(), 1);
//		i++;
//		assertEquals(resourceHexes.get(i).getLocation().getX(), 2);
//		assertEquals(resourceHexes.get(i).getLocation().getY(), -2);
//		i++;
//		assertEquals(resourceHexes.get(i).getLocation().getX(), 2);
//		assertEquals(resourceHexes.get(i).getLocation().getY(), -1);
//		i++;
//		assertEquals(resourceHexes.get(i).getLocation().getX(), 2);
//		assertEquals(resourceHexes.get(i).getLocation().getY(), 0);
//		
//		List<WaterHex> waterHex = board.getWaterHexes();
//		i = 0;
//		assertEquals(waterHex.get(i).getLocation().getX(), -3);
//		assertEquals(waterHex.get(i).getLocation().getY(), 1);
//		i++;
//		assertEquals(waterHex.get(i).getLocation().getX(), -3);
//		assertEquals(waterHex.get(i).getLocation().getY(), 3);
//		i++;
//		assertEquals(waterHex.get(i).getLocation().getX(), -2);
//		assertEquals(waterHex.get(i).getLocation().getY(), -1);
//		i++;
//		assertEquals(waterHex.get(i).getLocation().getX(), -1);
//		assertEquals(waterHex.get(i).getLocation().getY(), 3);
//		i++;
//		assertEquals(waterHex.get(i).getLocation().getX(), 0);
//		assertEquals(waterHex.get(i).getLocation().getY(), -3);
//		i++;
//		assertEquals(waterHex.get(i).getLocation().getX(), 1);
//		assertEquals(waterHex.get(i).getLocation().getY(), 3);
//		i++;
//		assertEquals(waterHex.get(i).getLocation().getX(), 2);
//		assertEquals(waterHex.get(i).getLocation().getY(), -1);
//		i++;
//		assertEquals(waterHex.get(i).getLocation().getX(), 3);
//		assertEquals(waterHex.get(i).getLocation().getY(), 1);
//		i++;
//		assertEquals(waterHex.get(i).getLocation().getX(), 3);
//		assertEquals(waterHex.get(i).getLocation().getY(), 3);
//		
//		List<PortHex> ports = board.getPorts();
//		i = 0;
//		assertEquals(ports.get(i).getLocation().getX(), -3);
//		assertEquals(ports.get(i).getLocation().getY(), 0);
//		assertEquals(ports.get(i).getRatio(), 3);
//		i++;
//		assertEquals(ports.get(i).getLocation().getX(), -3);
//		assertEquals(ports.get(i).getLocation().getY(), 2);
//		assertEquals(ports.get(i).getRatio(), 2);
//		i++;
//		assertEquals(ports.get(i).getLocation().getX(), -2);
//		assertEquals(ports.get(i).getLocation().getY(), 3);
//		assertEquals(ports.get(i).getRatio(), 2);
//		i++;
//		assertEquals(ports.get(i).getLocation().getX(), -1);
//		assertEquals(ports.get(i).getLocation().getY(), -2);
//		assertEquals(ports.get(i).getRatio(), 2);
//		i++;
//		assertEquals(ports.get(i).getLocation().getX(), 0);
//		assertEquals(ports.get(i).getLocation().getY(), 3);
//		assertEquals(ports.get(i).getRatio(), 3);
//		i++;
//		assertEquals(ports.get(i).getLocation().getX(), 1);
//		assertEquals(ports.get(i).getLocation().getY(), -2);
//		assertEquals(ports.get(i).getRatio(), 2);
//		i++;
//		assertEquals(ports.get(i).getLocation().getX(), 2);
//		assertEquals(ports.get(i).getLocation().getY(), 3);
//		assertEquals(ports.get(i).getRatio(), 3);
//		i++;
//		assertEquals(ports.get(i).getLocation().getX(), 3);
//		assertEquals(ports.get(i).getLocation().getY(), 0);
//		assertEquals(ports.get(i).getRatio(), 3);
//		i++;
//		assertEquals(ports.get(i).getLocation().getX(), 3);
//		assertEquals(ports.get(i).getLocation().getY(), 2);
//		assertEquals(ports.get(i).getRatio(), 2);
//
//	}
	
}











