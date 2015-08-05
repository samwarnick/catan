package server.factories;

import server.dao.IGameDAO;
import server.dao.IUserDAO;

public class SQLFactory implements AbstractFactory {

	@Override
	public IGameDAO makeGameDAO() {
		return null;
	}

	@Override
	public IUserDAO makeUserDAO() {
		return null;
	}

}
