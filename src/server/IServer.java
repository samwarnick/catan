package server;

import java.util.List;

import shared.communication.input.*;
import shared.model.GameModel;

/**
 * 
 * @author samwarnick
 * All inputs contain the method they are calling (i.e. /user/login)
 */
public interface IServer {

	// USER
	
	/**
	 * Logs the caller into the server, and sets their catan.user HTTP cookie.
	 * @param input contains username and password
	 * @pre username AND password != null
	 * @post VALID:<br>1. The server returns an HTTP 200 success response with “Success” in the body.<br>
	 * 2. The HTTP response headers set the catan.user cookie to contain the identity of the
	 * logged­in player.<br><br>FAIL:<br>The server returns an HTTP 400 error response, and the body contains an error message.
	 * @throws ServerException if status is not <pre>HTTP_OK</pre>
	 */
	public String loginUser(UserLoginInput input) throws ServerException; // boolean? or cookie? user?
	
	/**
	 * Creates a new user account and logs the caller into the server as the new user, and sets their catan.user HTTP cookie.
	 * @param input contains username, password, and username
	 * @pre username and password != null AND username not in use
	 * @post VALID:<br>1. A new user account has been created with the specified username and password.<br>
	 * 2.The server returns an HTTP 200 success response with “Success” in the body.<br>
	 * 3. The HTTP response headers set the catan.user cookie to contain the identity of the
	 * logged­in player.<br><br>FAIL:<br>The server returns an HTTP 400 error response, and the body contains an error message.
	 * @throws ServerException if status is not <pre>HTTP_OK</pre>
	 */
	public String registerUser(UserRegisterInput input) throws ServerException; // boolean? or cookie? user?
	
	// GAME
	
	/**
	 * Returns information about all of the current games on the server.
	 * @param input
	 * @pre none
	 * @post VALID:<br>1. The server returns an HTTP 200 success response.<br>
	 * 2. The body contains a JSON array containing a list of objects that contain information<br>
	 * <br>FAIL:<br>The server returns an HTTP 400 error response, and the body contains an error message.
	 * @throws ServerException if status is not <pre>HTTP_OK</pre>
	 */
	public List<GameModel> listGames(GamesListInput input) throws ServerException;  // list of GameModel?
	
	/**
	 * Creates a new game on the server.
	 * @param input contains gameName, randomTiles, randomNumbers, randomPorts
	 * @pre gameName != null AND randomTiles, randomNumbers, randomPorts valid boolean
	 * @post VALID:<br>1. A new game with the specified properties has been created<br>
	 * 2. The server returns an HTTP 200 success response.<br>
	 * 3. The body contains a JSON object describing the newly created game<br>
	 * <br>FAIL:<br>The server returns an HTTP 400 error response, and the body contains an error
	 * @throws ServerException if status is not <pre>HTTP_OK</pre>
	 */
	public GameModel createGame(GamesCreateInput input) throws ServerException; // GameModel?
	
	/**
	 * Adds the player to the specified game and sets their catan.game cookie.
	 * @param input contains user
	 * @pre 1. The user has previously logged in to the server (i.e., they have a valid catan.user HTTP cookie).<br>
	 * 2. The player may join the game because:<br>
	 * <li>a. They are already in the game, OR<br>
	 * <li>b. There is space in the game to add a new player<br>
	 * 3. The specified game ID is valid<br>
	 * 4. The specified color is valid (red, green, blue, yellow, puce, brown, white, purple, orange)<br>
	 * @post VALID:<br>1. The server returns an HTTP 200 success response with “Success” in the body.<br>
	 * 2. The player is in the game with the specified color 
	 * (i.e. calls to /games/list method will show the player in the game with the chosen color).<br>
	 * 3. The server response includes the “Set­cookie” response header setting the catan.game HTTP cookie<br>
	 * <br>FAIL:<br>The server returns an HTTP 400 error response, and the body contains an error
	 * @throws ServerException if status is not <pre>HTTP_OK</pre>
	 */
	public String joinGame(GamesJoinInput input) throws ServerException; // cookie?
	
