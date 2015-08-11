package server.commands.move;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

import client.domestic.Trade;
import shared.communication.input.move.OfferTradeInput;
import shared.model.GameModel;

public class OfferTradeCommand extends MoveCommand {

	public OfferTradeCommand() {
		super();
	}

	private transient GameModel model;
	
	
	/**
	 * @param input is a valid OfferTradeInput object
	 * @pre The offering player must have the resources being offered in the specified quantities.
	 * @post The trade is stored in the server model and offered to the other player.
	 * @return The GameModel after executing the changes
	 */
	@Override
	public Object execute(String input) {
		OfferTradeInput offerTradeInput;
		try {
			offerTradeInput = new ObjectMapper().readValue(input, OfferTradeInput.class);
			Trade trade = new Trade(offerTradeInput.getOffer().getBrick(), offerTradeInput.getOffer().getWood(), offerTradeInput.getOffer().getSheep(), offerTradeInput.getOffer().getWheat(), 
					offerTradeInput.getOffer().getOre(), offerTradeInput.getPlayerIndex(), offerTradeInput.getReceiver());
			model.setTrade(trade);
			model.getTurnTracker().setStatus("Trading");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return model;
	}

	public void setGameModel(GameModel model) {
		this.model = model;
	}

}
