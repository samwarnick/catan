package server.dao.sql;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
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
		try {
			database.startTransaction();
			stmt = database.getConnection().prepareStatement(query);
			ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
			ObjectOutputStream objectStream = new ObjectOutputStream(byteStream);
			objectStream.writeObject(user);
			byte[] array = byteStream.toByteArray();
			stmt.setBytes(1, array);
			
			if (stmt.executeUpdate() != 1) {
				throw new DatabaseException("Could not insert user");
			}
			database.endTransaction(true);
		}
		catch (SQLFeatureNotSupportedException e){
			e.printStackTrace();
		}
		catch (SQLException e) {
			try {
				throw new DatabaseException("Could not insert user", e);
			} catch (DatabaseException e1) {
				database.endTransaction(false);
				e1.printStackTrace();
			}
		} 
		catch (IOException e) {
			e.printStackTrace();
		} catch (DatabaseException e) {
			e.printStackTrace();
		}
		finally {
		}
		
	}

	@Override
	public List<User> getUsers() {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<User> users = new ArrayList<User>();
		try {
			database.startTransaction();
			String query = "select User from Users ";
			stmt = database.getConnection().prepareStatement(query);
			rs = stmt.executeQuery();
			while (rs.next()) {
				
				ByteArrayInputStream byteStream = new ByteArrayInputStream(rs.getBytes(1));
				ObjectInputStream objectStream = new ObjectInputStream(byteStream);
				User user = (User) objectStream.readObject();
				users.add(user);
			}
			database.endTransaction(true);
		}
		catch (SQLException e) {
			DatabaseException serverEx = new DatabaseException(e.getMessage(), e);
			try {
				throw serverEx;
			} catch (DatabaseException e1) {
				database.endTransaction(false);
				e1.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (DatabaseException e) {
			e.printStackTrace();
		}		
		finally {
		}
		return users;	}

}
