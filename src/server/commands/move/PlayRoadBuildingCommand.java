package server.commands.move;

import server.ServerException;
import server.commands.ICommand;
import shared.communication.input.Input;
import shared.communication.input.move.BuildRoadInput;
import shared.communication.input.move.PlayRoadBuildingInput;
import shared.locations.EdgeLocation;
import shared.model.GameModel;
import shared.model.board.PlayerID;
import shared.model.board.Road;
import shared.model.player.Player;

public class PlayRoadBuildingCommand implements ICommand {

	private GameModel model;
	
	
	/**
	 * @param input is a valid PlayRoadBuildingInput object
	 * @pre The first road location must connect to one of the player's existing roads. The second road location must connect to one of the player's existing roads or the first road. The locations must be on a valid land edge and not occupied by another player. The player must have two unused roads.
	 * @post The player has two less unused roads. The two new roads appear at the specified locations on the map. If the player qualifies, the player is awarded the 'longest road' card.
	 * @return The GameModel after executing the changes
	 * @throws ServerException 
	 */
	@Override
	public Object execute(Input input) throws ServerException {
		EdgeLocation location1 = ((PlayRoadBuildingInput)input).getLocation1();
		EdgeLocation location2 = ((PlayRoadBuildingInput)input).getLocation2();
		int playerIndex = ((BuildRoadInput) input).getPlayerIndex();
		
		Player player = model.getPlayer(new PlayerID(playerIndex));
		model.getBoard().getRoads().add(new Road(player.getPlayerID(), location1));
		model.getBoard().getRoads().add(new Road(player.getPlayerID(), location2));
		try {
			player.getRoads().buildRoad();
			player.getRoads().buildRoad();
		} catch (Exception e) {
			throw new ServerException("Error with changing roads available when playing road building card:\n" + e.getMessage());
		}
		
		model.assignLongestRoad();
		
		return model;
	}

	public void setModel(GameModel model) {
		this.model = model;
	}
	
}
