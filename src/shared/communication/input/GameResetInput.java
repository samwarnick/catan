package shared.communication.input;

public class GameResetInput extends Input {

	private boolean isUserCreatedGame;
	
	public GameResetInput (boolean isUserCreatedGame) {
		super("/game/reset");
		this.isUserCreatedGame = isUserCreatedGame;
	}

	public boolean isUserCreatedGame() {
		return isUserCreatedGame;
	}
	
}
