package client.controller;

import java.util.ArrayList;
import java.util.List;

import client.join.JoinGameController;
import client.poller.Poller;
import client.proxy.ProxyServer;
import server.ServerException;
import shared.communication.input.*;
import shared.communication.input.move.*;
import shared.definitions.ResourceType;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;
import shared.model.GameModel;
import shared.model.GameModelFacade;
import shared.model.TooManyPlayersException;
import shared.model.bank.ResourceHand;
import shared.model.board.Board;
import shared.model.player.ActivePlayerFacade;
import shared.model.player.Player;

public class ModelController {

	private GameModelFacade gameModelFacade;
	private Poller poller;
	private Player clientPlayer;
	private JoinGameController JGC;

	private int PlayerID;
	private String playerName;
	private static ModelController instance = null;
	private boolean testing = false;
	
	private List<ModelControllerListener> listeners = new ArrayList<ModelControllerListener>();

	// get instances
	
	public static ModelController getInstance() {
		if (instance == null) {
			instance = new ModelController();
		}
		return instance;
	}
	
	private ModelController(){
		gameModelFacade = GameModelFacade.getInstance(0);
	}
	
	public void startPoller(){
		poller = new Poller();
	}
	
	public void startGame(List<Player> players, boolean randomHexes, boolean randomNumbers, boolean randomPorts){
		for (Player player : players)
			try {
				gameModelFacade.getGameModel().addPlayer(player);
			} catch (TooManyPlayersException e) {
				e.printStackTrace();
			}
		gameModelFacade.getGameModel().setBoard(new Board(randomHexes,randomNumbers,randomPorts));
	}
	
	// getters and setters
	
	public GameModelFacade getGameModelFacade() {
		return gameModelFacade;
	}

	public void setGameModelFacade(GameModelFacade gameModelFacade) {
		this.gameModelFacade = gameModelFacade;
	}

	public Poller getPoller() {
		return poller;
	}
	
	public void setPoller(Poller poller) {
		this.poller = poller;
	}

	public void updateGame(GameModel gameModel){
		// replace model
		GameModelFacade.getInstance().setGameModel(gameModel);
		
		// if you are current player, set playerFacade to ActivePlayerFacade
		if (!testing)
		{
			clientPlayer = GameModelFacade.getInstance().getGameModel().getPlayer(playerName);
			PlayerID = clientPlayer.getPlayerID().getPlayerid();
			playerName = clientPlayer.getName();
		}
		
		int current = gameModelFacade.getGameModel().getTurnTracker().getCurrentTurn();
		Player currentPlayer = gameModelFacade.getGameModel().getPlayers().get(current);
		if (clientPlayer!=null && clientPlayer.getName().equals(currentPlayer.getName())) {
			clientPlayer.setPlayerFacade(new ActivePlayerFacade(clientPlayer));
		}
		
		notifyListeners();
	}
	
	

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public int getPlayerID() {
		return PlayerID;
	}

	public void setPlayerID(int playerID) {
		PlayerID = playerID;
	}
	
	public boolean isTesting() {
		return testing;
	}

	public void setTesting(boolean testing) {
		this.testing = testing;
	}
	
	public Player getClientPlayer() {
		return clientPlayer;
	}
	
	public Player getClientStartingPlayer(){
		if (clientPlayer == null){
			clientPlayer = GameModelFacade.getInstance().getGameModel().getPlayer(playerName);

		}
		
		return clientPlayer;
	}
	
	// moves
	
	public void finishTurn() {
		FinishTurnInput input = new FinishTurnInput(clientPlayer.getPlayerID().getPlayerid());
		try {
			updateGame(ProxyServer.getInstance().finishTurn(input));
		} catch (ServerException e) {
			e.printStackTrace();
		}
	}
	
	public void rollDice(RollNumberInput input){
		try {
			updateGame(ProxyServer.getInstance().rollNumber(input));
		} catch (ServerException e) {
			e.printStackTrace();
		}
	}
	
	public void domesticTrade(AcceptTradeInput input){
		try {
			updateGame(ProxyServer.getInstance().acceptTrade(input));
		} catch (ServerException e) {
			e.printStackTrace();
		}
	}
	
	public void maritimeTrade(MaritimeTradeInput input){
		try {
			updateGame(ProxyServer.getInstance().maritimeTrade(input));
		} catch (ServerException e) {
			e.printStackTrace();
		}
	}
	
