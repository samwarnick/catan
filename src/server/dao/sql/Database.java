package server.dao.sql;

import java.io.*;
import java.sql.*;
import java.util.logging.*;

public class Database {

	private static final String DATABASE_DIRECTORY = "lib/Persistance/DataBase";
	private static final String DATABASE_FILE = "SettlersOfCatanRBase.sqlite";
	private static final String DATABASE_URL = "jdbc:sqlite:" + DATABASE_DIRECTORY +
												File.separator + DATABASE_FILE;
	private static Logger logger;
	private static int commandLimit = 10;
	
	static {
		logger = Logger.getLogger("recordindexer");
	}

	public static void initialize() throws DatabaseException {
		try {
			final String driver = "org.sqlite.JDBC";
			Class.forName(driver);
		}
		catch(ClassNotFoundException e) {
			
			DatabaseException serverEx = new DatabaseException("Could not load database driver", e);
			
			logger.throwing("server.database.Database", "initialize", serverEx);

			throw serverEx; 
		}
	}

	private SQLUserDAO userDAO;
	private SQLGameDAO gameDAO;
	private Connection connection;
	
	public Database(int commandLimit) {
		userDAO = new SQLUserDAO(this);
		gameDAO = new SQLGameDAO(this);
		this.commandLimit = commandLimit;
		connection = null;
	}
	
	public Database() {
		userDAO = new SQLUserDAO(this);
		gameDAO = new SQLGameDAO(this);
		connection = null;
	}
	
	public Connection getConnection() {
		return connection;
	}

	public void startTransaction() throws DatabaseException {
		try {
			assert (connection == null);			
			connection = DriverManager.getConnection(DATABASE_URL);
			connection.setAutoCommit(false);
		}
		catch (SQLException e) {
			throw new DatabaseException("Could not connect to database. Make sure " + 
				DATABASE_FILE + " is available in ./" + DATABASE_DIRECTORY, e);
		}
	}
	
	public void endTransaction(boolean commit) {
		if (connection != null) {		
			try {
				if (commit) {
					connection.commit();
				}
				else {
					connection.rollback();
				}
			}
			catch (SQLException e) {
				System.out.println("Could not end transaction");
				e.printStackTrace();
			}
			finally {
				safeClose(connection);
				connection = null;
			}
		}
	}
	
	public static void safeClose(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void safeClose(Statement stmt) {
		if (stmt != null) {
			try {
				stmt.close();
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void safeClose(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public SQLUserDAO getUserDAO() {
		return userDAO;
	}

	public SQLGameDAO getGameDAO() {
		return gameDAO;
	}

	public int getCommandLimit() {
		return commandLimit;
	}


	

	
}
