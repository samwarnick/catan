package server.factories;

import server.dao.IGameDAO;
import server.dao.IUserDAO;

public class JsonFactory implements AbstractFactory{

	@Override
	public IGameDAO makeGameDAO(int commandLimit) {
		return null;
	}

	@Override
	public IUserDAO makeUserDAO() {
		return null;
	}

}
