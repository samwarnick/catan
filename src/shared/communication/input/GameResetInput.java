package shared.communication.input;

/**
 * 
 * @author Matt
 * This class contains the gameReset method name and whether the game was User or Computer created.
 * 
 */
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
