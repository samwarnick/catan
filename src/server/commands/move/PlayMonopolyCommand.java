package server.commands.move;

import com.google.gson.Gson;

import server.ServerException;
import server.commands.ICommand;
import shared.communication.input.Input;
import shared.communication.input.move.BuildCityInput;
import shared.communication.input.move.PlayMonopolyInput;
import shared.definitions.DevCardType;
import shared.definitions.ResourceType;
import shared.model.GameModel;
import shared.model.bank.BankException;
import shared.model.board.PlayerID;
import shared.model.player.Player;

public class PlayMonopolyCommand implements ICommand {

	private GameModel model;
	
	
	/**
	 * @param input is a valid PlayMonopolyInput object
	 * @pre None
	 * @post All other players have given all of the specified resource to 
	 * @return The GameModel after executing the changes
	 */
	@Override
	public Object execute(String input) {
		Gson parser = new Gson();
		PlayMonopolyInput playMonopolyInput = parser.fromJson(input, PlayMonopolyInput.class);
		int playerIndex = playMonopolyInput.getPlayerIndex();
		String type = playMonopolyInput.getType();
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
				System.out.println("dat ain't a real type 'o reasource - PlayMonopolyCommand");
		}
		int totalNumberOfCardsRecieved = 0;
		for(int i = 0; i < 4; i++)
		{
			PlayerID id = new PlayerID(i);
			Player p = model.getPlayer(id);
			totalNumberOfCardsRecieved += p.getPlayerBank().getResourceStack(realType).getQuantity();
			try {
				p.getPlayerBank().getResourceStack(realType).setQuantity(0);
			} catch (BankException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		PlayerID index = new PlayerID(playerIndex);
		Player p = model.getPlayer(index);
		try {
			p.getPlayerBank().getResourceStack(realType).setQuantity(totalNumberOfCardsRecieved);
		} catch (BankException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			p.getPlayerBank().getDevStack(DevCardType.MONOPOLY).setQuantity(p.getPlayerBank().getDevStack(DevCardType.MONOPOLY).getQuantity() - 1);
		} catch (BankException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return model;
	}

	public void setModel(GameModel model) {
		this.model = model;
	}

	
}