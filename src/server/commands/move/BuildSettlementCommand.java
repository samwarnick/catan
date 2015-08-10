package server.commands.move;

import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.ObjectMapper;

import server.ServerException;
import shared.communication.input.move.BuildSettlementInput;
import shared.locations.VertexLocation;
import shared.model.bank.BankException;
import shared.model.bank.ResourceHand;
import shared.model.board.PlayerID;
import shared.model.board.ResourceHex;
import shared.model.board.Settlement;
import shared.model.player.Player;

public class BuildSettlementCommand extends MoveCommand{
	
	public BuildSettlementCommand() {
		super();
	}

	/**
	 * @pre the location is a valid location for the player to build a settlement, the player has enough resources to build a settlement if it isn't free.
	 * @post the settlement will be placed at the location and the resources will be removed from the player if the settlement wasn't free.
	 * @return the updated GameModel
	 * @throws ServerException 
	 */
	@Override
	public Object execute(String input) throws ServerException {
		BuildSettlementInput in;
		try {
			in = new ObjectMapper().readValue(input, BuildSettlementInput.class);
			VertexLocation location = in.getVertexLocation();
			boolean isFree = in.getFree();
			int playerIndex = in.getPlayerIndex();
			
			Player player = model.getPlayer(new PlayerID(playerIndex));
			if (!isFree) {
				try {
					model.getBank().modifyRC(new ResourceHand(1,1,1,1,0));
					player.getPlayerBank().modifyRC(new ResourceHand(-1,-1,-1,-1,0));
				} catch (BankException e) {
					throw new ServerException("Error with player resources when building settlement:\n" + e.getMessage());
				} 
			}
			
			model.getBoard().getBuildings().add(new Settlement(player.getPlayerID(), location));
			try {
				player.getSettlements().buildSettlement();
				player.getVictoryPoints().addPublicVictoryPoint();
				if (model.getTurnTracker().getStatus().equals("SecondRound")) {
					ArrayList<VertexLocation> vertices = new ArrayList<VertexLocation>();
					vertices.add(location);
					vertices.addAll(location.getAmbiguousVertices());
					for (VertexLocation vertex : vertices) {
						if (model.getBoard().getResourceHexesMap().containsKey(vertex.getHexLoc())) {
							ResourceHex hex = model.getBoard().getResourceHexesMap().get(vertex.getHexLoc());
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
								model.getBank().modifyRC(resourceLoss);
								player.getPlayerBank().modifyRC(resourceGain); 
							}
						}
					}
				}
			} catch (ServerException e) {
				throw new ServerException("Error getting second round resources" + e.getMessage());
			} catch (Exception e) {
				throw new ServerException("Error with changing settlements available when building a settlement:\n" + e.getMessage());
			}
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return model;
	}
}
