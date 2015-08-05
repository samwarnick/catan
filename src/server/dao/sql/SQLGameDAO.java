package server.dao.sql;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import client.data.GameInfo;
import server.ServerException;
import server.commands.move.MoveCommand;
import server.dao.IGameDAO;
import shared.model.GameModel;
import shared.model.user.User;

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
	public void addCommand(int gameID, MoveCommand command) {
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
	public List<GameModel> getAllGameModels() {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<GameModel> models = new ArrayList<GameModel>();
		try {
			String query = "GameModel from GameModels";
			stmt = database.getConnection().prepareStatement(query);
			rs = stmt.executeQuery();
			while (rs.next()) {
				
				Blob blob = rs.getBlob(1);
				ByteArrayInputStream byteStream = new ByteArrayInputStream(blob.getBytes(0, (int) blob.length()));
				ObjectInputStream objectStream = new ObjectInputStream(byteStream);
				GameModel model = (GameModel) objectStream.readObject();
				models.add(model);
			}
		}
		catch (SQLException e) {
			DatabaseException serverEx = new DatabaseException(e.getMessage(), e);
			try {
				throw serverEx;
			} catch (DatabaseException e1) {
				e1.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}		
		finally {
			Database.safeClose(rs);
			Database.safeClose(stmt);
		}
		for (GameModel model : models){
			List<MoveCommand> commands = getCommands(model.getGameID());
			for (MoveCommand command : commands){
				command.setGameModel(model);
				try {
					command.execute(command.getInput());
				} catch (ServerException e) {
					e.printStackTrace();
				}
			}
		}
		return models;
	}

	@Override
	public List<GameInfo> getAllGameInfos() {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<GameInfo> infos = new ArrayList<GameInfo>();
		try {
			String query = "GameInfo from GameInfos";
			stmt = database.getConnection().prepareStatement(query);
			rs = stmt.executeQuery();
			while (rs.next()) {
				
				Blob blob = rs.getBlob(1);
				ByteArrayInputStream byteStream = new ByteArrayInputStream(blob.getBytes(0, (int) blob.length()));
				ObjectInputStream objectStream = new ObjectInputStream(byteStream);
				GameInfo info = (GameInfo) objectStream.readObject();
				infos.add(info);
			}
		}
		catch (SQLException e) {
			DatabaseException serverEx = new DatabaseException(e.getMessage(), e);
			try {
				throw serverEx;
			} catch (DatabaseException e1) {
				e1.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}		
		finally {
			Database.safeClose(rs);
			Database.safeClose(stmt);
		}
		return infos;
	}

	@Override
	public List<MoveCommand> getCommands(int gameID) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<MoveCommand> commands = new ArrayList<MoveCommand>();
		try {
			String query = "Command from Commands";
			stmt = database.getConnection().prepareStatement(query);
			rs = stmt.executeQuery();
			while (rs.next()) {
				
				Blob blob = rs.getBlob(1);
				ByteArrayInputStream byteStream = new ByteArrayInputStream(blob.getBytes(0, (int) blob.length()));
				ObjectInputStream objectStream = new ObjectInputStream(byteStream);
				MoveCommand command = (MoveCommand) objectStream.readObject();
				commands.add(command);
			}
		}
		catch (SQLException e) {
			DatabaseException serverEx = new DatabaseException(e.getMessage(), e);
			try {
				throw serverEx;
			} catch (DatabaseException e1) {
				e1.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}		
		finally {
			Database.safeClose(rs);
			Database.safeClose(stmt);
		}
		return commands;	
	}

	

}
