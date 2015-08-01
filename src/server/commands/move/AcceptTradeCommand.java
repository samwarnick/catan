package server.commands.move;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import server.commands.ICommand;
import shared.communication.input.Input;
import shared.communication.input.move.AcceptTradeInput;
import shared.communication.input.move.SendChatInput;
import shared.model.GameModel;
import shared.model.bank.BankException;
import shared.model.bank.ResourceHand;
import shared.model.board.PlayerID;

public class AcceptTradeCommand extends MoveCommand{
	
	private GameModel model;

	/**
	 * @pre the player is the receiver of the trade
	 * @post the trade will be made if the trade was accepted, no trade made otherwise, the tradeOffer will be reset.
	 * @return the updated GameModel is returned
	 */
	@Override
	public Object execute(String input) {
		AcceptTradeInput acceptTradeInput;
		try {
			acceptTradeInput = new ObjectMapper().readValue(input, AcceptTradeInput.class);
			if (acceptTradeInput.isWillAccept())
			{
				ResourceHand receiveRH = new ResourceHand(model.getTrade().getBrickNum(), model.getTrade().getWoodNum(), model.getTrade().getSheepNum(), model.getTrade().getWheatNum(), model.getTrade().getOreNum());
				ResourceHand sendRH = new ResourceHand(model.getTrade().getBrickNum() * -1, model.getTrade().getWoodNum()* -1, model.getTrade().getSheepNum()* -1, model.getTrade().getWheatNum()* -1, model.getTrade().getOreNum()* -1);
				try {
					model.getPlayer(new PlayerID(acceptTradeInput.getPlayerIndex())).getPlayerBank().modifyRC(receiveRH);
					model.getPlayer(new PlayerID(model.getTrade().getSender())).getPlayerBank().modifyRC(sendRH);
				} catch (BankException e) {
					e.printStackTrace();
				}
			}
			model.setTrade(null);
			model.getTurnTracker().setStatus("Playing");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return model;
	}
	
	public void setGameModel(GameModel model){
		this.model = model;
	}

}