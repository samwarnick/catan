package server.commands.move;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import server.ServerException;
import shared.communication.input.move.RollNumberInput;
import shared.locations.HexLocation;
import shared.model.bank.BankException;
import shared.model.bank.ResourceHand;
import shared.model.board.PlayerID;
import shared.model.board.ResourceHex;
import shared.model.player.Player;

public class RollNumberCommand extends MoveCommand{
	
	/**
	 * @pre none
	 * @post changes the resources the players have based on the number of the roll.  It adds one resource for each settlement touching the hexes that have
	 * the number, and 2 of each resource for the cities on the hexes.
	 * @return returns the game model after the modifications have been made
	 * @throws BankException 
	 */
	@Override
	public Object execute(String input) throws ServerException {
		Gson parser = new Gson();
		RollNumberInput in = parser.fromJson(input, RollNumberInput.class);
		int numberRolled = in.getNumber();
		
		if (numberRolled == 7) {
			for (Player player : model.getPlayers()) {
				if (player.getPlayerBank().getNumResourceCards() > 7) {
					model.getTurnTracker().setStatus("Discarding");
				}
			}
			if (!model.getTurnTracker().getStatus().equals("Discarding")) {
				model.getTurnTracker().setStatus("Robbing");				
			}
		}
		else {
			List<ResourceHex> hexes = model.getBoard().getResourceHexes();
			ArrayList<ResourceHex> rolledHexes = new ArrayList<ResourceHex>();
			HexLocation robber = model.getBoard().getRobber().getLocation();
			
			for (ResourceHex hex : hexes) {
				if (hex.getNumberToken() == numberRolled
						&& !hex.getLocation().equals(robber)) {
					rolledHexes.add(hex);
				}
			}
			assert(rolledHexes.size() <= 2);
			
			for (ResourceHex hex : rolledHexes) {
				List<PlayerID> ids = model.getBoard().getBoardFacade().getPlayersOnHex(hex.getLocation());
				for (PlayerID id : ids) {
					Player player = model.getPlayer(id);
					ResourceHand resourceGain = new ResourceHand();
					ResourceHand resourceLoss = new ResourceHand();
					switch(hex.getLandType()) {
					case BRICK:
						resourceGain.setBrick(1);
						resourceLoss.setBrick(-1);
						break;
					case ORE:
						resourceGain.setOre(1);
						resourceLoss.setOre(-1);
						break;
					case SHEEP:
						resourceGain.setSheep(1);
						resourceLoss.setSheep(-1);
						break;
					case WHEAT:
						resourceGain.setWheat(1);
						resourceLoss.setWheat(-1);
						break;
					case WOOD:
						resourceGain.setWood(1);
						resourceLoss.setWood(-1);
						break;
					default:
						assert(false);
						break;
					}
					
					if (model.getBank().hasRC(resourceGain)) {
						try {
							model.getBank().modifyRC(resourceLoss);
							player.getPlayerBank().modifyRC(resourceGain);
						} catch (Exception e) {
							throw new ServerException("Error distributing resources" + e.getMessage());
						} 
					}
				}
			}
			
			model.getTurnTracker().setStatus("Playing");
		}
		
		return model;
	}
}