	/**
	 * This method is for testing and debugging purposes. 
	 * When a bug is found, you can use the /games/save method to save the state of the game to a file, and attach the file to a bug report. 
	 * A developer can later restore the state of the game when the bug occurred by loading the previously saved file 
	 * using the /games/load method. Game files are saved to and loaded from the server's saves/ directory.
	 * @param input contains gameID and filename
	 * @pre 1. The specified game ID is valid <br>
	 * 2. The specified file name is valid (i.e., not null or empty)<br>
	 * @post VALID:<br>1. The server returns an HTTP 200 success response with “Success” in the body.<br>
	 * 2. The current state of the specified game (including its ID) has been saved to the specified file name in the server’s saves/ directory<br>
	 * <br>FAIL:<br>The server returns an HTTP 400 error response, and the body contains an error
	 * @throws ServerException if status is not <pre>HTTP_OK</pre>
	 */
	public boolean saveGame(GamesSaveInput input) throws ServerException; // boolean?
	
	/**
	 * This method is for testing and debugging purposes.
	 * When a bug is found, you can use the /games/save method to save the state of the game to a file, and attach the file to a bug report. 
	 * A developer can later restore the state of the game when the bug occurred by loading the previously saved file 
	 * using the /games/load method. Game files are saved to and loaded from the server's saves/ directory.
	 * @param input contains filename
	 * @pre A previously saved game file with the specified name exists in the server’s saves/ directory.
	 * @post VALID:<br>1. The server returns an HTTP 200 success response with “Success” in the body.<br>
	 * 2. The game in the specified file has been loaded into the server and its state restored (including its ID).<br>
	 * <br>FAIL:<br>The server returns an HTTP 400 error response, and the body contains an error<br>
	 * @throws ServerException if status is not <pre>HTTP_OK</pre>
	 */
	public GameModel loadGame(GamesLoadInput input) throws ServerException; // GameModel?
	
	/**
	 * Returns the current state of the game in JSON format.<br><br>
	 * In addition to the current game state, the returned JSON also includes a “version” number for the client model. 
	 * The next time /game/model is called, the version number from the previously retrieved model may optionally be included 
	 * as a query parameter in the request (/game/model?version=N). The server will only return the full JSON game state if 
	 * its version number is not equal to N. If it is equal to N, the server returns “true” to indicate that the caller already 
	 * has the latest game state. This is merely an optimization. If the version number is not included in the request URL, 
	 * the server will return the full game state.
	 * @param input contains user, version number
	 * @pre The caller has previously logged in to the server and joined a game
	 * 2. If specified, the version number is included as the “version” query parameter in the request URL, and its value is a valid integer.
	 * @post VALID:<br>1. The server returns an HTTP 200 success response.<br>
	 * 2. The response body contains JSON data<br>
	 * <li>a. The full client model JSON is returned if the caller does not provide a version number, 
	 * or the provide version number does not match the version on the server<br>
	 * <li>b. “true” (true in double quotes) is returned if the caller provided a version number, 
	 * and the version number matched the version number on the server<br>
	 * <br>FAIL:<br>The server returns an HTTP 400 error response, and the body contains an error
	 * @throws ServerException if status is not <pre>HTTP_OK</pre>
	 */
	public GameModel getGameModelVersion(GameModelVersionInput input) throws ServerException;
	
	/**
	 * Clears out the command history of the current game.<br><br>
	 * For the default games created by the server, this method reverts the game to the state immediately after the initial placement round.
	 * For user ­created games, this method reverts the game to the very beginning (i.e., before the initial placement round).<br><br>
	 * This method returns the client model JSON for the game after it has been reset.
	 * @param input contains user
	 * @pre The caller has previously logged in to the server and joined a game (i.e., they have valid catan.user and catan.game HTTP cookies).
	 * @post VALID:<br>1. The game’s command history has been cleared out<br>
	 * 2. The game’s players have NOT been cleared out<br>
	 * 3. The server returns an HTTP 200 success response.<br>
	 * 4. The body contains the game’s updated client model JSON<br>
	 * <br>FAIL:<br>The server returns an HTTP 400 error response, and the body contains an error
	 * @throws ServerException if status is not <pre>HTTP_OK</pre>
	 */
	public GameModel resetGame(GameResetInput input) throws ServerException; // GameModel?
	
