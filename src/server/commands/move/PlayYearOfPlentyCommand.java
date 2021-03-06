package server.commands.move;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

import shared.communication.input.move.PlayYearOfPlentyInput;
import shared.definitions.DevCardType;
import shared.definitions.ResourceType;
import shared.model.GameModel;
import shared.model.bank.BankException;
import shared.model.board.PlayerID;
import shared.model.player.Player;

public class PlayYearOfPlentyCommand extends MoveCommand {

	//private GameModel model;
	
	
	public PlayYearOfPlentyCommand() {
		super();
	}

	/**
	 * @param input is a valid PlayYearOfPlentyInput object
	 * @pre The two specified resources are available in the bank
	 * @post The player gained one of each of the two specified resources
	 * @return The GameModel after executing the changes
	 */
	@Override
	public Object execute(String input) {
		PlayYearOfPlentyInput playYearOfPlentyInput;
		try {
			playYearOfPlentyInput = new ObjectMapper().readValue(input, PlayYearOfPlentyInput.class);
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
		} catch (IOException e1) {
			e1.printStackTrace();
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
			break;
		case "wood":
			realType = ResourceType.WOOD;
			break;
		case "sheep":
			realType = ResourceType.SHEEP;
			break;
		case "wheat":
			realType = ResourceType.WHEAT;
			break;
		case "ore":
			realType = ResourceType.ORE;
			break;
		default:
				System.out.println("dat ain't a real type 'o reasource - PlayYearOfPlentyCommand");
		}
		return realType;
	}
	
}
