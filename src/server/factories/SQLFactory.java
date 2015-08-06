package server.factories;

import server.dao.IGameDAO;
import server.dao.IUserDAO;
import server.dao.sql.Database;
import server.dao.sql.DatabaseException;

public class SQLFactory implements AbstractFactory {

	@Override
	public IGameDAO makeGameDAO() {
		Database database = new Database();
		try {
			Database.initialize();
		} catch (DatabaseException e) {
			e.printStackTrace();
		}
		return (IGameDAO) database.getGameDAO();
	}

	@Override
	public IUserDAO makeUserDAO() {
		Database database = new Database();
		try {
			Database.initialize();
		} catch (DatabaseException e) {
			e.printStackTrace();
		}
		return (IUserDAO) database.getUserDAO();
	}

}
