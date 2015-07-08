package shared.model;

import java.util.ArrayList;
import java.util.List;

import shared.model.bank.Bank;
import shared.model.board.Board;
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
	private List<Player> players;
	private TurnTracker turnTracker;
	private Bank bank;
	
	public GameModel(int gameID) {
		this.gameID = gameID;
		gameVersion = 0;
		players = new ArrayList<Player>();
		turnTracker = new TurnTracker();
		bank = new Bank();
	}
	
	public void addPlayer(Player player) throws TooManyPlayersException{
		if (players.size() < 4){
			players.add(player);
		}
		else
			throw new TooManyPlayersException();
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public int getGameID() {
		return gameID;
	}

	public void setGameID(int gameID) {
		this.gameID = gameID;
	}

	public int getGameVersion() {
		return gameVersion;
	}

	public void setGameVersion(int gameVersion) {
		this.gameVersion = gameVersion;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	public TurnTracker getTurnTracker() {
		return turnTracker;
	}

	public void setTurnTracker(TurnTracker turnTracker) {
		this.turnTracker = turnTracker;
	}

	public Bank getBank() {
		return bank;
	}

	public void setBank(Bank bank) {
		this.bank = bank;
	}
}

