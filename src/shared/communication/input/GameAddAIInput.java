package shared.communication.input;

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
