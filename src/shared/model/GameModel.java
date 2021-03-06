package shared.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import client.communication.LogEntry;
import client.domestic.Trade;
import shared.definitions.CatanColor;
import shared.locations.EdgeDirection;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexDirection;
import shared.locations.VertexLocation;
import shared.model.bank.Bank;
import shared.model.bank.BankException;
import shared.model.bank.DevelopmentHand;
import shared.model.bank.ResourceHand;
import shared.model.board.Board;
import shared.model.board.BoardFacade;
import shared.model.board.City;
import shared.model.board.PlayerID;
import shared.model.board.Road;
import shared.model.board.Settlement;
import shared.model.board.Vertex;
import shared.model.player.ActivePlayerFacade;
import shared.model.player.InactivePlayerFacade;
import shared.model.player.Player;
import shared.model.player.VictoryPoints;

/**
 * The model for the game
 * @author samwarnick
 *
 */
@SuppressWarnings("serial")
public class GameModel implements Serializable{
	
	private int gameID;
	private int gameVersion;
	private Board board;
	private List<Player> players;
	private TurnTracker turnTracker;
	private Bank bank;
	private Trade trade;
	List<LogEntry> logs = new ArrayList<LogEntry>();
	List<LogEntry>  chats = new ArrayList<LogEntry>();
	private int winner = -1;
	private static GameModel defaultGM;
	
	public GameModel() {
		gameID = 0;
		gameVersion = 0;
		board = null;
		players = null;
		turnTracker = null;
		bank = null;
		trade = null;
		logs = null;
		chats = null;
		
	}
	
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
			player.setPlayerID(indexToAdd);
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
	
	public Player getPlayer(String playerName){
		for(Player player : players) {
			if(player != null && player.getName().equals(playerName)) {
				return player;
			}
		}
		return null;
	}
	
