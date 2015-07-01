package shared.model;

import java.util.List;

import shared.model.bank.Bank;
import shared.model.board.Board;
import shared.model.board.Robber;
import shared.model.player.Player;

/**
 * The model for the game
 * @author samwarnick
 *
 */
public class GameModel {
	
	private int gameID;
	private int gameVersion;
	private Board board;
	private Robber robber;
	private List<Player> players;
	// private TurnTracker turnTracker;
	private Bank bank;
	
	public GameModel() {
		
	}
}
