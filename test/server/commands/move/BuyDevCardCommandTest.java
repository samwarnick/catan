package server.commands.move;

import static org.junit.Assert.*;

import org.junit.*;

import com.google.gson.Gson;

import shared.communication.input.move.BuyDevCardInput;
import shared.model.GameModel;
import shared.model.bank.BankException;
import shared.model.bank.DevelopmentHand;
import shared.model.bank.ResourceHand;
import shared.model.board.PlayerID;
import shared.model.player.Cities;
import shared.model.player.Player;

public class BuyDevCardCommandTest {
	
	private GameModel model;
	
	@Before
	public void setUp(){
		model = GameModel.getDefaultModel();
	}

	@Test
	public void testBadInput() {
		BuyDevCardInput input = new BuyDevCardInput(0);
	}
	
	@Test
	public void testgoodInput() {
		BuyDevCardInput input = new BuyDevCardInput(0);
		GameModel testModel = GameModel.getDefaultModel();
		Player testPlayer = testModel.getPlayer(new PlayerID(0));
		try {
			BuyDevCardCommand command = new BuyDevCardCommand();
			command.setGameModel(model);
			model = (GameModel) command.execute(new Gson().toJson(input));
			model.getPlayer(new PlayerID(0)).getPlayerBank().modifyRC(new ResourceHand(1,1,1,1,1));
			testModel.getPlayer(new PlayerID(0)).getPlayerBank().modifyRC(new ResourceHand(1,1,1,1,1));
			testPlayer.getPlayerBank().modifyRC(new ResourceHand(0,0,-1,-1,-1));
			testModel.getBank().modifyRC(new ResourceHand(0,0,1,1,1));
			//need to modify the dev cards the same as Isaac.
			testModel.getBank().modifyDC(new DevelopmentHand(-1,0,0,0,0));
			testPlayer.getPlayerBank().modifyDC(new DevelopmentHand(1,0,0,0,0));
		} catch (BankException e) {
			e.printStackTrace();
		}
		
		assertEquals(testPlayer.getPlayerBank(),model.getPlayer(new PlayerID(0)).getPlayerBank());
		assertEquals(testModel.getBank(),model.getBank());
		
		
	}
}