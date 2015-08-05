package server.dao;

import java.util.List;

import shared.model.user.User;

public interface IUserDAO {
	
	/**
	 * @pre the user is a valid user object
	 * @post the user is added into the correct persistence storage
	 * @param user you want to add to the persistence storage
	 */
	
	public void addUser(User user);
	
	/**
	 * @pre none
	 * @post returns the list of all users that are stored in the persistence storage.
	 */
	
	public List<User> getUsers();

}
