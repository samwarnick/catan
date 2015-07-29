package server.commands.games;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

import com.google.gson.Gson;

import server.GameHub;
import server.commands.ICommand;
import shared.communication.input.GamesJoinInput;
import shared.communication.input.Input;
import shared.definitions.CatanColor;
import shared.definitions.DevCardType;
import shared.model.GameModel;
import shared.model.TooManyPlayersException;
import shared.model.player.Player;

public class JoinCommand implements ICommand {

	/**
	 * @pre a logged in player attempting to join a game he/she is not already a part of.
	 * @post player is added to game, a Boolean with the value of true is returned.
	 */
	
	@Override
	public Object execute(String input) {
		Gson parser = new Gson();
		GamesJoinInput jgi = parser.fromJson(input, GamesJoinInput.class);
		GameModel model = GameHub.getInstance().getModel(jgi.getId());
		String name = null;
		Player newP = new Player();
		CatanColor cc = chooseColor(jgi.getColor());
		newP.setColor(cc);
		newP.setName(name);
		try {
			model.addPlayer(newP);
		} catch (TooManyPlayersException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	}

}
