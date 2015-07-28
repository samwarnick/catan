package client.proxy;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import client.data.GameInfo;

import com.fasterxml.jackson.databind.JsonNode;

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
		ArrayList<GameInfo> games = (ArrayList<GameInfo>) clientCommunicator.post(input, "POST");
		return games;
	}

	@Override
	public GameInfo createGame(GamesCreateInput input) throws ServerException {
		return (GameInfo) clientCommunicator.post(input, "POST");
	}

	@Override
	public boolean joinGame(GamesJoinInput input) throws ServerException {
		clientCommunicator.post(input, "POST");
		return true;
	}

	@Override
	public GameModel getGameModelVersion(GameModelVersionInput input)
			throws ServerException {
		return (GameModel) clientCommunicator.post(input, "POST");
	}

	@Override
	public GameModel sendChat(SendChatInput input) throws ServerException {
		return (GameModel) clientCommunicator.post(input, "POST");
	}

	@Override
	public GameModel acceptTrade(AcceptTradeInput input) throws ServerException {
		return (GameModel) clientCommunicator.post(input, "POST");
	}

	@Override
	public GameModel discardCards(DiscardCardsInput input)
			throws ServerException {
		return (GameModel) clientCommunicator.post(input, "POST");
	}

	@Override
	public GameModel rollNumber(RollNumberInput input) throws ServerException {
		return (GameModel) clientCommunicator.post(input, "POST");
	}

	@Override
	public GameModel buildRoad(BuildRoadInput input) throws ServerException {
		return (GameModel) clientCommunicator.post(input, "POST");
	}

	@Override
	public GameModel buildSettlement(BuildSettlementInput input)
			throws ServerException {
		return (GameModel) clientCommunicator.post(input, "POST");
	}

	@Override
	public GameModel buildCity(BuildCityInput input) throws ServerException {
		return (GameModel) clientCommunicator.post(input, "POST");
	}

	@Override
	public GameModel offerTrade(OfferTradeInput input) throws ServerException {
		return (GameModel) clientCommunicator.post(input, "POST");
	}

	@Override
	public GameModel maritimeTrade(MaritimeTradeInput input)
			throws ServerException {
		return (GameModel) clientCommunicator.post(input, "POST");
	}

	@Override
	public GameModel robPlayer(RobPlayerInput input) throws ServerException {
		return (GameModel) clientCommunicator.post(input, "POST");
	}

	@Override
	public GameModel finishTurn(FinishTurnInput input) throws ServerException {
		return (GameModel) clientCommunicator.post(input, "POST");
	}

	@Override
	public GameModel buyDevCard(BuyDevCardInput input) throws ServerException {
		return (GameModel) clientCommunicator.post(input, "POST");
	}

	@Override
	public GameModel playSoldier(PlaySoldierInput input) throws ServerException {
		return (GameModel) clientCommunicator.post(input, "POST");
	}

	@Override
	public GameModel playYearOfPlenty(PlayYearOfPlentyInput input)
			throws ServerException {
		return (GameModel) clientCommunicator.post(input, "POST");
	}

	@Override
	public GameModel playRoadBuilding(PlayRoadBuildingInput input)
			throws ServerException {
		return (GameModel) clientCommunicator.post(input, "POST");
	}

	@Override
	public GameModel playMonopoly(PlayMonopolyInput input)
			throws ServerException {
		return (GameModel) clientCommunicator.post(input, "POST");
	}

	@Override
	public GameModel playMonument(PlayMonumentInput input)
			throws ServerException {
		return (GameModel) clientCommunicator.post(input, "POST");
	}
	
	public int getPlayerId(){
		return clientCommunicator.getPlayerId();
	}
	
	public int getGameId(){
		return clientCommunicator.getGameId();
	}
}