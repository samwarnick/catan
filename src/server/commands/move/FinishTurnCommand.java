package server.commands.move;

import server.commands.ICommand;
import shared.communication.input.Input;
import shared.model.GameModel;

public class FinishTurnCommand implements ICommand {

	private GameModel model;
	
	
	/**
	 * @param input is a valid FinishTurnInput object
	 * @pre None
	 * @post The cards in the player's new development card hand are moved to the old development card hand. The player is now inactive and the next player becomes active. 
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
