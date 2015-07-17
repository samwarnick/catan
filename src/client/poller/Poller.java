package client.poller;

import java.util.Timer;



public class Poller {

	private Timer timer;
	
	public Poller(){
		timer = new Timer();
		timer.schedule(new UpdateGame(), 5000, 5000);
	}
	
	
}