	public void sendTrade(OfferTradeInput input){
		try {
			updateGame(ProxyServer.getInstance().offerTrade(input));
		} catch (ServerException e) {
			e.printStackTrace();
		}
	}
	
	 
	public void getGameModelVersion(GameModelVersionInput input){
		try {
			updateGame(ProxyServer.getInstance().getGameModelVersion(input));
		}
		catch (ServerException e) {
			e.printStackTrace();
		}
	}

	public void discard(ResourceHand toDiscard) {
		DiscardCardsInput input = new DiscardCardsInput(PlayerID, toDiscard);
		try {
			updateGame(ProxyServer.getInstance().discardCards(input));
		} catch (ServerException e) {
			e.printStackTrace();
		}
	}
	
	public void sendChat(SendChatInput input){
		try {
			updateGame(ProxyServer.getInstance().sendChat(input));
		} catch (ServerException e) {
			e.printStackTrace();
		}
	}
	
	public void buildRoad(boolean isFree, EdgeLocation edgeLoc) {
		BuildRoadInput input = new BuildRoadInput(clientPlayer.getPlayerID().getPlayerid(), isFree, edgeLoc);
		try {
			updateGame(ProxyServer.getInstance().buildRoad(input));
		} catch (ServerException e) {
			e.printStackTrace();
		}
	}

	 
	public void buildSettlement(boolean isFree, VertexLocation vertLoc) {
		BuildSettlementInput input = new BuildSettlementInput(clientPlayer.getPlayerID().getPlayerid(), isFree, vertLoc);
		try {
			updateGame(ProxyServer.getInstance().buildSettlement(input));
		} catch (ServerException e) {
			e.printStackTrace();
		}
	}

	 
	public void buildCity(VertexLocation vertLoc) {
		BuildCityInput input = new BuildCityInput(clientPlayer.getPlayerID().getPlayerid(), vertLoc);
		try  {
			updateGame(ProxyServer.getInstance().buildCity(input));
		} catch (ServerException e) {
			e.printStackTrace();
		}
	}

	public void buyDevCard() {
		BuyDevCardInput input = new BuyDevCardInput(PlayerID);
		try {
			updateGame(ProxyServer.getInstance().buyDevCard(input));
		} catch (ServerException e) {
			e.printStackTrace();
		}
	}

	public void playYearOfPlenty(ResourceType resource1, ResourceType resource2) {
		PlayYearOfPlentyInput input = new PlayYearOfPlentyInput(PlayerID, resource1, resource2);
		try {
			updateGame(ProxyServer.getInstance().playYearOfPlenty(input));
		} catch (ServerException e) {
			e.printStackTrace();
		}
	}

	public void robPlayer(HexLocation loc, int victim) {
		RobPlayerInput input = new RobPlayerInput(clientPlayer.getPlayerID().getPlayerid(), loc, victim);
		try {
			updateGame(ProxyServer.getInstance().robPlayer(input));
		} catch (ServerException e) {
			e.printStackTrace();
		}
	}
	
	public void playMonopoly(ResourceType resource) {
		PlayMonopolyInput input = new PlayMonopolyInput(PlayerID, resource.toString().toLowerCase());
		try {
			updateGame(ProxyServer.getInstance().playMonopoly(input));
		} catch (ServerException e) {
			e.printStackTrace();
		}
	}
	
	public void playMonument() {
		PlayMonumentInput input = new PlayMonumentInput(PlayerID);
		try {
			updateGame(ProxyServer.getInstance().playMonument(input));
		} catch (ServerException e) {
			e.printStackTrace();
		}
	}
	
	public void playRoadBuilding(EdgeLocation road1, EdgeLocation road2) {
		PlayRoadBuildingInput input = new PlayRoadBuildingInput(PlayerID, road1, road2);
		try {
			updateGame(ProxyServer.getInstance().playRoadBuilding(input));
		} catch (ServerException e) {
			e.printStackTrace();
		}
	}
	
	public void playSoldier(HexLocation loc, int victim) {
		PlaySoldierInput input = new PlaySoldierInput(clientPlayer.getPlayerID().getPlayerid(), loc, victim);
		try {
			updateGame(ProxyServer.getInstance().playSoldier(input));
		} catch (ServerException e) {
			e.printStackTrace();
		}
	}
	
	public void notifyListeners(){
		for(ModelControllerListener M: listeners){
			M.ModelChanged();
		}
	}
	
	public void addListener(ModelControllerListener e){
		listeners.add(e);
	}

	public interface ModelControllerListener {
		
		public void ModelChanged();

	}
	
	public void setJGC(JoinGameController x){
		JGC = x;
	}
	
	public void reset(){
		poller = null;
		JGC.start();
	}
	
}
