package server.commands.move;

import java.util.ArrayList;
import java.util.Random;

import server.GameHub;
import server.commands.ICommand;
import shared.communication.input.Input;
import shared.definitions.DevCardType;
import shared.model.GameModel;
import shared.model.bank.DevelopmentHand;

public class BuyDevCardCommand implements ICommand {
	
	/**
	 * @param input is a valid BuyDevCardInput object
	 * @pre The player has the required resources (1 ore, 1 wheat, 1 sheep) and there is at least one development card left in the game bank
	 * @post The player has a new development card. If it is a monument card, it has been added to the player's old development card hand. If it is a non-monument card, it has been added to the player's new development card hand. 
	 * @return The GameModel after executing the changes
	 */
	@Override
	public Object execute(String input) {
		int GameID = -1;
		GameModel model = GameHub.getInstance().getModel(GameID);
		ArrayList<Integer> empties = new ArrayList<Integer>();
		Random rand = new Random();
		DevCardType dct = null;
		String name = null;
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
		    		break;
		    	}
		    	empties.add(randomNum);
		    case 1:
		    	if(model.getBank().hasDC(DevCardType.YEAR_OF_PLENTY, 1)){
		    		dct = DevCardType.SOLDIER;
		    		break;
		    	}
		    	empties.add(randomNum);
		    case 2:
		    	if(model.getBank().hasDC(DevCardType.ROAD_BUILD, 1)){
		    		dct = DevCardType.SOLDIER;
		    		break;
		    	}
		    	empties.add(randomNum);
		    case 3:
		    	if(model.getBank().hasDC(DevCardType.MONOPOLY, 1)){
		    		dct = DevCardType.SOLDIER;
		    		break;
		    	}
		    	empties.add(randomNum);
		    case 4:
		    	if(model.getBank().hasDC(DevCardType.MONUMENT, 1)){
		    		dct = DevCardType.SOLDIER;
		    		break;
		    	}
		    	empties.add(randomNum);
		    }
			
			if(dct != null) break;
		}
		
		model.getPlayer(name).getPlayerBank().addDC(dct);
		DevelopmentHand dh = new DevelopmentHand();
		dh.setSoldier(-1);
		model.getBank().modifyDC(dh);
		}	catch(Exception e){
			e.printStackTrace();
		}

		return model;
	}


}
