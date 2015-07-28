package server.commands.move;

import server.ServerException;
import server.commands.ICommand;
import shared.communication.input.Input;
import shared.communication.input.move.BuildRoadInput;
import shared.communication.input.move.BuildSettlementInput;
import shared.locations.VertexLocation;
import shared.model.GameModel;
import shared.model.bank.BankException;
import shared.model.bank.ResourceHand;
import shared.model.board.PlayerID;
import shared.model.board.Settlement;
import shared.model.player.Player;

public class BuildSettlementCommand implements ICommand{
	
	private GameModel model;

	
	/**
	 * @pre the location is a valid location for the player to build a settlement, the player has enough resources to build a settlement if it isn't free.
	 * @post the settlement will be placed at the location and the resources will be removed from the player if the settlement wasn't free.
	 * @return the updated GameModel
	 * @throws ServerException 
	 */
	@Override
	public Object execute(Input input) throws ServerException {
		VertexLocation location = ((BuildSettlementInput)input).getVertexLocation();
		boolean isFree = ((BuildSettlementInput)input).isFree();
		int playerIndex = ((BuildSettlementInput) input).getPlayerIndex();
		
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
		
		return model;
	}
	
	public void setModel(GameModel model){
		this.model = model;
	}

}
