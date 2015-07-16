package client.poller;

import java.util.TimerTask;

import server.ServerException;
import shared.communication.input.GameModelVersionInput;
import shared.model.GameModel;
import client.controller.ModelController;
import client.proxy.MockProxyServer;
import client.proxy.ProxyServer;

public class UpdateGame extends TimerTask{

	private ModelController controller;
	
	public UpdateGame(ModelController control){
		super();
		controller = control;
	}
	
	@Override
	public void run() {
		GameModel serverModel = null;
		if (controller.isTesting()) {
			try {
				serverModel = MockProxyServer.getInstance().getGameModelVersion(new GameModelVersionInput(controller.getGameModelFacade().getGameModel().getGameVersion()));
			} catch (ServerException e) {
				e.printStackTrace();
			}
		}
		else {
			try {
				serverModel = ProxyServer.getInstance().getGameModelVersion(new GameModelVersionInput(controller.getGameModelFacade().getGameModel().getGameVersion()));
			} catch (ServerException e) {
				e.printStackTrace();
			}
		}
		if (serverModel != null)
		{
			System.out.println(serverModel.getPlayers().size());
			controller.updateGame(serverModel);
		}
	}
}
