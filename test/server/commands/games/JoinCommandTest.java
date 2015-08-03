package server.commands.games;

import static org.junit.Assert.*;

import org.junit.*;

import com.google.gson.Gson;

import shared.communication.input.GamesCreateInput;
import shared.communication.input.GamesJoinInput;
import shared.communication.input.move.BuyDevCardInput;
import shared.definitions.CatanColor;
import client.data.*;

public class JoinCommandTest {
	
	
	@Before
	public void setUp(){
	}

	@Test
	public void testBadInput() {
		BuyDevCardInput input = new BuyDevCardInput(0);
		JoinCommand joinCommand = new JoinCommand();
		GameInfo output ;
	}
	
	@Test
	public void testNotInGame() {
		GamesCreateInput createInput = new GamesCreateInput("Bob", true, true, true);
		CreateCommand createCommand = new CreateCommand();
		GameInfo output = (GameInfo) createCommand.execute(new Gson().toJson(createInput));
		
		assertEquals(output.getTitle(),"Bob");
		// the rejoin runs before this
		assertEquals(output.getId(),4);
		assertEquals(output.getPlayers().size(),4);
		
		GamesJoinInput joinInput = new GamesJoinInput(0, CatanColor.BROWN);
		JoinCommand joinCommand = new JoinCommand();
		GameInfo joinOutput;
		
		//assertEquals(joinOutput.getId(),0);
		//assertEquals(joinOutput.getTitle(),"Bob");
		//assertEquals(joinOutput.getPlayers().get(1).getColor(),CatanColor.BROWN);

		
	}
	
	@Test
	public void testRejoinInGame() {
		GamesCreateInput createInput = new GamesCreateInput("Bob2", true, true, true);
		CreateCommand createCommand = new CreateCommand();
		GameInfo output = (GameInfo) createCommand.execute(new Gson().toJson(createInput));
		
		assertEquals(output.getTitle(),"Bob2");
		// 3 because of other tests run
		assertEquals(output.getId(),3);
		assertEquals(output.getPlayers().size(),4);
		
		GamesJoinInput joinInput = new GamesJoinInput(0, CatanColor.BROWN);
		JoinCommand joinCommand = new JoinCommand();
		//GameInfo joinOutput = (GameInfo) joinCommand.execute(new Gson().toJson(joinInput));
		
//		assertEquals(joinOutput.getId(),0);
//		assertEquals(joinOutput.getTitle(),"Bob");
//		assertEquals(joinOutput.getPlayers().get(0).getColor(),CatanColor.BROWN);

		
	}
}
