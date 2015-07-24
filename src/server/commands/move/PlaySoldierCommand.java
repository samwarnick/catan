package server.commands.move;

import server.commands.ICommand;
import shared.communication.input.Input;
import shared.model.GameModel;

public class PlaySoldierCommand implements ICommand {

	private GameModel model;
	
	
	/**
	 * @param input is a valid PlaySoldierInput object
	 * @pre The specified location is not the current location of the robber. The victim player has at least one resource.
	 * @post The robber is placed in the specified location. If a player is being robbed
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
