package server.commands.games;

import static org.junit.Assert.*;

import org.junit.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import server.ServerException;
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
		try{
		BuyDevCardInput input = new BuyDevCardInput(0);
		JoinCommand joinCommand = new JoinCommand();
		String in = new Gson().toJson(input);
		GameInfo output = null;
		try {
			output = (GameInfo) joinCommand.execute(in);
		} catch (ServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assert(output == null);
		}
		catch (NullPointerException e){
			assert(true);
		}
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
		Integer joinOutput = null;
		try {
			joinOutput = (Integer) joinCommand.execute(new ObjectMapper().writeValueAsString(joinInput));
		} catch (JsonProcessingException | ServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertEquals(joinOutput,new Integer(0));

		
	}
	
	@Test
	public void testRejoinInGame() {
		GamesCreateInput createInput = new GamesCreateInput("Bob2", true, true, true);
		CreateCommand createCommand = new CreateCommand();
		GameInfo output = (GameInfo) createCommand.execute(new Gson().toJson(createInput));
		
		assertEquals(output.getTitle(),"Bob2");
		assertEquals(output.getId(),3);
		assertEquals(output.getPlayers().size(),4);
		
		GamesJoinInput joinInput = new GamesJoinInput(0, CatanColor.BROWN);
		JoinCommand joinCommand = new JoinCommand();
		Integer joinOutput = null;
		try {
			joinOutput = (Integer) joinCommand.execute(new ObjectMapper().writeValueAsString(joinInput));
		} catch (JsonProcessingException | ServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertEquals(joinOutput,new Integer(0));

		
	}
}
