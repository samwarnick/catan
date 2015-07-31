package server.commands.move;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import server.ServerException;
import shared.communication.input.move.BuildSettlementInput;
import shared.communication.input.move.SendChatInput;
import shared.locations.VertexLocation;
import shared.model.bank.BankException;
import shared.model.bank.ResourceHand;
import shared.model.board.PlayerID;
import shared.model.board.Settlement;
import shared.model.player.Player;

public class BuildSettlementCommand extends MoveCommand{
	
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
				ResourceHand rh = new ResourceHand(-1, -1, -1, -1, 0);
				try {
					player.getPlayerBank().modifyRC(rh);
				} catch (BankException e) {
					throw new ServerException("Error with player resources when building settlement:\n" + e.getMessage());
				} 
			}
			
			model.getBoard().getBuildings().add(new Settlement(player.getPlayerID(), location));
			try {
				player.getSettlements().buildSettlement();
				player.getVictoryPoints().addPublicVictoryPoint();
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
