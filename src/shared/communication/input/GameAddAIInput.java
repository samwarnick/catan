package shared.communication.input;

/**
 * 
 * @author Matt
 * This class contains the gameAddAI method name and the AI player type.
 * 
 */
public class GameAddAIInput extends Input {

	private AIPlayerType playerType;
	
	public GameAddAIInput(AIPlayerType playerType) {
		super("/game/addAI");
		this.playerType = playerType;
	}

	public AIPlayerType getPlayerType() {
		return playerType;
	}
	
}
