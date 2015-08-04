package server.commands.games;

import client.data.PlayerInfo;

import com.google.gson.Gson;

import server.GameHub;
import server.ServerException;
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
	 * @throws ServerException 
	 * @pre a logged in player attempting to join a game he/she is not already a part of.
	 * @post player is added to game, a Boolean with the value of true is returned.
	 */
	
	@Override
	public Object execute(String input) throws ServerException {
		Gson parser = new Gson();
		GamesJoinInput jgi = parser.fromJson(input, GamesJoinInput.class);
		GameModel model = GameHub.getInstance().getModel(jgi.getId());
		User user = GameHub.getInstance().getUser(playerID);
		String name = user.getUsername();
		CatanColor cc = chooseColor(jgi.getColor());
		if(!GameHub.getInstance().getInfo(jgi.getId()).hasColor(cc).equals(null)&&
				!GameHub.getInstance().getInfo(jgi.getId()).hasColor(cc).equals(name)) throw new ServerException();
		if(model.getPlayer(name) != null){
			model.getPlayer(name).setColor(cc);
			int index = model.getPlayer(name).getPlayerID().getPlayerid();
			GameHub.getInstance().getInfo(jgi.getId()).getPlayers().get(index).setColor(cc);
			return jgi.getId();
		}
		Player newP = new Player(cc, name, -1);
		newP.setUniqueID(playerID);
		try {
			model.addPlayer(newP);
			GameHub.getInstance().getInfo(jgi.getId()).updatePlayers(model.getPlayers());
		} catch (TooManyPlayersException e) {
			e.printStackTrace();
		}
		return jgi.getId();
	}
	
	private CatanColor chooseColor(String c){
		c = c.trim().toLowerCase();
		c = c.toLowerCase();
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
