package server.commands.move;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import server.ServerException;
import shared.communication.input.move.BuildSettlementInput;
import shared.locations.VertexDirection;
import shared.locations.VertexLocation;
import shared.locations.HexLocation;
import shared.model.GameModel;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class BuildSettlementTest {

	private GameModel model;
	
	@BeforeClass
	void setup(){
		model = GameModel.getDefaultGM();
	}
	
	@Test
	void test1(){
		GameModel costModel;
		BuildSettlementInput inputfree = new BuildSettlementInput(0, true, new VertexLocation(new HexLocation(0,0), 
				VertexDirection.NorthEast));
		BuildSettlementInput inputcost = new BuildSettlementInput(0, false, new VertexLocation(new HexLocation(0,0), 
				VertexDirection.NorthEast));
		BuildSettlementCommand command = new BuildSettlementCommand();
		command.setGameModel(model);
		
		try {
			model.getBoard().getBuildings().clear();
			model = (GameModel) command.execute(new ObjectMapper().writeValueAsString(inputfree));
			costModel = (GameModel) command.execute(new ObjectMapper().writeValueAsString(inputcost));
			assertEquals(model.getBoard().getBuildings().get(0).getOwner().getPlayerid(),0);
			assertEquals(costModel.getBoard().getBuildings().get(0).getOwner().getPlayerid(),0);
			assertEquals(costModel.getPlayers().get(0).getPlayerBank().getSheep(),1);
			assertEquals(costModel.getPlayers().get(0).getPlayerBank().getWood(),1);
			assertEquals(costModel.getPlayers().get(0).getPlayerBank().getBrick(),1);
			assertEquals(costModel.getPlayers().get(0).getPlayerBank().getWheat(),1);
			
			
		} catch (JsonProcessingException | ServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}}