	public Player getPlayer(PlayerID id) {
		for(Player player : players) {
			if(player.getPlayerID().equals(id)) {
				return player;
			}
		}
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
	
	@JsonIgnore public Player getCurrentPlayer(){
		return players.get(turnTracker.getCurrentTurn());
	}

	public List<LogEntry> getLogs() {
		return logs;
	}

	public List<LogEntry> getChats() {
		return chats;
	}

	public void setLogs(List<LogEntry> logs) {
		this.logs = logs;
	}

	public void setChats(List<LogEntry> chats) {
		this.chats = chats;
	}
	
	

	public Trade getTrade() {
		return trade;
	}

	public void setTrade(Trade trade) {
		this.trade = trade;
	}

	public int getWinner() {
		return winner;
	}

	public void setWinner(int winner) {
		this.winner = winner;
	}

	public void assignLongestRoad() {
		Player player = players.get(0);
		for (Player p : players) {
			if (p.hasLongestRoad()) {
				player = p;
			}
		}
		
		for (Player p : players) {
			if (p.getLongestRoad().getNumRoads() > player.getLongestRoad().getNumRoads()) {
				player = p;
			}
		}
		
		if (player.getLongestRoad().getNumRoads() >= 5 && !player.hasLongestRoad()) {
			if (bank.hasLongestRoadCard()) {
				bank.setLongestRoadCard(false);
				player.getLongestRoad().setHasLongestRoad(true);
				player.getVictoryPoints().addPublicVictoryPoint();
				player.getVictoryPoints().addPublicVictoryPoint();
			}
			else {
				for (Player otherPlayer : players) {
					if (otherPlayer.hasLongestRoad()) {
						otherPlayer.getLongestRoad().setHasLongestRoad(false);
						otherPlayer.getVictoryPoints().subtractPublicVictoryPoints(2);
						player.getLongestRoad().setHasLongestRoad(true);
						player.getVictoryPoints().addPublicVictoryPoint();
						player.getVictoryPoints().addPublicVictoryPoint();
						return;
					}
				} 				
			}
			assert(player.hasLongestRoad());
		}
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

	@Override
	public String toString() {
		return "GameModel [gameID=" + gameID + ", gameVersion=" + gameVersion
				+ ", board=" + board + ", players=" + players
				+ ", turnTracker=" + turnTracker + ", bank=" + bank
				+ ", trade=" + trade + "]";
	}
	
	@JsonIgnore public static GameModel getDefaultModel(){
		if(defaultGM == null){
			defaultGM  = new GameModel(0);
			try {
				Player harold = new Player(CatanColor.BROWN, "Spencer", 0);
				harold.getPlayerBank().setRC(4);
				harold.setPlayerFacade(new ActivePlayerFacade(harold));
				harold.setUniqueID(0);
				harold.setVictoryPoints(new VictoryPoints(9,9));
				harold.getPlayerBank().addNewDC(new DevelopmentHand(1,1,1,1,1));
				harold.getPlayerBank().transfer();
				Player gretchen = new Player(CatanColor.GREEN, "Sam", 1);
//				gretchen.getPlayerBank().setLargestArmyCard(true);
//				gretchen.getPlayerBank().setLongestRoadCard(true);
				gretchen.setPlayerFacade(new InactivePlayerFacade(gretchen));
				gretchen.setUniqueID(1);
				Player ingrid  = new Player(CatanColor.ORANGE, "Matt", 2);
				ingrid.getPlayerBank().modifyRC(new ResourceHand(4,0,2,1,0));
				ingrid.setPlayerFacade(new InactivePlayerFacade(ingrid));
				ingrid.setUniqueID(2);
				Player jerry = new Player(CatanColor.BLUE, "Isaac", 3);
				jerry.getPlayerBank().modifyRC(new ResourceHand(2,2,2,1,1));
				jerry.setPlayerFacade(new InactivePlayerFacade(jerry));
				jerry.setUniqueID(4);
				defaultGM.addPlayer(harold);
				defaultGM.addPlayer(gretchen);
				defaultGM.addPlayer(ingrid);
				defaultGM.addPlayer(jerry);
				Board board = new Board(false, false, false);
				List<Vertex> vertices = new ArrayList<Vertex>();
				vertices.add(new Settlement(new PlayerID(0), new VertexLocation(new HexLocation(0,0), VertexDirection.East)));
				vertices.add(new City(new PlayerID(0), new VertexLocation(new HexLocation(-1,-1), VertexDirection.SouthWest)));
				vertices.add(new Settlement(new PlayerID(3), new VertexLocation(new HexLocation(1,1), VertexDirection.NorthEast)));
				board.setBuildings(vertices);
				List<Road> roads = new ArrayList<Road>();
				roads.add(new Road(new PlayerID(0), new EdgeLocation(new HexLocation(0,0), EdgeDirection.SouthEast)));
				roads.add(new Road(new PlayerID(0), new EdgeLocation(new HexLocation(0,0), EdgeDirection.South)));
				roads.add(new Road(new PlayerID(3), new EdgeLocation(new HexLocation(1,1), EdgeDirection.North)));
				roads.add(new Road(new PlayerID(3), new EdgeLocation(new HexLocation(0,2), EdgeDirection.NorthEast)));
				board.setRoads(roads);
				BoardFacade bf = new BoardFacade(board);
//				bf.setBoard(board);
				board.setBoardFacade(bf);
				defaultGM.setBoard(board);
				TurnTracker track = new TurnTracker();
				try {
					track.setCurrentTurn(0);
				} catch (NoPlayerFoundException e1) {
					e1.printStackTrace();
				}
				track.setStatus("Rolling");
				defaultGM.setTurnTracker(track);
				Bank bank = new Bank();
				bank.modifyRC(new ResourceHand(-4,-4,-4,-4,-4));
			} catch (TooManyPlayersException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (BankException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return defaultGM;
	}

	@JsonIgnore public static GameModel getDefaultGM() {
		return defaultGM;
	}

	@JsonIgnore public static void setDefaultGM(GameModel defaultGM) {
		GameModel.defaultGM = defaultGM;
	}

	public void updateLogs(CatanColor cc, String name) {
		int i = 0;
		CatanColor old = getPlayer(name).getColor();
			for(i=0;i<logs.size();i++){
				if(logs.get(i).getColor() == (old)){
					logs.get(i).setColor(cc);
				}
			}
			for(i=0;i<chats.size();i++){
				if(chats.get(i).getColor() == (old)){
					chats.get(i).setColor(cc);
				}
			}
	}	
}