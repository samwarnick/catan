package server.commands.move;

import java.util.ArrayList;
import java.util.Random;

import shared.definitions.DevCardType;
import shared.model.bank.DevelopmentHand;
import shared.model.bank.ResourceHand;

public class BuyDevCardCommand extends MoveCommand {
	
	/**
	 * @param input is a valid BuyDevCardInput object
	 * @pre The player has the required resources (1 ore, 1 wheat, 1 sheep) and there is at least one development card left in the game bank
	 * @post The player has a new development card. If it is a monument card, it has been added to the player's old development card hand. If it is a non-monument card, it has been added to the player's new development card hand. 
	 * @return The GameModel after executing the changes
	 */

	public Object execute(String input) {
		ArrayList<Integer> empties = new ArrayList<Integer>();
		Random rand = new Random();
		DevCardType dct = null;
		String name = null;
		DevelopmentHand dh = new DevelopmentHand();
	    int randomNum;
	    try{
		while(true){
			
			while(true){
				randomNum = rand.nextInt(5);
				if(!empties.contains(randomNum)) break;
			}
			
		    switch(randomNum){
		    case 0:
		    	if(model.getBank().hasDC(DevCardType.SOLDIER, 1)){
		    		dct = DevCardType.SOLDIER;
		    		dh.setSoldier(-1);
		    		break;
		    	}
		    	empties.add(randomNum);
		    case 1:
		    	if(model.getBank().hasDC(DevCardType.YEAR_OF_PLENTY, 1)){
		    		dct = DevCardType.YEAR_OF_PLENTY;
		    		dh.setYearOfPlenty(-1);
		    		break;
		    	}
		    	empties.add(randomNum);
		    case 2:
		    	if(model.getBank().hasDC(DevCardType.ROAD_BUILD, 1)){
		    		dct = DevCardType.ROAD_BUILD;
		    		dh.setRoadBuild(-1);
		    		break;
		    	}
		    	empties.add(randomNum);
		    case 3:
		    	if(model.getBank().hasDC(DevCardType.MONOPOLY, 1)){
		    		dct = DevCardType.MONOPOLY;
		    		dh.setMonopoly(-1);
		    		break;
		    	}
		    	empties.add(randomNum);
		    case 4:
		    	if(model.getBank().hasDC(DevCardType.MONUMENT, 1)){
		    		dct = DevCardType.MONUMENT;
		    		dh.setMonument(-1);
		    		break;
		    	}
		    	empties.add(randomNum);
		    }
			
			if(dct != null) break;
		}
		
		model.getPlayer(name).getPlayerBank().modifyRC(new ResourceHand(0,0,-1,-1,-1));
		model.getBank().modifyRC(new ResourceHand(0,0,1,1,1));
		model.getPlayer(name).getPlayerBank().addDC(dct);
		model.getBank().modifyDC(dh);
		}	catch(Exception e){
			e.printStackTrace();
		}

		return model;
	}


}