	/**
	 * Returns a list of commands that have been executed in the current game.<br><br>
	 * This method can be used for testing and debugging. The command list returned by this method can be passed 
	 * to the /game/command (POST) method to re­execute the commands in the game. This would typically be done after 
	 * calling /game/reset to clear out the game’s command history. This is one way to capture the state of a game and restore it later. 
	 * (See the /games/save and /games/load methods which provide another way to save and restore the state of a game.)<br><br>
	 * For the default games created by the server, this method returns a list of all commands that have been executed after 
	 * the initial placement round. For user­created games, this method returns a list of all commands that have been executed since 
	 * the very beginning of the game (i.e., before the initial placement round).
	 * @param input contains user
	 * @pre The caller has previously logged in to the server and joined a game (i.e., they have valid catan.user and catan.game HTTP cookies).
	 * @post VALID:<br>1. The server returns an HTTP 200 success response.<br>
	 * 2. The body contains a JSON array of commands that have been executed in the game. 
	 * This command array is suitable for passing back to the /game/command [POST] method to restore the state of the 
	 * game later (after calling /game/reset to revert the game to its initial state).<br>
	 * <br>FAIL:<br>The server returns an HTTP 400 error response, and the body contains an error
	 * @throws ServerException if status is not <pre>HTTP_OK</pre>
	 */
	public List<String> getGameCommands(GameCommandsGetInput input) throws ServerException; // list of commands?
	
	/**
	 * Executes the specified command list in the current game.<br><br>
	 * This method can be used for testing and debugging. The command list returned by the /game/command [GET] 
	 * method is suitable for passing to this method.<br><br>
	 * This method returns the client model JSON for the game after the command list has been applied.
	 * @param input contains user and commands
	 * @pre The caller has previously logged in to the server and joined a game (i.e., they have valid catan.user and catan.game HTTP cookies).
	 * @post VALID:<br>1. The passed­in command list has been applied to the game.<br>
	 * 2. The server returns an HTTP 200 success response.<br>
	 * 3. The body contains the game’s updated client model JSON<br>
	 * <br>FAIL:<br>The server returns an HTTP 400 error response, and the body contains an error
	 * @throws ServerException if status is not <pre>HTTP_OK</pre>
	 */
	public GameModel postGameCommands(GameCommandsPostInput input) throws ServerException; // GameModel?
	
	// public GameListAIOutput listGameAI(GameListAIInput input)
	
	// public GameAddAIOutput addGameAI(GameAddAIOutput input)
	
	// UTIL
	
	/**
	 * Sets the server’s logging level.
	 * @param input 
	 * @pre The caller specifies a valid logging level. Valid values include: <pre>SEVERE, WARNING, INFO, CONFIG, FINE, FINER, FINEST</pre>
	 * @post VALID:<br>1. The server returns an HTTP 200 success response with “Success” in the body.<br>
	 * 2. The Server is using the specified logging level<br>
	 * <br>FAIL:<br>The server returns an HTTP 400 error response, and the body contains an error
	 * @throws ServerException if status is not <pre>HTTP_OK</pre>
	 */
	public boolean changeLogLevel(UtilChangeLogLevelInput input) throws ServerException; // boolean?
	
	// MOVES
	// ANYTIME
	
	/**
	 * Player chats.
	 * @param input contains message
	 * @pre none
	 * @post The chat contains your message at the end
	 * @throws ServerException if status is not <pre>HTTP_OK</pre>
	 */
	public GameModel sendChat(MoveSendChat input) throws ServerException;
	
	// MISC.
	
	/**
	 * Player gets excited and accepts trade, or not. Either is fine.
	 * @param input contains willAccept boolean
	 * @pre 1. You have been offered a domestic trade<br>
	 * 2. To accept the offered trade, you have the required resources<br>
	 * @post 1. If you accepted, you and the player who offered swap the specified resources<br>
	 * 2. If you declined no resources are exchanged<br>
	 * 3. The trade offer is removed<br>
	 * @throws ServerException if status is not <pre>HTTP_OK</pre>
	 */
	public GameModel acceptTrade(MoveAcceptTrade input) throws ServerException;
	
