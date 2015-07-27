package server.commands.move;

import server.commands.ICommand;
import shared.communication.input.Input;
import shared.model.GameModel;

public class AcceptTradeCommand implements ICommand{
	
	private GameModel model;

	/**
	 * @pre the player is the receiver of the trade
	 * @post the trade will be made if the trade was accepted, no trade made otherwise, the tradeOffer will be reset.
	 * @return the updated GameModel is returned
	 */
	@Override
	public Object execute(Input input) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setModel(GameModel model){
		this.model = model;
	}

}