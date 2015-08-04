package server.commands.move;

import server.ServerException;
import shared.communication.input.move.BuildRoadInput;
import shared.communication.input.move.SendChatInput;
import shared.locations.EdgeDirection;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.model.GameModel;
import shared.model.board.PlayerID;
import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class BuildRoadTest {
	
	private GameModel model;
	
	@BeforeClass
	void setup(){
		model = GameModel.getDefaultGM();
	}
	
	@Test
	void test1(){
		GameModel costModel;
		BuildRoadInput inputfree = new BuildRoadInput(0, true, new EdgeLocation(new HexLocation(0,0), 
				EdgeDirection.NorthEast));
		BuildRoadInput inputcost = new BuildRoadInput(0, false, new EdgeLocation(new HexLocation(0,0), 
				EdgeDirection.NorthEast));
		BuildRoadCommand command = new BuildRoadCommand();
		command.setGameModel(model);
		
		try {
			model = (GameModel) command.execute(new ObjectMapper().writeValueAsString(inputfree));
			costModel = (GameModel) command.execute(new ObjectMapper().writeValueAsString(inputcost));
			//assertTrue(model.getBoard().getRoads().get(index);
			
		} catch (JsonProcessingException | ServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
