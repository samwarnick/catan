package client.roll;

import java.util.Timer;

import client.poller.UpdateGame;

public class RollTimer {
	
private Timer timer;
private RollController controller;
	
	public RollTimer(RollController controller){
		this.controller = controller;
		timer = new Timer();
		timer.schedule(new RollDice(controller), 5000, 20000);
	}
}
