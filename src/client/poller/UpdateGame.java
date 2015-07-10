package client.poller;

import java.util.TimerTask;

import server.IServer;
import server.ServerException;
import shared.communication.input.GameModelVersionInput;
import shared.model.GameModel;
import client.controller.Controller;

public class UpdateGame extends TimerTask{

	private Controller controller;
	private IServer server;
	
	public UpdateGame(Controller control){
		super();
		controller = control;
		server = controller.getProxyServer();
	}
	
	@Override
	public void run() {
		GameModel serverModel = null;
		try {
			serverModel = server.getGameModelVersion(new GameModelVersionInput(controller.getGameModelFacade().getGameModel().getGameVersion()));
		} catch (ServerException e) {
			e.printStackTrace();
		}
		if (serverModel != null)
		{
			controller.updateGame(serverModel);
		}
	}
}
