package server.commands.move;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import server.ServerException;
import shared.communication.input.move.RollNumberInput;
import shared.locations.HexLocation;
import shared.locations.VertexDirection;
import shared.locations.VertexLocation;
import shared.model.GameModel;
import shared.model.bank.BankException;
import shared.model.bank.ResourceCard;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RollNumberTest {
private GameModel model;
	
	@BeforeClass
	void setup(){
		model = GameModel.getDefaultGM();
	}
	
	@Test
	void test1() throws BankException{
		GameModel costModel;
		RollNumberInput inputcost = new RollNumberInput();
		RollNumberCommand command = new RollNumberCommand();
		command.setGameModel(model);
		
		
		
	}
}
