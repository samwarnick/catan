package client.proxy;

import java.util.List;

import server.*;
import shared.communication.input.*;
import shared.communication.input.move.*;
import shared.model.GameModel;

public class ProxyServer implements IServer {

	@Override
	public String loginUser(UserLoginInput input) throws ServerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String registerUser(UserRegisterInput input) throws ServerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<GameModel> listGames(GamesListInput input)
			throws ServerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GameModel createGame(GamesCreateInput input) throws ServerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String joinGame(GamesJoinInput input) throws ServerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean saveGame(GamesSaveInput input) throws ServerException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public GameModel loadGame(GamesLoadInput input) throws ServerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GameModel getGameModelVersion(GameModelVersionInput input)
			throws ServerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GameModel resetGame(GameResetInput input) throws ServerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getGameCommands(GameCommandsGetInput input)
			throws ServerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GameModel postGameCommands(GameCommandsPostInput input)
			throws ServerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean changeLogLevel(UtilChangeLogLevelInput input)
			throws ServerException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public GameModel sendChat(SendChatInput input) throws ServerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GameModel acceptTrade(AcceptTradeInput input) throws ServerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GameModel discardCards(DiscardCardsInput input)
			throws ServerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GameModel rollNumber(RollNumberInput input) throws ServerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GameModel buildRoad(BuildRoadInput input) throws ServerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GameModel buildSettlement(BuildSettlementInput input)
			throws ServerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GameModel buildCity(BuildCityInput input) throws ServerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GameModel offerTrade(OfferTradeInput input) throws ServerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GameModel maritimeTrade(MaritimeTradeInput input)
			throws ServerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GameModel robPlayer(RobPlayerInput input) throws ServerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GameModel finishTurn(FinishTurnInput input) throws ServerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GameModel buyDevCard(BuyDevCardInput input) throws ServerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GameModel playSoldier(PlaySoldierInput input) throws ServerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GameModel playYearOfPlenty(PlayYearOfPlentyInput input)
			throws ServerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GameModel playRoadBuilding(PlayRoadBuildingInput input)
			throws ServerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GameModel playMonopoly(PlayMonopolyInput input)
			throws ServerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GameModel playMonument(PlayMonumentInput input)
			throws ServerException {
		// TODO Auto-generated method stub
		return null;
	}

	

}