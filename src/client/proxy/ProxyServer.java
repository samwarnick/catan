package client.proxy;

import java.util.List;

import server.*;
import shared.communication.input.*;
import shared.model.GameModel;

public class ProxyServer implements IServer {

	@Override
	public String loginUser(UserLoginInput input) throws ServerException {
		return null;
	}

	@Override
	public String registerUser(UserRegisterInput input) throws ServerException {
		return null;
	}

	@Override
	public List<GameModel> listGames(GamesListInput input) throws ServerException {
		return null;
	}

	@Override
	public GameModel createGame(GamesCreateInput input) throws ServerException {
		return null;
	}

	@Override
	public String joinGame(GamesJoinInput input) throws ServerException {
		return null;
	}

	@Override
	public boolean saveGame(GamesSaveInput input) throws ServerException {
		return false;
	}

	@Override
	public GameModel loadGame(GamesLoadInput input) throws ServerException {
		return null;
	}

	@Override
	public GameModel getGameModelVersion(GameModelVersionInput input) throws ServerException {
		return null;
	}

	@Override
	public GameModel resetGame(GameResetInput input) throws ServerException {
		return null;
	}

	@Override
	public List<String> getGameCommands(GameCommandsGetInput input) throws ServerException {
		return null;
	}

	@Override
	public GameModel postGameCommands(GameCommandsPostInput input) throws ServerException {
		return null;
	}

	@Override
	public boolean changeLogLevel(UtilChangeLogLevelInput input) throws ServerException {
		return false;
	}

	@Override
	public GameModel sendChat(MoveSendChat input) throws ServerException {
		return null;
	}

	@Override
	public GameModel acceptTrade(MoveAcceptTrade input) throws ServerException {
		return null;
	}

	@Override
	public GameModel discardCards(MoveDiscardCards input) throws ServerException {
		return null;
	}

	@Override
	public GameModel rollNumber(MoveRollNumber input) throws ServerException {
		return null;
	}

	@Override
	public GameModel buildRoad(MoveBuildRoad input) throws ServerException {
		return null;
	}

	@Override
	public GameModel buildSettlement(MoveBuildSettlement input) throws ServerException {
		return null;
	}

	@Override
	public GameModel buildCity(MoveBuildCity input) throws ServerException {
		return null;
	}

	@Override
	public GameModel offerTrade(MoveOfferTrade input) throws ServerException {
		return null;
	}

	@Override
	public GameModel maritimeTrade(MoveMaritimeTrade input) throws ServerException {
		return null;
	}

	@Override
	public GameModel robPlayer(MoveRobPlayer input) throws ServerException {
		return null;
	}

	@Override
	public GameModel finishTurn(MoveFinishTurn input) throws ServerException {
		return null;
	}

	@Override
	public GameModel buyDevCard(MoveBuyDevCard input) throws ServerException {
		return null;
	}

	@Override
	public GameModel playSoldier(MoveSoldier input) throws ServerException {
		return null;
	}

	@Override
	public GameModel playYearOfPlenty(MoveYearOfPlenty input) throws ServerException {
		return null;
	}

	@Override
	public GameModel playRoadBuilding(MoveRoadBuilding input) throws ServerException {
		return null;
	}

	@Override
	public GameModel playMonopoly(MoveMonopoly input) throws ServerException {
		return null;
	}

	@Override
	public GameModel playMonument(MoveMonument input) throws ServerException {
		return null;
	}

}