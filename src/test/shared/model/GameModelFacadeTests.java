package test.shared.model;

import java.io.IOException;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import shared.locations.HexLocation;
import shared.definitions.CatanColor;
import shared.model.GameModelFacade;
import shared.model.TooManyPlayersException;
import shared.model.TurnTracker;
import shared.model.bank.BankException;
import shared.model.bank.DevelopmentHand;
import shared.model.bank.ResourceHand;
import shared.model.board.Board;
import shared.model.board.Robber;
import shared.model.player.ActivePlayerFacade;
import shared.model.player.Color;
import shared.model.player.InactivePlayerFacade;
import shared.model.player.Player;

public class GameModelFacadeTests {
	
	private GameModelFacade GMF;
	
	@BeforeClass void prep(){
		GMF = new GameModelFacade(0);
		try {
			Player harold = new Player(CatanColor.BROWN, "Harold", 11);
			harold.getPlayerBank().setRC(3);
			harold.setPlayerFacade(new ActivePlayerFacade(harold));
			Player gretchen = new Player(CatanColor.GREEN, "Gretchen", 22);
			gretchen.getPlayerBank().setLargestArmyCard(true);
			gretchen.getPlayerBank().setLongestRoadCard(true);
			gretchen.setPlayerFacade(new InactivePlayerFacade(gretchen));
			Player ingrid  = new Player(CatanColor.ORANGE, "Ingrid", 33);
			ingrid.getPlayerBank().modifyRC(new ResourceHand(2,2,2,1,0));
			ingrid.setPlayerFacade(new InactivePlayerFacade(ingrid));
			Player jerry = new Player(CatanColor.BLUE, "Jerry", 44);
			jerry.getPlayerBank().modifyRC(new ResourceHand(2,2,2,1,1));
			jerry.setPlayerFacade(new InactivePlayerFacade(jerry));
			GMF.getGameModel().addPlayer(harold);
			GMF.getGameModel().addPlayer(gretchen);
			GMF.getGameModel().addPlayer(ingrid);
			GMF.getGameModel().addPlayer(jerry);
			GMF.getGameModel().setBoard(new Board(false, false, false));
			GMF.getGameModel().getBank().setRC(19);
			DevelopmentHand dh = new DevelopmentHand(14,4,2,2,2);
			GMF.getGameModel().getBank().setDC(dh);
		} catch (TooManyPlayersException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BankException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	@Test
	public void testCanAcceptTrade(){
		Player p = GMF.getGameModel().getPlayers().get(0);
		boolean passed = GMF.canAcceptTrade(p, new ResourceHand());
		assert(passed);
		passed = GMF.canAcceptTrade(p, new ResourceHand(5,5,5,5,5));
		assert(!passed);
		passed = GMF.canAcceptTrade(p, new ResourceHand(-1,-1,-1,-1,-1));
		assert(!passed);
	}
	
	@Test
	public void testCanDiscardCards(){
		Player p0 = GMF.getGameModel().getPlayers().get(0);
		Player p1 = GMF.getGameModel().getPlayers().get(1);
		Player p2 = GMF.getGameModel().getPlayers().get(2);
		Player p3 = GMF.getGameModel().getPlayers().get(3);
		boolean passed = GMF.canDiscardCards(p0);
		assert(passed);
		passed = GMF.canDiscardCards(p1);
		assert(!passed);
		passed = GMF.canDiscardCards(p2);
		assert(!passed);
		passed = GMF.canDiscardCards(p3);
		assert(passed);
	}
	
	@Test
	public void testCanRollDice(){
		try {
			GMF.getGameModel().getTurnTracker().setStatus("Rolling");
			GMF.getGameModel().getTurnTracker().setCurrentTurn(0);
			boolean passed = GMF.canRollDice(GMF.getGameModel().getPlayers().get(0));
			assert(passed);
			passed = GMF.canRollDice(GMF.getGameModel().getPlayers().get(1));
			assert(!passed);
			GMF.getGameModel().getTurnTracker().setStatus("NotRolling");
			passed = GMF.canRollDice(GMF.getGameModel().getPlayers().get(0));
			assert(!passed);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		GMF.getGameModel().setRobber(new Robber(new HexLocation(1,1)));
	}
	
	@Test
	public void testCanFinishTurn(){
		boolean passed = GMF.canFinishTurn(GMF.getGameModel().getPlayers().get(0));
		assert(passed);
		passed = GMF.canFinishTurn(GMF.getGameModel().getPlayers().get(1));
		assert(!passed);
	}
	
	@Test
	public void testCanBuyDevCard(){
		Player p0 = GMF.getGameModel().getPlayers().get(0);
		Player p1 = GMF.getGameModel().getPlayers().get(1);
		Player p2 = GMF.getGameModel().getPlayers().get(2);
		boolean passed = GMF.canBuyDevCard(p0);
		assert(passed);
		passed = GMF.canBuyDevCard(p1);
		assert(!passed);
		passed = GMF.canBuyDevCard(p2);
		assert(!passed);
		
	}
	
	@Test
	public void testCanPlayDevCard(){
		Player p0 = GMF.getGameModel().getPlayers().get(0);
		Player p1 = GMF.getGameModel().getPlayers().get(1);
		Player p2 = GMF.getGameModel().getPlayers().get(2);
		p0.setHasPlayedCard(true);
		p1.setHasPlayedCard(false);
		boolean passed = GMF.canPlayDevCard(p0);
		assert(!passed);
		passed = GMF.canPlayDevCard(p1);
		assert(passed);
		passed = GMF.canPlayDevCard(p2);
		assert(passed);
	}

}
