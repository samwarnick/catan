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
		addCommands();
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
				throw new DatabaseException("Could not add Game Model");
			}
		}
		catch (SQLException e) {
			try {
				throw new DatabaseException("Could not add Game Model", e);
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
				throw new DatabaseException("Could not add Game info");
			}
		}
		catch (SQLException e) {
			try {
				throw new DatabaseException("Could not add Game info", e);
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
				throw new DatabaseException("Could not add COmmand");
			}
		}
		catch (SQLException e) {
			try {
				throw new DatabaseException("Could not add COmmand", e);
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
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<MoveCommand> dbcommands = new ArrayList<MoveCommand>();
		try {
			String query = "select Command from Commands where GameId = ?";
			stmt = database.getConnection().prepareStatement(query);
			stmt.setInt(1, gameID);
			rs = stmt.executeQuery();
			Blob blob = rs.getBlob(1);
			ByteArrayInputStream byteStream = new ByteArrayInputStream(blob.getBytes(0, (int) blob.length()));
			ObjectInputStream objectStream = new ObjectInputStream(byteStream);
			dbcommands = (ArrayList<MoveCommand>) objectStream.readObject();
			if(dbcommands.size()==database.getCommandLimit()) dbcommands.clear();
			dbcommands.add(command);
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
			updateCommands(gameID, dbcommands);
		}
		
	}
	
	private void updateCommands(int gameID, List<MoveCommand> commands){
		String query = "update Commands set Command = ? where GameId = ?)";
		PreparedStatement stmt = null;
		ResultSet keyRS = null;		
		try {
			stmt = database.getConnection().prepareStatement(query);
			Blob blob = database.getConnection().createBlob();
			ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
			ObjectOutputStream objectStream = new ObjectOutputStream(byteStream);
			objectStream.writeObject(commands);
			blob.setBytes(0, byteStream.toByteArray());
			stmt.setBlob(1, blob);
			stmt.setInt(2, gameID);
			if (stmt.executeUpdate() != 1) {
				throw new DatabaseException("Could not update Commands");
			}
		}
		catch (SQLException e) {
			try {
				throw new DatabaseException("Could not update Commands", e);
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
		String query = "update GameModels set Command = ? where id = ?)";
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
			stmt.setInt(2, model.getGameID());
			if (stmt.executeUpdate() != 1) {
				throw new DatabaseException("Could not update Game Model");
			}
		}
		catch (SQLException e) {
			try {
				throw new DatabaseException("Could not update Game Model", e);
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
	public void updateGameInfo(GameInfo info) {
		String query = "update GameInfos set Command = ? where id = ?)";
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
			stmt.setInt(2, info.getId());
			if (stmt.executeUpdate() != 1) {
				throw new DatabaseException("Could not update Game info");
			}
		}
		catch (SQLException e) {
			try {
				throw new DatabaseException("Could not update Game info", e);
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

	
	//has errors, doesn't use parameter and each blob is a list of commands, not a single command.
	
	@SuppressWarnings("unchecked")
	@Override
	public List<MoveCommand> getCommands(int gameID) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<MoveCommand> commands = new ArrayList<MoveCommand>();
		try {
			String query = "Command from Commands where GameId = ?";
			stmt = database.getConnection().prepareStatement(query);
			stmt.setInt(1, gameID);
			rs = stmt.executeQuery();
			while (rs.next()) {
				
				Blob blob = rs.getBlob(1);
				ByteArrayInputStream byteStream = new ByteArrayInputStream(blob.getBytes(0, (int) blob.length()));
				ObjectInputStream objectStream = new ObjectInputStream(byteStream);
				commands = (ArrayList<MoveCommand>) objectStream.readObject();
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
