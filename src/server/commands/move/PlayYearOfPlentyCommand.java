package server.commands.move;

import com.google.gson.Gson;

import server.commands.ICommand;
import shared.communication.input.Input;
import shared.communication.input.move.PlaySoldierInput;
import shared.communication.input.move.PlayYearOfPlentyInput;
import shared.definitions.DevCardType;
import shared.definitions.ResourceType;
import shared.model.GameModel;
import shared.model.bank.BankException;
import shared.model.board.PlayerID;
import shared.model.player.Player;

public class PlayYearOfPlentyCommand extends MoveCommand {

	//private GameModel model;
	
	
	/**
	 * @param input is a valid PlayYearOfPlentyInput object
	 * @pre The two specified resources are available in the bank
	 * @post The player gained one of each of the two specified resources
	 * @return The GameModel after executing the changes
	 */
	@Override
	public Object execute(String input) {
		Gson parser = new Gson();
		PlayYearOfPlentyInput playYearOfPlentyInput = parser.fromJson(input, PlayYearOfPlentyInput.class);
		ResourceType type1 = getResourceTypeFromString(playYearOfPlentyInput.getResource1());
		ResourceType type2 = getResourceTypeFromString(playYearOfPlentyInput.getResource2());
		//add resources to player
		Player p = model.getPlayer(new PlayerID(playYearOfPlentyInput.getPlayerIndex()));
		try {
			p.getPlayerBank().getResourceStack(type1).setQuantity(p.getPlayerBank().getResourceStack(type1).getQuantity() + 1);
			p.getPlayerBank().getResourceStack(type2).setQuantity(p.getPlayerBank().getResourceStack(type2).getQuantity() + 1);
			//decrement resources from bank
			model.getBank().getResourceStack(type1).setQuantity(model.getBank().getResourceStack(type1).getQuantity() -1);
			model.getBank().getResourceStack(type2).setQuantity(model.getBank().getResourceStack(type2).getQuantity() -1);
		} catch (BankException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//decrement year of plenty card
		try {
			p.getPlayerBank().getDevStack(DevCardType.YEAR_OF_PLENTY).setQuantity(p.getPlayerBank().getDevStack(DevCardType.YEAR_OF_PLENTY).getQuantity() - 1);
		} catch (BankException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return model;
	}

	public void setModel(GameModel model) {
		this.model = model;
	}
	
	private ResourceType getResourceTypeFromString(String type)
	{
		ResourceType realType = null;
		switch(type){
		case "brick":
			realType = ResourceType.BRICK;
		case "wood":
			realType = ResourceType.WOOD;
		case "sheep":
			realType = ResourceType.SHEEP;
		case "wheat":
			realType = ResourceType.WHEAT;
		case "ore":
			realType = ResourceType.ORE;
		default:
				System.out.println("dat ain't a real type 'o reasource - PlayYearOfPlentyCommand");
		}
		return realType;
	}
	
}
