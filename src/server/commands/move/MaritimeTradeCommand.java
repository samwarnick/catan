package server.commands.move;

import server.commands.ICommand;
import shared.communication.input.Input;
import shared.model.GameModel;

public class MaritimeTradeCommand implements ICommand {

	private GameModel model;
	
	
	/**
	 * @param input is a valid MaritimeTradeInput object
	 * @pre The player must have the specified input resource in the given quantity. The player must have a port for the resource if the ratio is less than 4. There must be at least one of the specified output resource in the game bank.
	 * @post The specified input resources are moved from the player to the game bank and the output resource is moved from the game bank to the player's bank.
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
