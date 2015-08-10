package server.commands.move;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

import shared.communication.input.move.PlayMonumentInput;
import shared.model.GameModel;
import shared.model.bank.BankException;
import shared.model.bank.DevelopmentHand;
import shared.model.board.PlayerID;
import shared.model.player.Player;
import shared.model.player.VictoryPoints;

public class PlayMonumentCommand extends MoveCommand {
	
	//private GameModel model;
	
	
	public PlayMonumentCommand() {
		super();
	}

	/**
	 * @param input is a valid PlayMonumentInput object
	 * @pre The player must have enough monument cards for the player to have 10 victory points
	 * @post The player gained a victory point. The method returns a not null GameModel
	 * @return The GameModel after executing the changes
	 */
	@Override
	public Object execute(String input) {
		PlayMonumentInput playMonumentInput;
		try {
			playMonumentInput = new ObjectMapper().readValue(input, PlayMonumentInput.class);
			Player p = model.getPlayer(new PlayerID(playMonumentInput.getPlayerIndex()));
			//add victory point
			VictoryPoints victoryPoints= p.getVictoryPoints();
			victoryPoints.addPublicVictoryPoint();
			p.setVictoryPoints(victoryPoints);
			//subtract victory card
			try {
				p.getPlayerBank().modifyDC(new DevelopmentHand(0,-1,0,0,0));
			} catch (BankException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return model;
	}

	public void setModel(GameModel model) {
		this.model = model;
	}
	
}

