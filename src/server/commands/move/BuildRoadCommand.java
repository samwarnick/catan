package server.commands.move;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

import server.ServerException;
import shared.communication.input.move.BuildRoadInput;
import shared.locations.EdgeLocation;
import shared.model.bank.BankException;
import shared.model.bank.ResourceHand;
import shared.model.board.PlayerID;
import shared.model.board.Road;
import shared.model.player.Player;

public class BuildRoadCommand extends MoveCommand{
	
	public BuildRoadCommand() {
		super();
	}

	/**
	 * @pre the location is a valid location for the player to build a road, the player has enough resources to build a road if it isn't free.
	 * @post the road will be placed at the location and the resources will be removed from the player if the road wasn't free.
	 * @return the updated GameModel
	 */
	@Override
	public Object execute(String input) throws ServerException {
		BuildRoadInput in;
		try {
			in = new ObjectMapper().readValue(input, BuildRoadInput.class);
			EdgeLocation location = in.getRoadLocation();
			boolean isFree = in.getFree();
			int playerIndex = in.getPlayerIndex();
			
			Player player = model.getPlayer(new PlayerID(playerIndex));
			if (!isFree) {
				try {
					model.getBank().modifyRC(new ResourceHand(1,1,0,0,0));
					player.getPlayerBank().modifyRC(new ResourceHand(-1,-1,0,0,0));
				} catch (BankException e) {
					throw new ServerException("Error with player resources when building road:\n" + e.getMessage());
				} 
			}
			
			model.getBoard().getRoads().add(new Road(player.getPlayerID(), location));
			try {
				player.buildRoad();
			} catch (Exception e) {
				throw new ServerException("Error with changing roads available when building a road:\n" + e.getMessage());
			}
			
			model.assignLongestRoad();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return model;
	}

}