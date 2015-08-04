package server.commands.move;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import server.ServerException;
import server.commands.ICommand;
import shared.communication.input.Input;
import shared.communication.input.move.BuildCityInput;
import shared.communication.input.move.PlayMonopolyInput;
import shared.communication.input.move.SendChatInput;
import shared.definitions.DevCardType;
import shared.definitions.ResourceType;
import shared.model.GameModel;
import shared.model.bank.BankException;
import shared.model.bank.DevelopmentHand;
import shared.model.board.PlayerID;
import shared.model.player.Player;

public class PlayMonopolyCommand extends MoveCommand {

	//private GameModel model;
	
	
	/**
	 * @param input is a valid PlayMonopolyInput object
	 * @pre None
	 * @post All other players have given all of the specified resource to 
	 * @return The GameModel after executing the changes
	 */
	@Override
	public Object execute(String input) {
		PlayMonopolyInput playMonopolyInput;
		try {
			playMonopolyInput = new ObjectMapper().readValue(input, PlayMonopolyInput.class);
			int playerIndex = playMonopolyInput.getPlayerIndex();
			String type = playMonopolyInput.getResource();
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
					e.printStackTrace();
				}
			}
			PlayerID index = new PlayerID(playerIndex);
			Player p = model.getPlayer(index);
			try {
				p.getPlayerBank().getResourceStack(realType).setQuantity(totalNumberOfCardsRecieved);
			} catch (BankException e) {
				e.printStackTrace();
			}
			try {
				p.getPlayerBank().modifyDC(new DevelopmentHand(0,0,-1,0,0));
			} catch (BankException e) {
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

	
}