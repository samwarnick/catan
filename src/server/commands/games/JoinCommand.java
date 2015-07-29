package server.commands.games;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

import server.GameHub;
import server.commands.ICommand;
<<<<<<< Updated upstream
=======
import shared.communication.input.GamesJoinInput;
import shared.communication.input.Input;
import shared.definitions.CatanColor;
import shared.definitions.DevCardType;
import shared.model.GameModel;
import shared.model.player.Player;
>>>>>>> Stashed changes

public class JoinCommand implements ICommand {

	/**
	 * @pre a logged in player attempting to join a game he/she is not already a part of.
	 * @post player is added to game, a Boolean with the value of true is returned.
	 */
	
	@Override
<<<<<<< Updated upstream
	public Object execute(String input) {
		// TODO Auto-generated method stub
		return null;
=======
	public Object execute(Input input) {
		int GameID = -1;
		GameModel model = GameHub.getInstance().getModel(GameID);
		GamesJoinInput jgi = (GamesJoinInput)input;
		String name = null;
		Player newP = new Player();
		CatanColor cc = chooseColor(jgi.getColor());
		newP.setColor(cc);
		newP.setName(name);
		model.addPlayer(new Player());
		return true;
	}
	
	private CatanColor chooseColor(String c){
		c = c.toLowerCase();
		if(c == "blue"){
			return CatanColor.BLUE;
		}
		if(c == "red"){
			return CatanColor.RED;
		}
		if(c == "orange"){
			return CatanColor.ORANGE;
		}
		if(c == "green"){
			return CatanColor.GREEN;
		}
		if(c == "purple"){
			return CatanColor.PURPLE;
		}
		if(c == "puce"){
			return CatanColor.PUCE;
		}
		if(c == "brown"){
			return CatanColor.BROWN;
		}
		if(c == "white"){
			return CatanColor.WHITE;
		}
		if(c == "yellow"){
			return CatanColor.YELLOW;
		}

		
		return CatanColor.RED;
>>>>>>> Stashed changes
	}

}
