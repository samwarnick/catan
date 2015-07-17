package client.join;

import java.util.List;

import server.ServerException;
import shared.communication.input.GameModelVersionInput;
import shared.communication.input.GamesListInput;
import shared.model.GameModelFacade;
import client.base.*;
import client.controller.ModelController;
import client.controller.ModelController.ModelControllerListener;
import client.proxy.ProxyServer;
import client.data.PlayerInfo;


/**
 * Implementation for the player waiting controller
 */
public class PlayerWaitingController extends Controller implements IPlayerWaitingController,
																	ModelControllerListener{
	
	private ModelController MC;

	public PlayerWaitingController(IPlayerWaitingView view) {

		super(view);
		MC = ModelController.getInstance();
		MC.addListener(this);
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
	        int gameIndex =  ProxyServer.getInstance().getGameId();
	        PlayerInfo[] players = new PlayerInfo[gameList.get(gameIndex).getPlayers().size()];
	        players = gameList.get(gameIndex).getPlayers().toArray(players);

	        getView().setPlayers(players);
		} catch (ServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		getView().showModal();
	}
	
	private void closeView(){
		getView().closeModal();
	}

	@Override
	public void ModelChanged() {
		if(MC.getGameModelFacade().isGameFull()){
			//closeView();
		}
	}

	@Override
	public void addAI() {
		// TODO Auto-generated method stub
		
	}

}


