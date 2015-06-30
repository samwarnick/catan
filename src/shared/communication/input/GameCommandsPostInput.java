package shared.communication.input;

/**
 * 
 * @author Matt
 * This class contains the gameCommandsPost method name and the list of commands previously retrieved from the server.
 * 
 */
public class GameCommandsPostInput extends Input {

	private GameCommandsGetOutput commandsList;
	
	public GameCommandsPostInput(GameCommandsGetOutput commandsList) {
		super("/game/commandsPost");
		this.commandsList = commandsList;
	}

	public GameCommandsGetOutput getCommandsList() {
		return commandsList;
	}

}
