package server.commands.move;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import server.ServerException;
import shared.communication.input.move.BuildCityInput;
import shared.locations.VertexLocation;
import shared.model.bank.BankException;
import shared.model.bank.ResourceHand;
import shared.model.board.City;
import shared.model.board.PlayerID;
import shared.model.board.Vertex;
import shared.model.player.Player;

public class BuildCityCommand extends MoveCommand{
		
	public BuildCityCommand() {
		super();
	}

	/**
	 * @pre the location is a valid location for the player to build a city, the player has enough resources to build a city.
	 * @post the city will be placed at the location and the resources will be removed from the player
	 * @return the updated GameModel
	 * @throws ServerException 
	 */
	@Override
	public Object execute(String input) throws ServerException {
		BuildCityInput in;
		try {
			in = new ObjectMapper().readValue(input, BuildCityInput.class);
			VertexLocation location = in.getVertexLocation();
			int playerIndex = in.getPlayerIndex();
			
			List<Vertex> buildings = model.getBoard().getBuildings();
			Vertex settlement = null;
			for (int i = 0; i < buildings.size(); i++) {
				if (buildings.get(i).getLocation().equals(location)) {
					settlement = buildings.get(i);
					break;
				}
			}
			if (settlement == null) {
				throw new ServerException("The given location is not a settlement.");
			}
			buildings.remove(settlement);
			City city = new City(settlement.getOwner(), settlement.getLocation());
			buildings.add(city);
			
			Player player = model.getPlayer(city.getOwner());
			if (!player.equals(model.getPlayer(new PlayerID(playerIndex)))) {
				throw new ServerException("The player does not match the owner of the settlement at the given location.");
			}
			else {
				try {
					model.getBank().modifyRC(new ResourceHand(0,0,0,2,3));
					player.getPlayerBank().modifyRC(new ResourceHand(0,0,0,-2,-3));
				} catch (BankException e) {
					throw new ServerException("Error with player resources when building city:\n" + e.getMessage());
				}
				
				try {
					player.getSettlements().subtractSettlement();
					player.getCities().buildCity();
					player.getVictoryPoints().addPublicVictoryPoint();
				} catch (Exception e) {
					throw new ServerException("Error changing player values when building city:\n" + e.getMessage());
				}			
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		return model;
	}
}
