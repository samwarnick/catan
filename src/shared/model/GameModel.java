package shared.model;

import java.util.ArrayList;
import java.util.List;

import shared.model.bank.Bank;
import shared.model.board.Board;
import shared.model.board.PlayerID;
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
		players.add(null);
		players.add(null);
		players.add(null);
		players.add(null);
		turnTracker = new TurnTracker();
		bank = new Bank();
	}
	
	public void addPlayer(Player player) throws TooManyPlayersException{
		boolean tooManyPlayers = true;
		int indexToAdd = -1;
		for (int i = players.size() - 1;i >= 0;i--){
			if (players.get(i) == null){
				tooManyPlayers = false;
				indexToAdd = i;
			}
		}
		if (!tooManyPlayers){
			players.add(indexToAdd, player);
			players.remove(4);
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
	
	public Player getPlayer(PlayerID id) {
		for(Player player : players) {
			if(player.getPlayerID().equals(id)) {
				return player;
			}
		}
		assert false;
		return null;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bank == null) ? 0 : bank.hashCode());
		result = prime * result + ((board == null) ? 0 : board.hashCode());
		result = prime * result + gameID;
		result = prime * result + gameVersion;
		result = prime * result + ((players == null) ? 0 : players.hashCode());
		result = prime * result
				+ ((turnTracker == null) ? 0 : turnTracker.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GameModel other = (GameModel) obj;
		if (bank == null) {
			if (other.bank != null)
				return false;
		} else if (!bank.equals(other.bank))
			return false;
		if (board == null) {
			if (other.board != null)
				return false;
		} else if (!board.equals(other.board))
			return false;
		if (gameID != other.gameID)
			return false;
		if (gameVersion != other.gameVersion)
			return false;
		if (players == null) {
			if (other.players != null)
				return false;
		} else if (!players.equals(other.players))
			return false;
		if (turnTracker == null) {
			if (other.turnTracker != null)
				return false;
		} else if (!turnTracker.equals(other.turnTracker))
			return false;
		return true;
	}
	
	
}