	/**
	 * Player whimpers as they discard cards
	 * @param input contains bank of cards to discard
	 * @pre The status of the client model is 'Discarding'<br>
	 * You have over 7 cards<br>
	 * You have the cards you're choosing to discard.
	 * @post 1. You gave up the specified resources<br>
	 * 2. If you're the last one to discard, the client model status changes to 'Robbing'
	 * @throws ServerException if status is not <pre>HTTP_OK</pre>
	 */
	public GameModel discardCards(MoveDiscardCards input) throws ServerException;
	
	// ROLLING
	
	/**
	 * Player rolls load dice, if they are a no good cheater
	 * @param input contains number
	 * @pre It is your turn<br>
	 * The client model’s status is ‘Rolling’
	 * @post The client model’s status is now in ‘Discarding’ or ‘Robbing’ or ‘Playing’
	 * @throws ServerException if status is not <pre>HTTP_OK</pre>
	 */
	public GameModel rollNumber(MoveRollNumber input) throws ServerException;
	
	// PLAYING
	/**
	 * PLAYING
	 * @pre 1. It is your turn<br>
	 * 2. The client model’s status is 'Playing'
	 */
	/**
	 * Player builds a road
	 * @param input contains free and roadLocation
	 * @preThe 1. road location is open<br>
	 * 2. The road location is connected to another road owned by the player<br>
	 * 3. The road location is not on water<br>
	 * 4. You have the required resources (1 wood, 1 brick; 1 road)<br>
	 * 5. Setup round: Must be placed by settlement owned by the player with no adjacent road
	 * @post 1. You lost the resources required to build a road (1 wood, 1 brick; 1 road)<br>
	 * 2. The road is on the map at the specified location<br>
	 * 3. If applicable, “longest road” has been awarded to the player with the longest road
	 * @throws ServerException if status is not <pre>HTTP_OK</pre>
	 */
	public GameModel buildRoad(MoveBuildRoad input) throws ServerException;
	
	/**
	 * Player builds a settlement
	 * @param input contains free and vertexLocation
	 * @pre 1. The settlement location is open<br>
	 * 2. The settlement location is not on water<br>
	 * 3. The settlement location is connected to one of your roads except during setup<br>
	 * 4. You have the required resources (1 wood, 1 brick, 1 wheat, 1 sheep; 1 settlement)<br>
	 * 5. The settlement cannot be placed adjacent to another settlement<br>
	 * @post 1. You lost the resources required to build a settlement (1 wood, 1 brick, 1 wheat, 1 sheep; 1 settlement)<br>
	 * 2.The settlement is on the map at the specified location
	 * @throws ServerException if status is not <pre>HTTP_OK</pre>
	 */
	public GameModel buildSettlement(MoveBuildSettlement input) throws ServerException;
	
	/**
	 * Player builds a City
	 * @param input contains vertexLocation
	 * @pre 1. The city location is where you currently have a settlement<br>
	 * 2. You have the required resources (2 wheat, 3 ore; 1 city)
	 * @post 1. You lost the resources required to build a city (2 wheat, 3 ore; 1 city)<br>
	 * 2. The city is on the map at the specified location<br>
	 * 3. You got a settlement back<br>
	 * @throws ServerException if status is not <pre>HTTP_OK</pre>
	 */
	public GameModel buildCity(MoveBuildCity input) throws ServerException;
	
	/**
	 * Player offers a trade
	 * @param input contains offer and receiver
	 * @pre You have the resources you are offering
	 * @post The trade is offered to the other player (stored in the server model)
	 * @throws ServerException if status is not <pre>HTTP_OK</pre>
	 */
	public GameModel offerTrade(MoveOfferTrade input) throws ServerException;
	
	/**
	 * Player trades with the salty sea
	 * @param input contains TradeRation, inputResource, and outputResource
	 * @pre 1. You have the resources you are giving<br>
	 * 2. For ratios less than 4, you have the correct port for the trade
	 * @post The trade has been executed (the offered resources are in the bank, and the requested resource has been received)
	 * @throws ServerException if status is not <pre>HTTP_OK</pre>
	 */
	public GameModel maritimeTrade(MoveMaritimeTrade input) throws ServerException;
	
