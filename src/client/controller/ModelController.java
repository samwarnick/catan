package client.controller;

import java.util.ArrayList;
import java.util.List;

import client.login.LoginController;
import client.maritime.MaritimeTradeController;
import client.maritime.MaritimeTradeView;
import client.poller.Poller;
import client.proxy.ProxyServer;
import server.IServer;
import server.ServerException;
import shared.communication.input.GameCommandsGetInput;
import shared.communication.input.GameCommandsPostInput;
import shared.communication.input.GameModelVersionInput;
import shared.communication.input.GameResetInput;
import shared.communication.input.UtilChangeLogLevelInput;
import shared.communication.input.move.AcceptTradeInput;
import shared.communication.input.move.BuildCityInput;
import shared.communication.input.move.BuildRoadInput;
import shared.communication.input.move.BuildSettlementInput;
import shared.communication.input.move.BuyDevCardInput;
import shared.communication.input.move.DiscardCardsInput;
import shared.communication.input.move.FinishTurnInput;
import shared.communication.input.move.MaritimeTradeInput;
import shared.communication.input.move.OfferTradeInput;
import shared.communication.input.move.PlayMonopolyInput;
import shared.communication.input.move.PlayMonumentInput;
import shared.communication.input.move.PlayRoadBuildingInput;
import shared.communication.input.move.PlaySoldierInput;
import shared.communication.input.move.PlayYearOfPlentyInput;
import shared.communication.input.move.RobPlayerInput;
import shared.communication.input.move.RollNumberInput;
import shared.communication.input.move.SendChatInput;
import shared.model.GameModel;
import shared.model.GameModelFacade;
import shared.model.JsonParser;
import shared.model.TooManyPlayersException;
import shared.model.bank.ResourceHand;
import shared.model.board.Board;
import shared.model.board.PlayerID;
import shared.model.player.Player;

public class ModelController {

	private MaritimeTradeController maritimeController;
	private GameModelFacade gameModelFacade;
	private Poller poller;

	private int PlayerID;
	private static ModelController instance = null;
	private boolean testing = false;
	
	private List<ModelControllerListener> listeners = new ArrayList<ModelControllerListener>();


	
	public static ModelController getInstance() {
		if (instance == null) {
			// TODO
			instance = new ModelController(0);
		}
		return instance;
	}
	
	public static ModelController getInstance(int x) {
		if (instance == null) {
			// TODO
			instance = new ModelController(x);
		}
		return instance;
	}
	
	private ModelController(int gameID){
		gameModelFacade = GameModelFacade.getInstance(gameID);
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
		maritimeController.getTradeView().enableMaritimeTrade(gameModelFacade.canFinishTurn(gameModelFacade.getGameModel().getPlayer(new PlayerID(PlayerID))));
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
	
	public void maritimeTrade(MaritimeTradeInput input){
		try {
			updateGame(ProxyServer.getInstance().maritimeTrade(input));
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
//	@Override
//	public GameModel resetGame(GameResetInput input) throws ServerException {
//		return JsonParser.gameModelFromJson(clientCommunicator.post(input, "POST"));
//	}
//
//	@Override
//	public List<String> getGameCommands(GameCommandsGetInput input)
//			throws ServerException {
//		// TODO
//		return null;
//	}
//
//	@Override
//	public GameModel postGameCommands(GameCommandsPostInput input)
//			throws ServerException {
//		return JsonParser.gameModelFromJson(clientCommunicator.post(input, "POST"));
//	}
//
//	@Override
//	public boolean changeLogLevel(UtilChangeLogLevelInput input)
//			throws ServerException {
//		clientCommunicator.post(input, "POST");
//		return true;
//	}
//
//	@Override
//	public GameModel sendChat(SendChatInput input) throws ServerException {
//		return JsonParser.gameModelFromJson(clientCommunicator.post(input, "POST"));
//	}
//
//	@Override
//	public GameModel acceptTrade(AcceptTradeInput input) throws ServerException {
//		return JsonParser.gameModelFromJson(clientCommunicator.post(input, "POST"));
//	}
//
//	@Override
//	public GameModel discardCards(DiscardCardsInput input)
//			throws ServerException {
//		return JsonParser.gameModelFromJson(clientCommunicator.post(input, "POST"));
//	}
//
//	@Override
//	public GameModel rollNumber(RollNumberInput input) throws ServerException {
//		return JsonParser.gameModelFromJson(clientCommunicator.post(input, "POST"));
//	}
//
//	@Override
//	public GameModel buildRoad(BuildRoadInput input) throws ServerException {
//		return JsonParser.gameModelFromJson(clientCommunicator.post(input, "POST"));
//	}
//
//	@Override
//	public GameModel buildSettlement(BuildSettlementInput input)
//			throws ServerException {
//		return JsonParser.gameModelFromJson(clientCommunicator.post(input, "POST"));
//	}
//
//	@Override
//	public GameModel buildCity(BuildCityInput input) throws ServerException {
//		return JsonParser.gameModelFromJson(clientCommunicator.post(input, "POST"));
//	}
//
//	@Override
//	public GameModel offerTrade(OfferTradeInput input) throws ServerException {
//		return JsonParser.gameModelFromJson(clientCommunicator.post(input, "POST"));
//	}
//
//	@Override
//	public GameModel maritimeTrade(MaritimeTradeInput input)
//			throws ServerException {
//		return JsonParser.gameModelFromJson(clientCommunicator.post(input, "POST"));
//	}
//
//	@Override
//	public GameModel robPlayer(RobPlayerInput input) throws ServerException {
//		return JsonParser.gameModelFromJson(clientCommunicator.post(input, "POST"));
//	}
//
//	@Override
//	public GameModel finishTurn(FinishTurnInput input) throws ServerException {
//		return JsonParser.gameModelFromJson(clientCommunicator.post(input, "POST"));
//	}
//
//	@Override
//	public GameModel buyDevCard(BuyDevCardInput input) throws ServerException {
//		return JsonParser.gameModelFromJson(clientCommunicator.post(input, "POST"));
//	}
//
//	@Override
//	public GameModel playSoldier(PlaySoldierInput input) throws ServerException {
//		return JsonParser.gameModelFromJson(clientCommunicator.post(input, "POST"));
//	}
//
//	@Override
//	public GameModel playYearOfPlenty(PlayYearOfPlentyInput input)
//			throws ServerException {
//		return JsonParser.gameModelFromJson(clientCommunicator.post(input, "POST"));
//	}
//
//	@Override
//	public GameModel playRoadBuilding(PlayRoadBuildingInput input)
//			throws ServerException {
//		return JsonParser.gameModelFromJson(clientCommunicator.post(input, "POST"));
//	}
//
//	@Override
//	public GameModel playMonopoly(PlayMonopolyInput input)
//			throws ServerException {
//		return JsonParser.gameModelFromJson(clientCommunicator.post(input, "POST"));
//	}
//
//	@Override
//	public GameModel playMonument(PlayMonumentInput input)
//			throws ServerException {
//		return JsonParser.gameModelFromJson(clientCommunicator.post(input, "POST"));
//	}
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
