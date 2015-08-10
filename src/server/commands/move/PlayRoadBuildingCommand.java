package server.commands.move;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

import server.ServerException;
import shared.communication.input.move.PlayRoadBuildingInput;
import shared.locations.EdgeLocation;
import shared.model.bank.DevelopmentHand;
import shared.model.board.PlayerID;
import shared.model.board.Road;
import shared.model.player.Player;

public class PlayRoadBuildingCommand extends MoveCommand {

	public PlayRoadBuildingCommand() {
		super();
	}

	/**
	 * @param input is a valid PlayRoadBuildingInput object
	 * @pre The first road location must connect to one of the player's existing roads. The second road location must connect to one of the player's existing roads or the first road. The locations must be on a valid land edge and not occupied by another player. The player must have two unused roads.
	 * @post The player has two less unused roads. The two new roads appear at the specified locations on the map. If the player qualifies, the player is awarded the 'longest road' card.
	 * @return The GameModel after executing the changes
	 * @throws ServerException 
	 */
	@Override
	public Object execute(String input) throws ServerException {
		PlayRoadBuildingInput in;
		try {
			in = new ObjectMapper().readValue(input, PlayRoadBuildingInput.class);
			EdgeLocation location1 = in.getLocation1();
			EdgeLocation location2 = in.getLocation2();
			int playerIndex = in.getPlayerIndex();
			
			Player player = model.getPlayer(new PlayerID(playerIndex));
			model.getBoard().getRoads().add(new Road(player.getPlayerID(), location1));
			model.getBoard().getRoads().add(new Road(player.getPlayerID(), location2));
			try {
				player.buildRoad();
				player.buildRoad();
				player.getPlayerBank().modifyDC(new DevelopmentHand(0,0,0,0,-1));
			} catch (Exception e) {
				throw new ServerException("Error with changing roads available when playing road building card:\n" + e.getMessage());
			}
			
			model.assignLongestRoad();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		return model;
	}

	
}
