package client.poller;

import java.util.Timer;


import client.controller.ModelController;

public class Poller {

	private Timer timer;
	
	public Poller(){
		timer = new Timer();
		timer.schedule(new UpdateGame(), 1000, 1000);
	}
	
	
}
