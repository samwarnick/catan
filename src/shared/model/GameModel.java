package shared.model;

import java.util.ArrayList;
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

	public Robber getRobber() {
		return robber;
	}

	public void setRobber(Robber robber) {
		this.robber = robber;
	}

	public Bank getBank() {
		return bank;
	}

	public void setBank(Bank bank) {
		this.bank = bank;
	}
	
	
}

