package server.dao.sql;

import java.util.List;

import client.data.GameInfo;
import server.commands.move.MoveCommand;
import server.dao.IGameDAO;
import shared.model.GameModel;

public class SQLGameDAO implements IGameDAO {
	
	private Database database;
	
	public SQLGameDAO(Database database) {
		this.database = database;
	}

	@Override
	public void addGameModel(GameModel model) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addGameInfo(GameInfo info) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addCommands() {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateGameModel(GameModel model) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateGameInfo(GameInfo info) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateCommands(int gameID, List<MoveCommand> commands) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<GameModel> getAllGameModels() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<GameInfo> getAllGameInfos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MoveCommand> getCommands(int gameID) {
		// TODO Auto-generated method stub
		return null;
	}

}
