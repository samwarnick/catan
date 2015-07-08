package client.poller;

import java.util.TimerTask;

import client.controller.Controller;

public class UpdateGame extends TimerTask{

	private Controller controller;
	
	public UpdateGame(Controller control){
		super();
		controller = control;
	}
	
	@Override
	public void run() {
		controller.updateGame();
		
	}

}
