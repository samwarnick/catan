package server.commands.games;

import client.data.PlayerInfo;

import com.google.gson.Gson;

import server.GameHub;
import server.commands.ICommand;
import shared.communication.input.GamesJoinInput;
import shared.definitions.CatanColor;
import shared.model.GameModel;
import shared.model.TooManyPlayersException;
import shared.model.player.Player;
import shared.model.user.User;

public class JoinCommand implements ICommand {

	private int playerID;
	
	public void setPlayerID(int playerID) {
		this.playerID = playerID;
	}

	/**
	 * @pre a logged in player attempting to join a game he/she is not already a part of.
	 * @post player is added to game, a Boolean with the value of true is returned.
	 */
	
	@Override
	public Object execute(String input) {
		Gson parser = new Gson();
		GamesJoinInput jgi = parser.fromJson(input, GamesJoinInput.class);
		GameModel model = GameHub.getInstance().getModel(jgi.getId());
		User user = GameHub.getInstance().getUser(playerID);
		String name = user.getUsername();
		if(model.getPlayer(name) != null){
			return jgi.getId();
		}
		Player newP = new Player();
		CatanColor cc = chooseColor(jgi.getColor());
		newP.setColor(cc);
		newP.setName(name);
		try {
			model.addPlayer(newP);
			GameHub.getInstance().getInfo(playerID).updatePlayers(model.getPlayers());
		} catch (TooManyPlayersException e) {
			e.printStackTrace();
		}
		System.out.println(model.getPlayers().toString());
		return jgi.getId();
	}
	
	private CatanColor chooseColor(String c){
		System.out.println("choosing color");
		c = c.toLowerCase();
		if(c == "blue"){
			return CatanColor.BLUE;
		}
		else if(c == "red"){
			return CatanColor.RED;
		}
		else if(c == "orange"){
			return CatanColor.ORANGE;
		}
		else if(c == "green"){
			return CatanColor.GREEN;
		}
		else if(c == "purple"){
			return CatanColor.PURPLE;
		}
		else if(c == "puce"){
			return CatanColor.PUCE;
		}
		else if(c == "brown"){
			return CatanColor.BROWN;
		}
		else if(c == "white"){
			return CatanColor.WHITE;
		}
		else if(c == "yellow"){
			return CatanColor.YELLOW;
		} else {
			return CatanColor.RED;
		}
	}

}
