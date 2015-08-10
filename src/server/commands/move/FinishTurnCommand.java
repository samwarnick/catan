package server.commands.move;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

import shared.communication.input.move.FinishTurnInput;
import shared.model.GameModel;
import shared.model.board.PlayerID;
import shared.model.player.ActivePlayerFacade;
import shared.model.player.InactivePlayerFacade;
import shared.model.player.Player;

public class FinishTurnCommand extends MoveCommand {

	public FinishTurnCommand() {
		super();
	}

	private GameModel model;
	
	
	/**
	 * @param input is a valid FinishTurnInput object
	 * @pre None
	 * @post The cards in the player's new development card hand are moved to the old development card hand. The player is now inactive and the next player becomes active. 
	 * @return The GameModel after executing the changes
	 */
	@Override
	public Object execute(String input) {
		FinishTurnInput finishTurnInput;
		try {

			finishTurnInput = new ObjectMapper().readValue(input, FinishTurnInput.class);

			PlayerID player = new PlayerID(finishTurnInput.getPlayerIndex());
			Player players = model.getPlayer(player);
			if (players.getVictoryPoints().getTotalVictoryPoints() >= 10){
				model.setWinner(players.getUniqueID());
			}
			players.setPlayerFacade(new InactivePlayerFacade(players));
			int nextActivePlayer;
			if (model.getTurnTracker().getStatus().equals("SecondRound")){
				nextActivePlayer = finishTurnInput.getPlayerIndex() - 1;
				if (nextActivePlayer == -1){
					nextActivePlayer = 0;
					model.getTurnTracker().setStatus("Rolling");
				}
			}
			else if (model.getTurnTracker().getStatus().equals("FirstRound")){
				nextActivePlayer = finishTurnInput.getPlayerIndex() + 1;
				if (nextActivePlayer == 4){
						nextActivePlayer = 3;
						model.getTurnTracker().setStatus("SecondRound");
				}
			}
			else{
				nextActivePlayer = finishTurnInput.getPlayerIndex() + 1;
				if (nextActivePlayer == 4){
					nextActivePlayer = 0;
			}
				model.getTurnTracker().setStatus("Rolling");
			}
			

			model.getPlayer(new PlayerID(nextActivePlayer)).setPlayerFacade(new ActivePlayerFacade(model.getPlayer(new PlayerID(nextActivePlayer))));
			try {
				model.getPlayer(new PlayerID(finishTurnInput.getPlayerIndex())).getPlayerBank().transfer();
				model.getTurnTracker().setCurrentTurn(nextActivePlayer);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		
		return model;
	}
	
	public void setGameModel(GameModel model) {
		this.model = model;
	}
}