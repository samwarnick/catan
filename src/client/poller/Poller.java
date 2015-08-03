package client.poller;

import java.util.Timer;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;



public class Poller {

	private Timer timer;
	private ScheduledThreadPoolExecutor executor;
	
	public Poller(){
//		timer = new Timer();
//		timer.schedule(new UpdateGame(), 5000, 5000);
		
		executor = new ScheduledThreadPoolExecutor(1);
		executor.scheduleAtFixedRate(new UpdateGame(), 0, 5, TimeUnit.SECONDS);
	}
	
	
}
