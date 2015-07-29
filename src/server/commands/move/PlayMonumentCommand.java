package server.commands.move;

import com.google.gson.Gson;

import server.commands.ICommand;
import shared.communication.input.Input;
import shared.communication.input.move.PlayMonopolyInput;
import shared.communication.input.move.PlayMonumentInput;
import shared.definitions.DevCardType;
import shared.model.GameModel;
import shared.model.bank.BankException;
import shared.model.board.PlayerID;
import shared.model.player.Player;
import shared.model.player.VictoryPoints;

public class PlayMonumentCommand implements ICommand {
	
	private GameModel model;
	
	
	/**
	 * @param input is a valid PlayMonumentInput object
	 * @pre The player must have enough monument cards for the player to have 10 victory points
	 * @post The player gained a victory point. The method returns a not null GameModel
	 * @return The GameModel after executing the changes
	 */
	@Override
	public Object execute(String input) {
		Gson parser = new Gson();
		PlayMonumentInput playMonumentInput = parser.fromJson(input, PlayMonumentInput.class);
		Player p = model.getPlayer(new PlayerID(playMonumentInput.getPlayerIndex()));
		//add victory point
		VictoryPoints victoryPoints= p.getVictoryPoints();
		victoryPoints.addPublicVictoryPoint();
		p.setVictoryPoints(victoryPoints);
		//subtract victory card
		try {
			p.getPlayerBank().getDevStack(DevCardType.MONUMENT).setQuantity(p.getPlayerBank().getDevStack(DevCardType.MONUMENT).getQuantity() - 1);
		} catch (BankException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return model;
	}

	public void setModel(GameModel model) {
		this.model = model;
	}
	
}

