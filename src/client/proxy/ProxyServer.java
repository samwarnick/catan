package client.proxy;

import java.util.List;

import client.data.GameInfo;

import com.fasterxml.jackson.databind.JsonNode;

import server.*;
import shared.communication.input.*;
import shared.communication.input.move.*;
import shared.model.Game;
import shared.model.GameModel;
import shared.model.JsonParser;

public class ProxyServer implements IServer {

	private static ClientCommunicator clientCommunicator = null;
	private static ProxyServer instance = null;
	
	private ProxyServer(ClientCommunicator clientCommunicator) {
		super();
		ProxyServer.clientCommunicator = clientCommunicator;
	}
	
	public static ProxyServer getInstance() {
		if (instance == null) {
			clientCommunicator = new ClientCommunicator();
			instance = new ProxyServer(clientCommunicator);
		}
		return instance;
	}
	
	public static ProxyServer getInstance(String host, int port) {
		if (instance == null) {
			clientCommunicator = new ClientCommunicator(host, port);
			instance = new ProxyServer(clientCommunicator);
		}
		return instance;
	}

	@Override
	public boolean loginUser(UserLoginInput input) throws ServerException {
		clientCommunicator.post(input, "POST");
		return true;
	}

	@Override
	public boolean registerUser(UserRegisterInput input) throws ServerException {
		clientCommunicator.post(input, "POST");
		return true;
	}

	@Override
	public List<GameInfo> listGames(GamesListInput input)
			throws ServerException {
		
		return JsonParser.gamesFromJson(clientCommunicator.post(input, "GET"));
		 
	}

	@Override
	public GameInfo createGame(GamesCreateInput input) throws ServerException {
		List<GameInfo> games = JsonParser.gamesFromJson(clientCommunicator.post(input, "POST"));
		return games.get(0);
	}

	@Override
	public boolean joinGame(GamesJoinInput input) throws ServerException {
		clientCommunicator.post(input, "POST");
		return true;
	}

	@Override
	public boolean saveGame(GamesSaveInput input) throws ServerException {
		clientCommunicator.post(input, "POST");
		return true;
	}

	@Override
	public GameModel loadGame(GamesLoadInput input) throws ServerException {
		return JsonParser.gameModelFromJson(clientCommunicator.post(input, "POST"));
	}

	@Override
	public GameModel getGameModelVersion(GameModelVersionInput input)
			throws ServerException {
		return JsonParser.gameModelFromJson(clientCommunicator.post(input, "GET"));
	}

	@Override
	public GameModel resetGame(GameResetInput input) throws ServerException {
		return JsonParser.gameModelFromJson(clientCommunicator.post(input, "POST"));
	}

	@Override
	public List<String> getGameCommands(GameCommandsGetInput input)
			throws ServerException {
		@SuppressWarnings("unchecked")
		JsonNode toReturn = clientCommunicator.post(input, "GET");
		// TODO
		return null;
	}

	@Override
	public GameModel postGameCommands(GameCommandsPostInput input)
			throws ServerException {
		return JsonParser.gameModelFromJson(clientCommunicator.post(input, "POST"));
	}

	@Override
	public boolean changeLogLevel(UtilChangeLogLevelInput input)
			throws ServerException {
		clientCommunicator.post(input, "POST");
		return true;
	}

	@Override
	public GameModel sendChat(SendChatInput input) throws ServerException {
		return JsonParser.gameModelFromJson(clientCommunicator.post(input, "POST"));
	}

	@Override
	public GameModel acceptTrade(AcceptTradeInput input) throws ServerException {
		return JsonParser.gameModelFromJson(clientCommunicator.post(input, "POST"));
	}

	@Override
	public GameModel discardCards(DiscardCardsInput input)
			throws ServerException {
		return JsonParser.gameModelFromJson(clientCommunicator.post(input, "POST"));
	}

	@Override
	public GameModel rollNumber(RollNumberInput input) throws ServerException {
		return JsonParser.gameModelFromJson(clientCommunicator.post(input, "POST"));
	}

	@Override
	public GameModel buildRoad(BuildRoadInput input) throws ServerException {
		return JsonParser.gameModelFromJson(clientCommunicator.post(input, "POST"));
	}

	@Override
	public GameModel buildSettlement(BuildSettlementInput input)
			throws ServerException {
		return JsonParser.gameModelFromJson(clientCommunicator.post(input, "POST"));
	}

	@Override
	public GameModel buildCity(BuildCityInput input) throws ServerException {
		return JsonParser.gameModelFromJson(clientCommunicator.post(input, "POST"));
	}

	@Override
	public GameModel offerTrade(OfferTradeInput input) throws ServerException {
		return JsonParser.gameModelFromJson(clientCommunicator.post(input, "POST"));
	}

	@Override
	public GameModel maritimeTrade(MaritimeTradeInput input)
			throws ServerException {
		return JsonParser.gameModelFromJson(clientCommunicator.post(input, "POST"));
	}

	@Override
	public GameModel robPlayer(RobPlayerInput input) throws ServerException {
		return JsonParser.gameModelFromJson(clientCommunicator.post(input, "POST"));
	}

	@Override
	public GameModel finishTurn(FinishTurnInput input) throws ServerException {
		return JsonParser.gameModelFromJson(clientCommunicator.post(input, "POST"));
	}

	@Override
	public GameModel buyDevCard(BuyDevCardInput input) throws ServerException {
		return JsonParser.gameModelFromJson(clientCommunicator.post(input, "POST"));
	}

	@Override
	public GameModel playSoldier(PlaySoldierInput input) throws ServerException {
		return JsonParser.gameModelFromJson(clientCommunicator.post(input, "POST"));
	}

	@Override
	public GameModel playYearOfPlenty(PlayYearOfPlentyInput input)
			throws ServerException {
		return JsonParser.gameModelFromJson(clientCommunicator.post(input, "POST"));
	}

	@Override
	public GameModel playRoadBuilding(PlayRoadBuildingInput input)
			throws ServerException {
		return JsonParser.gameModelFromJson(clientCommunicator.post(input, "POST"));
	}

	@Override
	public GameModel playMonopoly(PlayMonopolyInput input)
			throws ServerException {
		return JsonParser.gameModelFromJson(clientCommunicator.post(input, "POST"));
	}

	@Override
	public GameModel playMonument(PlayMonumentInput input)
			throws ServerException {
		return JsonParser.gameModelFromJson(clientCommunicator.post(input, "POST"));
	}

	

}