package server.factories;

import server.dao.IGameDAO;
import server.dao.IUserDAO;

public interface AbstractFactory {
	
	
	/**
	 * @pre none
	 * @post returns a new GameDAO with the correct persistence implementation. 
	 */
	public IGameDAO makeGameDAO(int commandLimit);
	
	/**
	 * @pre none
	 * @post returns a new UserDAO with the correct persistence implementation.
	 */
	
	public IUserDAO makeUserDAO();

}
