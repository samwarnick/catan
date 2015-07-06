package client.proxy;

import java.util.List;

import server.IServer;
import server.ServerException;
import shared.communication.input.GameCommandsGetInput;
import shared.communication.input.GameCommandsPostInput;
import shared.communication.input.GameModelVersionInput;
import shared.communication.input.GameResetInput;
import shared.communication.input.GamesCreateInput;
import shared.communication.input.GamesJoinInput;
import shared.communication.input.GamesListInput;
import shared.communication.input.GamesLoadInput;
import shared.communication.input.GamesSaveInput;
import shared.communication.input.UserLoginInput;
import shared.communication.input.UserRegisterInput;
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

public class MockProxyServer implements IServer {

	private GameModel model;
	
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
