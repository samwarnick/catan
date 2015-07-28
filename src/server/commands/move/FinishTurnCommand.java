package server.commands.move;

import server.commands.ICommand;
import shared.communication.input.Input;
import shared.communication.input.move.FinishTurnInput;
import shared.model.GameModel;
import shared.model.board.PlayerID;
import shared.model.player.ActivePlayerFacade;
import shared.model.player.InactivePlayerFacade;

public class FinishTurnCommand implements ICommand {

	private GameModel model;
	
	
	/**
	 * @param input is a valid FinishTurnInput object
	 * @pre None
	 * @post The cards in the player's new development card hand are moved to the old development card hand. The player is now inactive and the next player becomes active. 
	 * @return The GameModel after executing the changes
	 */
	@Override
	public Object execute(Input input) {
		FinishTurnInput finishTurnInput = (FinishTurnInput) input;
		model.getPlayer(new PlayerID(finishTurnInput.getPlayerIndex())).setPlayerFacade(new InactivePlayerFacade(model.getPlayer(new PlayerID(finishTurnInput.getPlayerIndex()))));
		
		
		int nextActivePlayer = finishTurnInput.getPlayerIndex() + 1;
		if (nextActivePlayer == 4)
			nextActivePlayer = 0;
		model.getPlayer(new PlayerID(nextActivePlayer)).setPlayerFacade(new ActivePlayerFacade(model.getPlayer(new PlayerID(nextActivePlayer))));
		try {
			model.getPlayer(new PlayerID(finishTurnInput.getPlayerIndex())).getPlayerBank().transfer();
			model.getTurnTracker().setCurrentTurn(nextActivePlayer);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}

	public void setModel(GameModel model) {
		this.model = model;
	}
}
