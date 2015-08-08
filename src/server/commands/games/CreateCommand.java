package server.commands.games;

import java.io.Serializable;

import com.google.gson.Gson;

import client.data.GameInfo;
import server.GameHub;
import server.commands.ICommand;
import shared.communication.input.GamesCreateInput;
import shared.model.GameModel;
import shared.model.board.Board;


@SuppressWarnings("serial")
public class CreateCommand implements ICommand,Serializable {
	
	/**
	 * @pre player is logged in
	 * @post an object containing the title, id, and list of players (empty) of the new game.
	 * 
	 */

	@Override
	public Object execute(String input) {
		Gson parser = new Gson();
		GamesCreateInput gci = parser.fromJson(input, GamesCreateInput.class);
		GameModel model = new GameModel(GameHub.getInstance().getModelsSize());
		Board b = new Board(gci.isRandomTiles(), gci.isRandomPorts(), gci.isRandomNumbers());
		model.setBoard(b);
		GameInfo gi = new GameInfo();
		gi.setTitle(gci.getName());
		gi.setId(GameHub.getInstance().getModelsSize());
		gi.updatePlayers(model.getPlayers());
		GameHub.getInstance().addModel(model);
		GameHub.getInstance().addInfo(gi);
		
		// add to persistence
		GameHub.getInstance().getGameDAO().addGameModel(model);
		GameHub.getInstance().getGameDAO().addGameInfo(gi);
		
		return gi;
	}

}
