package server.factories;

import server.dao.IGameDAO;
import server.dao.IUserDAO;
import server.dao.json.JsonGameDAO;
import server.dao.json.JsonUserDAO;

public class JsonFactory implements AbstractFactory{

	@Override
	public IGameDAO makeGameDAO(int commandLimit) {
		return new JsonGameDAO(commandLimit);
	}

	@Override
	public IUserDAO makeUserDAO() {
		return new JsonUserDAO();
	}

}
