package server.commands.move;

import com.google.gson.Gson;

import server.ServerException;
import shared.communication.input.move.PlayRoadBuildingInput;
import shared.locations.EdgeLocation;
import shared.model.board.PlayerID;
import shared.model.board.Road;
import shared.model.player.Player;

public class PlayRoadBuildingCommand extends MoveCommand {

	/**
	 * @param input is a valid PlayRoadBuildingInput object
	 * @pre The first road location must connect to one of the player's existing roads. The second road location must connect to one of the player's existing roads or the first road. The locations must be on a valid land edge and not occupied by another player. The player must have two unused roads.
	 * @post The player has two less unused roads. The two new roads appear at the specified locations on the map. If the player qualifies, the player is awarded the 'longest road' card.
	 * @return The GameModel after executing the changes
	 * @throws ServerException 
	 */
	@Override
	public Object execute(String input) throws ServerException {
		Gson parser = new Gson();
		PlayRoadBuildingInput in = parser.fromJson(input, PlayRoadBuildingInput.class);
		EdgeLocation location1 = in.getLocation1();
		EdgeLocation location2 = in.getLocation2();
		int playerIndex = in.getPlayerIndex();
		
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

	
}
