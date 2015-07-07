package test.shared.model;

import java.io.IOException;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import shared.definitions.CatanColor;
import shared.model.player.Color;
import shared.model.player.Player;

public class GameModelFacadeTests {
	
	private Player player;
	
	@Before void prep(){
		player = new Player(CatanColor.RED, "George", 1);
	}
	
	@Test
	public void testCanAcceptTrade(){
		
	}
	
	@Test
	public void testCanDiscardCards(){
		
	}
	
	@Test
	public void testCanRollDice(){
		
	}
	
	@Test
	public void testCanBuildRoad(){
		
	}
	
	@Test
	public void testCanBuildSettlement(){
		
	}
	
	@Test
	public void testCanBuildCity(){
		
	}
	
	@Test
	public void testCanOfferTrade(){
		
	}
	
	@Test
	public void testCanMaritimeTrade(){
		
	}
	
	@Test
	public void testCanRobPlayer(){
		
	}
	
	@Test
	public void testCanFinishTurn(){
		
	}
	
	@Test
	public void testCanBuyDevCard(){
		
	}
	
	@Test
	public void testCanPlayDevCard(){
		
	}

}
