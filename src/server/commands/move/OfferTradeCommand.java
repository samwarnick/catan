package server.commands.move;

import server.commands.ICommand;
import shared.communication.input.Input;
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
		// TODO Auto-generated method stub
		return null;
	}

	public void setModel(GameModel model) {
		this.model = model;
	}

}