	/**
	 * Player robs another helpless victim
	 * @param input contains location and victim
	 * @pre 1. The robber is not being kept in the same location<br>
	 * 2. If a player is being robbed (i.e., victimIndex != ­1), the player being robbed has resource cards
	 * @post 1. The robber is in the new location<br>
	 * 2. The player being robbed (if any) gave you one of his resource cards (randomly selected)
	 * @throws ServerException if status is not <pre>HTTP_OK</pre>
	 */
	public GameModel robPlayer(MoveRobPlayer input) throws ServerException;
	
	/**
	 * Player takes their sweet time and finishes their turn, finally
	 * @param input
	 * @pre none 
	 * @post 1. The cards in your new dev card hand have been transferred to your old dev card hand<br>
	 * 2. It is the next player’s turn
	 * @throws ServerException if status is not <pre>HTTP_OK</pre>
	 */
	public GameModel finishTurn(MoveFinishTurn input) throws ServerException;
	
	/**
	 * Player shows their wealth by purchasing a development card to the awe of the other players
	 * @param input
	 * @pre 1. You have the required resources (1 ore, 1 wheat, 1 sheep)<br>
	 * 2. There are dev cards left in the deck
	 * @post You have a new card<br>
	 * <li>If it is a monument card, it has been added to your old devcard hand
	 * <li>If it is a non­monument card, it has been added to your new devcard hand (unplayable this turn)
	 * @throws ServerException if status is not <pre>HTTP_OK</pre>
	 */
	public GameModel buyDevCard(MoveBuyDevCard input) throws ServerException;
	
	// DEV CARD
	/**
	 * DEV CARD
	 * @pre 1. It is your turn<br>
	 * 2. The client model status is 'Playing'<br>
	 * 3. You have the specific card you want to play in your old dev card hand<br>
	 * 4. You have not yet played a non­monument dev card this turn<br>
	 */
	
	/**
	 * Player plays soldier card
	 * @param input contains location and victimIndex
	 * @pre 1. The robber is not being kept in the same location<br>
	 * 2. If a player is being robbed (i.e., victimIndex != ­1), the player being robbed has resource cards
	 * @post 1. The robber is in the new location<br>
	 * 2. The player being robbed (if any) gave you one of his resource cards (randomly selected)<br>
	 * 3. If applicable, “largest army” has been awarded to the player who has played the most soldier cards<br>
	 * 4. You are not allowed to play other development cards during this turn (except for monument cards, which may still be played)
	 * @throws ServerException if status is not <pre>HTTP_OK</pre>
	 */
	public GameModel playSoldier(MoveSoldier input) throws ServerException;
	
	/**
	 * Player plays soldier card
	 * @param input contains two resources
	 * @pre The two specified resources are in the bank
	 * @post You gained the two specified resources
	 * @throws ServerException if status is not <pre>HTTP_OK</pre>
	 */
	public GameModel playYearOfPlenty(MoveYearOfPlenty input) throws ServerException;
	
	/**
	 * Player plays soldier card
	 * @param input contains two edge locations
	 * @pre 1. The first road location (spot1) is connected to one of your roads.<br>
	 * 2. The second road location (spot2) is connected to one of your roads or to the first road location (spot1)<br>
	 * 3. Neither road location is on water<br>
	 * 4. You have at least two unused roads
	 * @post 1. You have two fewer unused roads<br>
	 * 2. Two new roads appear on the map at the specified locations<br> 
	 * 3. If applicable, “longest road” has been awarded to the player with the longest road
	 * @throws ServerException if status is not <pre>HTTP_OK</pre>
	 */
	public GameModel playRoadBuilding(MoveRoadBuilding input) throws ServerException;
	
	/**
	 * Player plays soldier card
	 * @param input contains resource
	 * @pre General preconditions
	 * @post All of the other players have given you all of their resource cards of the specified type
	 * @throws ServerException if status is not <pre>HTTP_OK</pre>
	 */
	public GameModel playMonopoly(MoveMonopoly input) throws ServerException;
	
	/**
	 * Player plays soldier card
	 * @param input
	 * @pre You have enough monument cards to win the game (i.e., reach 10 victory points)
	 * @post You gained a victory point
	 * @throws ServerException if status is not <pre>HTTP_OK</pre>
	 */
	public GameModel playMonument(MoveMonument input) throws ServerException;
}