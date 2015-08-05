package server.dao.sql;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import client.data.GameInfo;
import server.commands.move.MoveCommand;
import server.dao.IGameDAO;
import shared.model.GameModel;

public class SQLGameDAO implements IGameDAO {
	
	private Database database;
	
	public SQLGameDAO(Database database) {
	}

	@Override
	public void addGameModel(GameModel model) {
		String query = "insert into GameModels (GameModel) values ( ?)";
		PreparedStatement stmt = null;
		ResultSet keyRS = null;		
		try {
			stmt = database.getConnection().prepareStatement(query);
			Blob blob = database.getConnection().createBlob();
			ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
			ObjectOutputStream objectStream = new ObjectOutputStream(byteStream);
			objectStream.writeObject(model);
			blob.setBytes(0, byteStream.toByteArray());
			stmt.setBlob(1, blob);
			if (stmt.executeUpdate() != 1) {
				throw new DatabaseException("Could not insert user");
			}
		}
		catch (SQLException e) {
			try {
				throw new DatabaseException("Could not insert user", e);
			} catch (DatabaseException e1) {
				e1.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (DatabaseException e) {
			e.printStackTrace();
		}
		finally {
			Database.safeClose(stmt);
			Database.safeClose(keyRS);
		}
	}

	@Override
	public void addGameInfo(GameInfo info) {
		addCommands();
		String query = "insert into GameInfos (GameInfo) values ( ?)";
		PreparedStatement stmt = null;
		ResultSet keyRS = null;		
		try {
			stmt = database.getConnection().prepareStatement(query);
			Blob blob = database.getConnection().createBlob();
			ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
			ObjectOutputStream objectStream = new ObjectOutputStream(byteStream);
			objectStream.writeObject(info);
			blob.setBytes(0, byteStream.toByteArray());
			stmt.setBlob(1, blob);
			if (stmt.executeUpdate() != 1) {
				throw new DatabaseException("Could not insert user");
			}
		}
		catch (SQLException e) {
			try {
				throw new DatabaseException("Could not insert user", e);
			} catch (DatabaseException e1) {
				e1.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (DatabaseException e) {
			e.printStackTrace();
		}
		finally {
			Database.safeClose(stmt);
			Database.safeClose(keyRS);
		}

	}

	private void addCommands() {
		String query = "insert into Commands (Command) values ( ?)";
		PreparedStatement stmt = null;
		ResultSet keyRS = null;		
		try {
			stmt = database.getConnection().prepareStatement(query);
			Blob blob = database.getConnection().createBlob();
			ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
			ObjectOutputStream objectStream = new ObjectOutputStream(byteStream);
			objectStream.writeObject(new ArrayList<MoveCommand>());
			blob.setBytes(0, byteStream.toByteArray());
			stmt.setBlob(1, blob);
			if (stmt.executeUpdate() != 1) {
				throw new DatabaseException("Could not insert user");
			}
		}
		catch (SQLException e) {
			try {
				throw new DatabaseException("Could not insert user", e);
			} catch (DatabaseException e1) {
				e1.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (DatabaseException e) {
			e.printStackTrace();
		}
		finally {
			Database.safeClose(stmt);
			Database.safeClose(keyRS);
		}

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
