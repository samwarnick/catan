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
		System.out.println("executing join command");
		Gson parser = new Gson();
		GamesJoinInput jgi = parser.fromJson(input, GamesJoinInput.class);
		GameModel model = GameHub.getInstance().getModel(jgi.getId());
		User user = GameHub.getInstance().getUser(playerID);
		String name = user.getUsername();
		System.out.println(input);
		CatanColor cc = chooseColor(jgi.getColor());
		if(model.getPlayer(name) != null){
			System.out.println("Already joined");
			model.getPlayer(name).setColor(cc);
			int index = model.getPlayer(name).getPlayerID().getPlayerid();
			GameHub.getInstance().getInfo(jgi.getId()).getPlayers().get(index).setColor(cc);;
			return jgi.getId();
		}
		System.out.println("creating new player");
		Player newP = new Player(cc, name, -1);
		try {
			model.addPlayer(newP);
			GameHub.getInstance().getInfo(jgi.getId()).updatePlayers(model.getPlayers());
		} catch (TooManyPlayersException e) {
			e.printStackTrace();
		}
		System.out.println("added");
		return jgi.getId();
	}
	
	private CatanColor chooseColor(String c){
		System.out.println("choosing color");
		c = c.trim().toLowerCase();
		if(c.equals("blue")){
			return CatanColor.BLUE;
		}
		else if(c.equals("red")){
			return CatanColor.RED;
		}
		else if(c.equals("orange")){
			return CatanColor.ORANGE;
		}
		else if(c.equals("green")){
			return CatanColor.GREEN;
		}
		else if(c.equals("purple")){
			return CatanColor.PURPLE;
		}
		else if(c.equals("puce")){
			return CatanColor.PUCE;
		}
		else if(c.equals("brown")){
			return CatanColor.BROWN;
		}
		else if(c.equals("white")){
			return CatanColor.WHITE;
		}
		else if(c.equals("yellow")){
			return CatanColor.YELLOW;
		} 
		else {
			return CatanColor.PUCE;
		}
	}

}
