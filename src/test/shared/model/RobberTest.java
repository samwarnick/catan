package test.shared.model;

/**
 * 
 * @author Jordan Johnson
 *
 */

import static org.junit.Assert.*;

import org.junit.Test;

import shared.locations.HexLocation;
import shared.model.board.Robber;

public class RobberTest {

	@Test
	public void testMoveRobber() {
		HexLocation locationValid = new HexLocation(0, 0);
		Robber robber = new Robber(locationValid);
		assertEquals(locationValid, robber.getLocation());
		HexLocation locationValid2 = new HexLocation(-1, 0);
		robber.moveRobber(locationValid2);
		
		assertEquals(locationValid2, robber.getLocation());
		try{
			robber.moveRobber(locationValid2);
			assertTrue(false);
		}
		catch{
			assertTrue(true);
		}
		HexLocation locationInvalid = new HexLocation(-3, 0);
		try{
			robber.moveRobber(locationInvalid);
			assertTrue(false);
		}
		catch{
			assertTrue(true);
		}
	}
}