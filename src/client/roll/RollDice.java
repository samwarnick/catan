package client.roll;

import java.util.TimerTask;

public class RollDice implements Runnable {
	
	private RollController controller;
	
	public RollDice(RollController controller){
		this.controller = controller;
	}

	@Override
	public void run() {

		if (controller.getRollView().isModalShowing()){
			controller.rollDice();
		}
		
	}

	
	
}
