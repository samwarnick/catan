package client.poller;

import java.util.TimerTask;

import server.ServerException;
import shared.communication.input.GameModelVersionInput;
import shared.model.GameModel;
import client.controller.ModelController;
import client.proxy.MockProxyServer;
import client.proxy.ProxyServer;

public class UpdateGame extends TimerTask{

	
	public UpdateGame(){
		super();
	}
	
	@Override
	public void run() {
//		System.out.println("POLLING");
		GameModel serverModel = null;
		if (ModelController.getInstance().isTesting()) {
			try {
				serverModel = MockProxyServer.getInstance().getGameModelVersion(new GameModelVersionInput(ModelController.getInstance().getGameModelFacade().getGameModel().getGameVersion()));
			} catch (ServerException e) {
				e.printStackTrace();
			}
		}
		else {
			try {
				serverModel = ProxyServer.getInstance().getGameModelVersion(new GameModelVersionInput(ModelController.getInstance().getGameModelFacade().getGameModel().getGameVersion()));
//				System.out.println(serverModel.getTurnTracker().getStatus());
			} catch (ServerException e) {
				e.printStackTrace();
			}
		}
		if (serverModel != null)
		{
			ModelController.getInstance().updateGame(serverModel);
		}
	}
}
