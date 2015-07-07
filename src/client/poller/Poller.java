package client.poller;

import java.util.Timer;

import client.controller.Controller;

public class Poller {

	private Timer timer;
	private Controller controller;
	
	public Poller(Controller controller){
		this.controller = controller;
		timer = new Timer();
		timer.schedule(new UpdateGame(controller), 0,60000);
	}
	
	
}
