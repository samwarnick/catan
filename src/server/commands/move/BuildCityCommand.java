package server.commands.move;

import java.util.List;

import server.commands.ICommand;
import shared.communication.input.Input;
import shared.communication.input.move.BuildCityInput;
import shared.locations.VertexLocation;
import shared.model.GameModel;
import shared.model.board.Vertex;

public class BuildCityCommand implements ICommand{
	
	private GameModel model;

	
	/**
	 * @pre the location is a valid location for the player to build a city, the player has enough resources to build a city.
	 * @post the city will be placed at the location and the resources will be removed from the player
	 * @return the updated GameModel
	 */
	@Override
	public Object execute(Input input) {
		int playerIndex = ((BuildCityInput) input).getPlayerIndex();
		VertexLocation location = ((BuildCityInput) input).getVertexLocation();
		
		List<Vertex> buildings = model.getBoard().getBuildings();
		
	}
	
	public void setModel(GameModel model){
		this.model = model;
	}

}
