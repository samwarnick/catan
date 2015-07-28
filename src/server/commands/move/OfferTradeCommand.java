package server.commands.move;

import client.domestic.Trade;
import server.commands.ICommand;
import shared.communication.input.Input;
import shared.communication.input.move.OfferTradeInput;
import shared.model.GameModel;

public class OfferTradeCommand implements ICommand {

	private GameModel model;
	
	
	/**
	 * @param input is a valid OfferTradeInput object
	 * @pre The offering player must have the resources being offered in the specified quantities.
	 * @post The trade is stored in the server model and offered to the other player.
	 * @return The GameModel after executing the changes
	 */
	@Override
	public Object execute(Input input) {
		OfferTradeInput offerTradeInput = (OfferTradeInput) input;
		Trade trade = new Trade(offerTradeInput.getOffer().getBrick(), offerTradeInput.getOffer().getWood(), offerTradeInput.getOffer().getSheep(), offerTradeInput.getOffer().getWheat(), 
				offerTradeInput.getOffer().getOre(), offerTradeInput.getPlayerIndex(), offerTradeInput.getReceiver());
		model.setTrade(trade);
		model.getTurnTracker().setStatus("Trading");
		return model;
	}

	public void setModel(GameModel model) {
		this.model = model;
	}

}
