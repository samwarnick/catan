package shared.communication.input;

import java.util.List;

/**
 * This class contains the gameCommandsPost method name and the list of commands previously retrieved from the server.
 * @author Matt
 * 
 */
public class GameCommandsPostInput extends Input {

	private List<String> commandsList;
	
	public GameCommandsPostInput(List<String> commandsList) {
		super("/game/commandsPost");
		this.commandsList = commandsList;
	}

	public List<String> getCommandsList() {
		return commandsList;
	}

}
