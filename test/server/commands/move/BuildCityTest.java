package server.commands.move;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import server.ServerException;
import shared.communication.input.move.BuildCityInput;
import shared.locations.HexLocation;
import shared.locations.VertexDirection;
import shared.locations.VertexLocation;
import shared.model.GameModel;
import shared.model.bank.BankException;
import shared.model.bank.ResourceCard;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class BuildCityTest {

	private GameModel model;
	
	@BeforeClass
	void setup(){
		model = GameModel.getDefaultGM();
	}
	
	@Test
	void test1() throws BankException{
		GameModel costModel;
		BuildCityInput inputcost = new BuildCityInput(0, new VertexLocation(new HexLocation(0,0), 
				VertexDirection.NorthEast));
		BuildCityCommand command = new BuildCityCommand();
		command.setGameModel(model);
		
		try {
			model.getBoard().getBuildings().clear();
			ResourceCard rc = new ResourceCard();
			rc.setQuantity(3);
			model.getPlayers().get(0).getPlayerBank().setOre(rc);
			rc.setQuantity(2);
			model.getPlayers().get(0).getPlayerBank().setWheat(rc);
			costModel = (GameModel) command.execute(new ObjectMapper().writeValueAsString(inputcost));
			assertEquals(model.getBoard().getBuildings().get(0).getOwner().getPlayerid(),0);
			assertEquals(costModel.getBoard().getBuildings().get(0).getOwner().getPlayerid(),0);
			assertEquals(costModel.getPlayers().get(0).getPlayerBank().getOre(),0);
			assertEquals(costModel.getPlayers().get(0).getPlayerBank().getWheat(),0);
			
			
		} catch (JsonProcessingException | ServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}}
