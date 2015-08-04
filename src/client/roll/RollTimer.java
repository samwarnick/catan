package client.roll;

import java.util.Timer;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import client.poller.UpdateGame;

public class RollTimer {
	
private Timer timer;
private ScheduledThreadPoolExecutor executor;

private RollController controller;
	
	public RollTimer(RollController controller){
		this.controller = controller;
//		timer = new Timer();
//		timer.schedule(new RollDice(controller), 5000, 20000);
		executor = new ScheduledThreadPoolExecutor(1);
		executor.scheduleAtFixedRate(new RollDice(controller), 5000, 20000, TimeUnit.MICROSECONDS);
	}
}
