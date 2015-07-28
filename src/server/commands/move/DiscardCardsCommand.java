package server.commands.move;

import server.commands.ICommand;
import shared.communication.input.Input;
import shared.model.GameModel;

public class DiscardCardsCommand implements ICommand{
	
	private GameModel model;

	/**
	 * @pre The player has the discarded resources
	 * @post the resource hand will be discarded from the player's bank and added to the bank.
	 * @return the updated GameModel
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