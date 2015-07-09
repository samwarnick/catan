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
		try {
			robber.moveRobber(locationValid2);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		assertEquals(locationValid2, robber.getLocation());
		try{
			robber.moveRobber(locationValid2);
			assertTrue(false);
		}
		catch(Exception e){
			assertTrue(true);
		}
		HexLocation locationInvalid = new HexLocation(-3, 0);
		try{
			robber.moveRobber(locationInvalid);
			assertTrue(true);
		}
		catch(Exception e){
			assertTrue(false);
		}
	}
}