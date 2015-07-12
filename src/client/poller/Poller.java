package client.poller;

import java.util.Timer;


import client.controller.ModelController;

public class Poller {

	private Timer timer;
	private ModelController controller;
	
	public Poller(ModelController controller){
		this.controller = controller;
		timer = new Timer();
		timer.schedule(new UpdateGame(controller), 1000, 1000);
	}
	
	
}
