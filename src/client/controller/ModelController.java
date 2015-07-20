package client.controller;

import java.util.ArrayList;
import java.util.List;

import client.poller.Poller;
import client.proxy.ProxyServer;
import server.ServerException;
import shared.communication.input.*;
import shared.communication.input.move.*;
import shared.definitions.ResourceType;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.model.GameModel;
import shared.model.GameModelFacade;
import shared.model.TooManyPlayersException;
import shared.model.bank.PlayerBank;
import shared.model.bank.ResourceHand;
import shared.model.board.Board;
import shared.model.board.PlayerID;
import shared.model.player.ActivePlayerFacade;
import shared.model.player.Player;

public class ModelController {

	private GameModelFacade gameModelFacade;
	private Poller poller;
	private Player clientPlayer;

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
			clientPlayer = getClientPlayer();
			PlayerID = clientPlayer.getPlayerID().getPlayerid();
		}
		
		System.out.printf("%s: %d\n", "Soldiers", clientPlayer.getPlayerBank().getNewSoldier().getQuantity());
		System.out.printf("%s: %d\n", "Monuments", clientPlayer.getPlayerBank().getNewMonument().getQuantity());
		System.out.printf("%s: %d\n", "Monopoloy", clientPlayer.getPlayerBank().getNewMonopoly().getQuantity());
		System.out.printf("%s: %d\n", "YearOfPlenty", clientPlayer.getPlayerBank().getNewYearOfPlenty().getQuantity());
		System.out.printf("%s: %d\n", "RoadBuilding", clientPlayer.getPlayerBank().getNewRoadBuild().getQuantity());
		
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
		clientPlayer = GameModelFacade.getInstance().getGameModel().getPlayer(playerName);
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
	
	public void buildRoad(BuildRoadInput input) {
		try {
			updateGame(ProxyServer.getInstance().buildRoad(input));
		} catch (ServerException e) {
			e.printStackTrace();
		}
	}

	 
	public void buildSettlement(BuildSettlementInput input) {
		try {
			updateGame(ProxyServer.getInstance().buildSettlement(input));
		} catch (ServerException e) {
			e.printStackTrace();
		}
	}

	 
	public void buildCity(BuildCityInput input) {
		try  {
			updateGame(ProxyServer.getInstance().buildCity(input));
		} catch (ServerException e) {
			e.printStackTrace();
		}
	}

	public void buyDevCard() {
		System.out.println("Buying Dev Card");
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
	
	public void playSoldier(HexLocation location, int victimIndex) {
		PlaySoldierInput input = new PlaySoldierInput(gameModelFacade.getGameModel().getTurnTracker().getCurrentTurn(), location, victimIndex);
		try {
			updateGame(ProxyServer.getInstance().playSoldier(input));
		} catch (ServerException e) {
			e.printStackTrace();
		}
	}
	
	public void playRoadBuilding(EdgeLocation location1, EdgeLocation location2) {
		PlayRoadBuildingInput input = new PlayRoadBuildingInput(getClientPlayer().getPlayerID().getPlayerid(), 
				location1, location2);
		try {
			updateGame(ProxyServer.getInstance().playRoadBuilding(input));
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
	
}
