package client.proxy;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import client.data.GameInfo;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import server.*;
import shared.communication.input.*;
import shared.communication.input.move.*;
import shared.model.GameModel;
import shared.model.JsonParser;

public class ProxyServer implements IServerFacade {

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
	public ArrayList<GameInfo> listGames(GamesListInput input)
			throws ServerException {
		String json = clientCommunicator.post(input, "POST");
		ArrayList<GameInfo> games = new Gson().fromJson(json, new TypeToken<ArrayList<GameInfo>>(){}.getType());
		return games;
	}

	@Override
	public GameInfo createGame(GamesCreateInput input) throws ServerException {
		String json = clientCommunicator.post(input, "POST");
		GameInfo info = new Gson().fromJson(json, GameInfo.class);
		return info;
	}

	@Override
	public boolean joinGame(GamesJoinInput input) throws ServerException {
		clientCommunicator.post(input, "POST");
		return true;
	}

	@Override
	public GameModel getGameModelVersion(GameModelVersionInput input)
			throws ServerException {
		String json = clientCommunicator.post(input, "POST");
		GameModel model = new Gson().fromJson(json, GameModel.class);
		return model;
	}

	@Override
	public GameModel sendChat(SendChatInput input) throws ServerException {
		String json = clientCommunicator.post(input, "POST");
		GameModel model = new Gson().fromJson(json, GameModel.class);
		return model;
	}

	@Override
	public GameModel acceptTrade(AcceptTradeInput input) throws ServerException {
		String json = clientCommunicator.post(input, "POST");
		GameModel model = new Gson().fromJson(json, GameModel.class);
		return model;
	}

	@Override
	public GameModel discardCards(DiscardCardsInput input)
			throws ServerException {
		String json = clientCommunicator.post(input, "POST");
		GameModel model = new Gson().fromJson(json, GameModel.class);
		return model;
	}

	@Override
	public GameModel rollNumber(RollNumberInput input) throws ServerException {
		String json = clientCommunicator.post(input, "POST");
		GameModel model = new Gson().fromJson(json, GameModel.class);
		return model;
	}

	@Override
	public GameModel buildRoad(BuildRoadInput input) throws ServerException {
		String json = clientCommunicator.post(input, "POST");
		GameModel model = new Gson().fromJson(json, GameModel.class);
		return model;
	}

	@Override
	public GameModel buildSettlement(BuildSettlementInput input)
			throws ServerException {
		String json = clientCommunicator.post(input, "POST");
		GameModel model = new Gson().fromJson(json, GameModel.class);
		return model;
	}

	@Override
	public GameModel buildCity(BuildCityInput input) throws ServerException {
		String json = clientCommunicator.post(input, "POST");
		GameModel model = new Gson().fromJson(json, GameModel.class);
		return model;
	}

	@Override
	public GameModel offerTrade(OfferTradeInput input) throws ServerException {
		String json = clientCommunicator.post(input, "POST");
		GameModel model = new Gson().fromJson(json, GameModel.class);
		return model;
	}

	@Override
	public GameModel maritimeTrade(MaritimeTradeInput input)
			throws ServerException {
		String json = clientCommunicator.post(input, "POST");
		GameModel model = new Gson().fromJson(json, GameModel.class);
		return model;
	}

	@Override
	public GameModel robPlayer(RobPlayerInput input) throws ServerException {
		String json = clientCommunicator.post(input, "POST");
		GameModel model = new Gson().fromJson(json, GameModel.class);
		return model;
	}

	@Override
	public GameModel finishTurn(FinishTurnInput input) throws ServerException {
		String json = clientCommunicator.post(input, "POST");
		GameModel model = new Gson().fromJson(json, GameModel.class);
		return model;
	}

	@Override
	public GameModel buyDevCard(BuyDevCardInput input) throws ServerException {
		String json = clientCommunicator.post(input, "POST");
		GameModel model = new Gson().fromJson(json, GameModel.class);
		return model;
	}

	@Override
	public GameModel playSoldier(PlaySoldierInput input) throws ServerException {
		String json = clientCommunicator.post(input, "POST");
		GameModel model = new Gson().fromJson(json, GameModel.class);
		return model;
	}

	@Override
	public GameModel playYearOfPlenty(PlayYearOfPlentyInput input)
			throws ServerException {
		String json = clientCommunicator.post(input, "POST");
		GameModel model = new Gson().fromJson(json, GameModel.class);
		return model;
	}

	@Override
	public GameModel playRoadBuilding(PlayRoadBuildingInput input)
			throws ServerException {
		String json = clientCommunicator.post(input, "POST");
		GameModel model = new Gson().fromJson(json, GameModel.class);
		return model;
	}

	@Override
	public GameModel playMonopoly(PlayMonopolyInput input)
			throws ServerException {
		String json = clientCommunicator.post(input, "POST");
		GameModel model = new Gson().fromJson(json, GameModel.class);
		return model;
	}

	@Override
	public GameModel playMonument(PlayMonumentInput input)
			throws ServerException {
		String json = clientCommunicator.post(input, "POST");
		GameModel model = new Gson().fromJson(json, GameModel.class);
		return model;
	}
	
	public int getPlayerId(){
		return clientCommunicator.getPlayerId();
	}
	
	public int getGameId(){
		return clientCommunicator.getGameId();
	}
}