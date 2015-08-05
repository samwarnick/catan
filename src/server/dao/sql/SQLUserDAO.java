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

import server.dao.IUserDAO;
import shared.model.user.User;

public class SQLUserDAO implements IUserDAO{

	
	private Database database;
	
	public SQLUserDAO(Database database) {
		this.database = database;
	}
	
	@Override
	public void addUser(User user) {
		String query = "insert into Users (User) values ( ?)";
		PreparedStatement stmt = null;
		ResultSet keyRS = null;		
		try {
			stmt = database.getConnection().prepareStatement(query);
			Blob blob = database.getConnection().createBlob();
			ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
			ObjectOutputStream objectStream = new ObjectOutputStream(byteStream);
			objectStream.writeObject(user);
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
	public List<User> getUsers() {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<User> users = new ArrayList<User>();
		try {
			String query = "select User from Users ";
			stmt = database.getConnection().prepareStatement(query);
			rs = stmt.executeQuery();
			while (rs.next()) {
				
				Blob blob = rs.getBlob(1);
				ByteArrayInputStream byteStream = new ByteArrayInputStream(blob.getBytes(0, (int) blob.length()));
				ObjectInputStream objectStream = new ObjectInputStream(byteStream);
				User user = (User) objectStream.readObject();
				users.add(user);
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
		return users;	}

}
