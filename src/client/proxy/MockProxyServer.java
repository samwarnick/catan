package client.proxy;

import java.io.File;
import java.util.List;

import client.data.GameInfo;

import com.fasterxml.jackson.databind.JsonNode;

import server.IServerFacade;
import server.ServerException;
import shared.communication.input.*;
import shared.communication.input.move.*;
import shared.model.GameModel;
import shared.model.JsonParser;

public class MockProxyServer implements IServerFacade {

	private static MockProxyServer instance = null;
	
	public static MockProxyServer getInstance() {
		if (instance == null) {
			instance = new MockProxyServer();
		}
		return instance;
	}
	
	@Override
	public boolean loginUser(UserLoginInput input) throws ServerException {
		return true;
	}

	@Override
	public boolean registerUser(UserRegisterInput input) throws ServerException {
		return true;
	}

	@Override
	public List<GameInfo> listGames(GamesListInput input)
			throws ServerException {
		return null;
	}

	@Override
	public GameInfo createGame(GamesCreateInput input) throws ServerException {
		return null;
	}

	@Override
	public boolean joinGame(GamesJoinInput input) throws ServerException {
		return true;
	}

	@Override
	public GameModel getGameModelVersion(GameModelVersionInput input)
			throws ServerException {
		JsonNode root = JsonParser.nodeFromFile(new File("model.json"));
		GameModel model = JsonParser.gameModelFromJson(root);
		return model;
	}

	@Override
	public GameModel sendChat(SendChatInput input) throws ServerException {
		return null;
	}

	@Override
	public GameModel acceptTrade(AcceptTradeInput input) throws ServerException {
		return null;
	}

	@Override
	public GameModel discardCards(DiscardCardsInput input)
			throws ServerException {
		return null;
	}

	@Override
	public GameModel rollNumber(RollNumberInput input) throws ServerException {
		return null;
	}

	@Override
	public GameModel buildRoad(BuildRoadInput input) throws ServerException {
		return null;
	}

	@Override
	public GameModel buildSettlement(BuildSettlementInput input)
			throws ServerException {
		return null;
	}

	@Override
	public GameModel buildCity(BuildCityInput input) throws ServerException {
		return null;
	}

	@Override
	public GameModel offerTrade(OfferTradeInput input) throws ServerException {
		return null;
	}

	@Override
	public GameModel maritimeTrade(MaritimeTradeInput input)
			throws ServerException {
		return null;
	}

	@Override
	public GameModel robPlayer(RobPlayerInput input) throws ServerException {
		return null;
	}

	@Override
	public GameModel finishTurn(FinishTurnInput input) throws ServerException {
		return null;
	}

	@Override
	public GameModel buyDevCard(BuyDevCardInput input) throws ServerException {
		return null;
	}

	@Override
	public GameModel playSoldier(PlaySoldierInput input) throws ServerException {
		return null;
	}

	@Override
	public GameModel playYearOfPlenty(PlayYearOfPlentyInput input)
			throws ServerException {
		return null;
	}

	@Override
	public GameModel playRoadBuilding(PlayRoadBuildingInput input)
			throws ServerException {
		return null;
	}

	@Override
	public GameModel playMonopoly(PlayMonopolyInput input)
			throws ServerException {
		return null;
	}

	@Override
	public GameModel playMonument(PlayMonumentInput input)
			throws ServerException {
		return null;
	}

}
