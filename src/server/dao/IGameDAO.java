package server.dao;

import java.util.List;

import client.data.GameInfo;
import server.commands.move.MoveCommand;
import shared.model.GameModel;

public interface IGameDAO {
	
	/**
	 * This adds the given model to the persistence storage and creates a list of commands in the storage.
	 * @param model
	 * @pre model must be a valid GameModel object
	 * @post model has been added to the persistence storage
	 */
	public void addGameModel(GameModel model);
	
	/**
	 * This adds the given GameInfo to the persistence storage
	 * @param info
	 * @pre info must be a valid GameInfo object
	 * @post info has been added to the persistence storage
	 */
	public void addGameInfo(GameInfo info);
	
	/**
	 * This updates the given model on the persistence storage
	 * @param model
	 * @pre model must be a valid GameModel object
	 * @post model has been updated on the persistence storage
	 */
	public void updateGameModel(GameModel model);
	
	/**
	 * This updates the given info on the persistence storage
	 * @param info
	 * @pre info must be a valid GameInfo 
	 */
	public void updateGameInfo(GameInfo info);
	
	/**
	 * This updates the list of commands for the game with the given gameID
	 * @param gameID
	 * @param commands
	 * @pre gameID must be a valid id for a game currently in the persistence storage
	 * @post the list of commands has been updated for the game with the given id
	 */
	public void addCommand(int gameID, MoveCommand command);
	
	/**
	 * This returns a list of all game models in the persistence storage
	 * @return list of game models in the persistence storage
	 * @pre none
	 * @post returns a list of the models in the persistence storage all updated with the associated commands stored on the persistence storage. the commands for all of the games are now empty
	 */
	public List<GameModel> getAllGameModels();
	
	/**
	 * This returns a list of all game infos in the persistence storage
	 * @return list of game infos in the persistence storage
	 * @pre none
	 * @post returns the list of game infos in the persistence storages
	 */
	public List<GameInfo> getAllGameInfos();
	
	/**
	 * This returns the list of commands for the game with the given id
	 * @param gameID
	 * @return list of commands for game model
	 * @pre gameID is a valid id for a game
	 * @post returns list of commands
	 */
	public List<MoveCommand> getCommands(int gameID);
}
