package client.join;

import java.util.List;

import server.ServerException;
import shared.communication.input.GameModelVersionInput;
import shared.communication.input.GamesListInput;
import shared.model.GameModelFacade;
import client.base.*;
import client.proxy.ProxyServer;
import client.data.PlayerInfo;


/**
 * Implementation for the player waiting controller
 */
public class PlayerWaitingController extends Controller implements IPlayerWaitingController {

	public PlayerWaitingController(IPlayerWaitingView view) {

		super(view);
	}

	@Override
	public IPlayerWaitingView getView() {

		return (IPlayerWaitingView)super.getView();
	}

	@Override
	public void start() {
		GamesListInput gameIn = new GamesListInput();
		List<client.data.GameInfo> gameList;
		try {
			gameList = ProxyServer.getInstance().listGames(gameIn);
	        int gameIndex =  GameModelFacade.getInstance().getGameModel().getGameID();
	        PlayerInfo[] players = new PlayerInfo[gameList.get(gameIndex).getPlayers().size()];
	        players = gameList.get(gameIndex).getPlayers().toArray(players);

	        getView().setPlayers(players);
		} catch (ServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		getView().showModal();
	}

	@Override
	public void addAI() {

		// TEMPORARY
		getView().closeModal();
	}

}

