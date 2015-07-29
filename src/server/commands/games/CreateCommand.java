package server.commands.games;

import client.data.GameInfo;
import server.GameHub;
import server.commands.ICommand;
import shared.communication.input.GamesCreateInput;
import shared.communication.input.Input;
import shared.model.GameModel;
import shared.model.board.Board;


public class CreateCommand implements ICommand {
	
	/**
	 * @pre player is logged in
	 * @post an object containing the title, id, and list of players (empty) of the new game.
	 * 
	 */

	@Override
	public Object execute(String input) {
		int GameID = 0;
		GamesCreateInput gci = new GamesCreateInput(input, false, false, false);
		GameModel model = new GameModel(GameID);
		Board b = new Board(gci.isRandomTiles(), gci.isRandomPorts(), gci.isRandomNumbers());
		model.setBoard(b);
		GameInfo gi = new GameInfo();
		gi.setTitle(gci.getName());
		gi.setId(GameID);
		GameHub.getInstance().addModel(model);
		GameHub.getInstance().addInfo(gi);
		return gi;
	}

}
