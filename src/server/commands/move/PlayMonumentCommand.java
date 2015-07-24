package server.commands.move;

import server.commands.ICommand;
import shared.communication.input.Input;
import shared.model.GameModel;

public class PlayMonumentCommand implements ICommand {
	
	private GameModel model;
	
	
	/**
	 * @param input is a valid PlayMonumentInput object
	 * @pre The player must have enough monument cards for the player to have 10 victory points
	 * @post The player gained a victory point. The method returns a not null GameModel
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
