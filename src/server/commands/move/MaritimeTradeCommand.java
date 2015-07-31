package server.commands.move;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import server.commands.ICommand;
import shared.communication.input.Input;
import shared.communication.input.move.MaritimeTradeInput;
import shared.communication.input.move.SendChatInput;
import shared.model.GameModel;
import shared.model.bank.BankException;
import shared.model.bank.ResourceHand;
import shared.model.board.PlayerID;

public class MaritimeTradeCommand extends MoveCommand {

	private GameModel model;
	
	
	/**
	 * @param input is a valid MaritimeTradeInput object
	 * @pre The player must have the specified input resource in the given quantity. The player must have a port for the resource if the ratio is less than 4. There must be at least one of the specified output resource in the game bank.
	 * @post The specified input resources are moved from the player to the game bank and the output resource is moved from the game bank to the player's bank.
	 * @return The GameModel after executing the changes
	 */
	@Override
	public Object execute(String input) {
		MaritimeTradeInput maritimeTradeInput;
		try {
			maritimeTradeInput = new ObjectMapper().readValue(input, MaritimeTradeInput.class);
			int ratio = maritimeTradeInput.getRatio();
			ResourceHand rh = new ResourceHand();
			ResourceHand bankrh = new ResourceHand();
			switch (maritimeTradeInput.getInputResource()){
			case "Wood":{
				rh.setWood(-1 * ratio);
				bankrh.setWood(ratio);
				break;
			}
			case "Brick":{
				rh.setBrick(-1 * ratio);
				bankrh.setBrick(ratio);
				break;
			}
			case "Sheep":{
				rh.setSheep(-1 * ratio);
				bankrh.setSheep(ratio);
				break;
			}
			case "Wheat":{
				rh.setWheat(-1 * ratio);
				bankrh.setWheat(ratio);
				break;
			}
			case "Ore":{
				rh.setOre(-1 * ratio);
				bankrh.setOre(ratio);
				break;
			}
			}
			switch (maritimeTradeInput.getOutputResource()){
			case "Wood":{
				rh.setWood(1);
				bankrh.setWood(-1);
				break;
			}
			case "Brick":{
				rh.setBrick(1);
				bankrh.setBrick(-1);
				break;
			}
			case "Sheep":{
				rh.setSheep(1);
				bankrh.setSheep(-1);
				break;
			}
			case "Wheat":{
				rh.setWheat(1);
				bankrh.setWheat(-1);
				break;
			}
			case "Ore":{
				rh.setOre(1);
				bankrh.setOre(-1);
				break;
			}
			}
			try {
				model.getPlayer(new PlayerID(maritimeTradeInput.getPlayerIndex())).getPlayerBank().modifyRC(rh);
				model.getBank().modifyRC(bankrh);
			} catch (BankException e) {
				e.printStackTrace();
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return model;
	}

	public void setGameModel(GameModel model) {
		this.model = model;
	}

}
