package server.commands.move;

import server.commands.ICommand;
import shared.communication.input.Input;
import shared.model.GameModel;

public class RobPlayerCommand implements ICommand {

	private GameModel model;
	
	
	/**
	 * @param input is a valid RobPlayerInput object
	 * @pre The specified location is not the current location of the robber. The victim index must be a valid player index and the player must have at least one resource (unless no player is being robbed).
	 * @post The robber location is set to the specified location. If a player is being robbed, that player has one less resource and the robbing player received that resource.
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
