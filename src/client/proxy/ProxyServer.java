package client.proxy;

import java.util.List;

import server.*;
import shared.communication.input.*;
import shared.communication.input.move.*;
import shared.model.GameModel;
import shared.model.JsonParser;

public class ProxyServer implements IServer {

	ClientCommunicator clientCommunicator;
	
	public ProxyServer(ClientCommunicator clientCommunicator) {
		super();
		this.clientCommunicator = clientCommunicator;
	}

	@Override
	public String loginUser(UserLoginInput input) throws ServerException {
		String toReturn = (String) clientCommunicator.post(input);
		return toReturn;
	}

	@Override
	public String registerUser(UserRegisterInput input) throws ServerException {
		String toReturn = (String) clientCommunicator.post(input);
		return toReturn;
	}

	@Override
	public List<GameModel> listGames(GamesListInput input)
			throws ServerException {
		@SuppressWarnings("unchecked")
		List<GameModel> toReturn = (List<GameModel>) clientCommunicator.post(input);
		return toReturn;
	}

	@Override
	public GameModel createGame(GamesCreateInput input) throws ServerException {
		GameModel toReturn = (GameModel) clientCommunicator.post(input);
		return toReturn;
	}

	@Override
	public String joinGame(GamesJoinInput input) throws ServerException {
		String toReturn = (String) clientCommunicator.post(input);
		return toReturn;
	}

	@Override
	public boolean saveGame(GamesSaveInput input) throws ServerException {
		boolean toReturn = (Boolean) clientCommunicator.post(input);
		return toReturn;
	}

	@Override
	public GameModel loadGame(GamesLoadInput input) throws ServerException {
		GameModel toReturn = (GameModel) clientCommunicator.post(input);
		return toReturn;
	}

	@Override
	public GameModel getGameModelVersion(GameModelVersionInput input)
			throws ServerException {
		GameModel toReturn = (GameModel) clientCommunicator.post(input);
		return toReturn;
	}

	@Override
	public GameModel resetGame(GameResetInput input) throws ServerException {
		GameModel toReturn = (GameModel) clientCommunicator.post(input);
		return toReturn;
	}

	@Override
	public List<String> getGameCommands(GameCommandsGetInput input)
			throws ServerException {
		@SuppressWarnings("unchecked")
		List<String> toReturn = (List<String>) clientCommunicator.post(input);
		return toReturn;
	}

	@Override
	public GameModel postGameCommands(GameCommandsPostInput input)
			throws ServerException {
		GameModel toReturn = (GameModel) clientCommunicator.post(input);
		return toReturn;
	}

	@Override
	public boolean changeLogLevel(UtilChangeLogLevelInput input)
			throws ServerException {
		boolean toReturn = (Boolean) clientCommunicator.post(input);
		return toReturn;
	}

	@Override
	public GameModel sendChat(SendChatInput input) throws ServerException {
		GameModel toReturn = (GameModel) clientCommunicator.post(input);
		return toReturn;
	}

	@Override
	public GameModel acceptTrade(AcceptTradeInput input) throws ServerException {
		GameModel toReturn = (GameModel) clientCommunicator.post(input);
		return toReturn;
	}

	@Override
	public GameModel discardCards(DiscardCardsInput input)
			throws ServerException {
		GameModel toReturn = (GameModel) clientCommunicator.post(input);
		return toReturn;
	}

	@Override
	public GameModel rollNumber(RollNumberInput input) throws ServerException {
		GameModel toReturn = (GameModel) clientCommunicator.post(input);
		return toReturn;
	}

	@Override
	public GameModel buildRoad(BuildRoadInput input) throws ServerException {
		GameModel toReturn = (GameModel) clientCommunicator.post(input);
		return toReturn;
	}

	@Override
	public GameModel buildSettlement(BuildSettlementInput input)
			throws ServerException {
		GameModel toReturn = (GameModel) clientCommunicator.post(input);
		return toReturn;
	}

	@Override
	public GameModel buildCity(BuildCityInput input) throws ServerException {
		GameModel toReturn = (GameModel) clientCommunicator.post(input);
		return toReturn;
	}

	@Override
	public GameModel offerTrade(OfferTradeInput input) throws ServerException {
		GameModel toReturn = (GameModel) clientCommunicator.post(input);
		return toReturn;
	}

	@Override
	public GameModel maritimeTrade(MaritimeTradeInput input)
			throws ServerException {
		GameModel toReturn = (GameModel) clientCommunicator.post(input);
		return toReturn;
	}

	@Override
	public GameModel robPlayer(RobPlayerInput input) throws ServerException {
		GameModel toReturn = (GameModel) clientCommunicator.post(input);
		return toReturn;
	}

	@Override
	public GameModel finishTurn(FinishTurnInput input) throws ServerException {
		GameModel toReturn = (GameModel) clientCommunicator.post(input);
		return toReturn;
	}

	@Override
	public GameModel buyDevCard(BuyDevCardInput input) throws ServerException {
		GameModel toReturn = (GameModel) clientCommunicator.post(input);
		return toReturn;
	}

	@Override
	public GameModel playSoldier(PlaySoldierInput input) throws ServerException {
		GameModel toReturn = (GameModel) clientCommunicator.post(input);
		return toReturn;
	}

	@Override
	public GameModel playYearOfPlenty(PlayYearOfPlentyInput input)
			throws ServerException {
		GameModel toReturn = (GameModel) clientCommunicator.post(input);
		return toReturn;
	}

	@Override
	public GameModel playRoadBuilding(PlayRoadBuildingInput input)
			throws ServerException {
		GameModel toReturn = (GameModel) clientCommunicator.post(input);
		return toReturn;
	}

	@Override
	public GameModel playMonopoly(PlayMonopolyInput input)
			throws ServerException {
		GameModel toReturn = (GameModel) clientCommunicator.post(input);
		return toReturn;
	}

	@Override
	public GameModel playMonument(PlayMonumentInput input)
			throws ServerException {
		return JsonParser.gameModelFromJson(clientCommunicator.post(input));
	}

	

}