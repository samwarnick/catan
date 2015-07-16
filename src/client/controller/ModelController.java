package client.controller;

import java.util.ArrayList;
import java.util.List;

import client.poller.Poller;
import client.proxy.ProxyServer;
import server.ServerException;
import shared.communication.input.*;
import shared.communication.input.move.*;
import shared.definitions.ResourceType;
import shared.model.GameModel;
import shared.model.GameModelFacade;
import shared.model.TooManyPlayersException;
import shared.model.bank.ResourceHand;
import shared.model.board.Board;
import shared.model.board.PlayerID;
import shared.model.player.Player;

public class ModelController {

	private GameModelFacade gameModelFacade;
	private Poller poller;

	private int PlayerID;
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
		poller = new Poller(this);
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
		gameModelFacade.setGameModel(gameModel);
		notifyListeners();
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
		if (gameModelFacade.getGameModel().getPlayers()!= null){
			return gameModelFacade.getGameModel().getPlayer(new PlayerID(PlayerID));

		}
		else
			return null;
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
	
//	@Override
//	public GameModel getGameModelVersion(GameModelVersionInput input)
//			throws ServerException {
//		return JsonParser.gameModelFromJson(clientCommunicator.post(input, "GET"));
//	}
//
//	 
//	public GameModel resetGame(GameResetInput input) throws ServerException {
//		return JsonParser.gameModelFromJson(clientCommunicator.post(input, "POST"));
//	}
//
//	 
//	public List<String> getGameCommands(GameCommandsGetInput input)
//			throws ServerException {
//		// TODO
//		return null;
//	}
//
//	 
//	public GameModel postGameCommands(GameCommandsPostInput input)
//			throws ServerException {
//		return JsonParser.gameModelFromJson(clientCommunicator.post(input, "POST"));
//	}
//
//	 
//	public boolean changeLogLevel(UtilChangeLogLevelInput input)
//			throws ServerException {
//		clientCommunicator.post(input, "POST");
//		return true;
//	}
//
	public void sendChat(SendChatInput input){
		try {
			updateGame(ProxyServer.getInstance().sendChat(input));
		} catch (ServerException e) {
			e.printStackTrace();
		}
	}
//
//	 
//	public GameModel acceptTrade(AcceptTradeInput input) throws ServerException {
//		return JsonParser.gameModelFromJson(clientCommunicator.post(input, "POST"));
//	}
//
//	 
//	public GameModel discardCards(DiscardCardsInput input)
//			throws ServerException {
//		return JsonParser.gameModelFromJson(clientCommunicator.post(input, "POST"));
//	}
//
//	 
//	public GameModel rollNumber(RollNumberInput input) throws ServerException {
//		return JsonParser.gameModelFromJson(clientCommunicator.post(input, "POST"));
//	}
//
//	 
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
//
//	 
//	public GameModel offerTrade(OfferTradeInput input) throws ServerException {
//		return JsonParser.gameModelFromJson(clientCommunicator.post(input, "POST"));
//	}
//
//
//	 
//	public GameModel robPlayer(RobPlayerInput input) throws ServerException {
//		return JsonParser.gameModelFromJson(clientCommunicator.post(input, "POST"));
//	}
//
//	 
//	public GameModel finishTurn(FinishTurnInput input) throws ServerException {
//		return JsonParser.gameModelFromJson(clientCommunicator.post(input, "POST"));
//	}
//
	public void buyDevCard() {
		BuyDevCardInput input = new BuyDevCardInput(PlayerID);
		try {
			updateGame(ProxyServer.getInstance().buyDevCard(input));
		} catch (ServerException e) {
			e.printStackTrace();
		}
	}
//	 
//	public GameModel buyDevCard(BuyDevCardInput input) throws ServerException {
//		return JsonParser.gameModelFromJson(clientCommunicator.post(input, "POST"));
//	}
//
//	 
//	public GameModel playSoldier(PlaySoldierInput input) throws ServerException {
//		return JsonParser.gameModelFromJson(clientCommunicator.post(input, "POST"));
//	}
//
	public void playYearOfPlenty(ResourceType resource1, ResourceType resource2) {
		PlayYearOfPlentyInput input = new PlayYearOfPlentyInput(PlayerID, resource1, resource2);
		try {
			updateGame(ProxyServer.getInstance().playYearOfPlenty(input));
		} catch (ServerException e) {
			e.printStackTrace();
		}
	}
//
//	 
//	public GameModel playRoadBuilding(PlayRoadBuildingInput input)
//			throws ServerException {
//		return JsonParser.gameModelFromJson(clientCommunicator.post(input, "POST"));
//	}
//
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
//	
//	public int getPlayerId(){
//		return clientCommunicator.getPlayerId();
//	}
//	
//	public int getGameId(){
//		return clientCommunicator.getGameId();
//	}
	
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
