package server.commands.move;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import server.commands.ICommand;
import shared.communication.input.Input;
import shared.communication.input.move.PlayMonumentInput;
import shared.communication.input.move.PlaySoldierInput;
import shared.communication.input.move.SendChatInput;
import shared.definitions.DevCardType;
import shared.definitions.ResourceType;
import shared.locations.HexLocation;
import shared.model.GameModel;
import shared.model.bank.BankException;
import shared.model.board.PlayerID;
import shared.model.player.Player;

public class PlaySoldierCommand extends MoveCommand {

	//private GameModel model;
	
	
	/**
	 * @param input is a valid PlaySoldierInput object
	 * @pre The specified location is not the current location of the robber. The victim player has at least one resource.
	 * @post The robber is placed in the specified location. If a player is being robbed
	 * @return The GameModel after executing the changes
	 */
	@Override
	public Object execute(String input) {
		PlaySoldierInput playSoldierInput;
		try {
			playSoldierInput = new ObjectMapper().readValue(input, PlaySoldierInput.class);
			//robber location is set to specified location
			HexLocation newLocation = playSoldierInput.getHexLocation();
			try {
				model.getBoard().getRobber().moveRobber(newLocation);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//victim gives a card to the player who moved the robber
			int victimIndex = playSoldierInput.getVictimIndex();
			int aggressorIndex = playSoldierInput.getPlayerIndex();
			if(victimIndex != -1)
			{
				Player victim = model.getPlayer(new PlayerID(victimIndex));
				//Player aggressor = model.getPlayer(new PlayerID(aggressorIndex));
				if(CheckIfVictimHasCard(ResourceType.BRICK, victim))
				{
					giveReasource(ResourceType.BRICK, victimIndex, aggressorIndex);
				}
				else if(CheckIfVictimHasCard(ResourceType.WOOD, victim))
				{
					giveReasource(ResourceType.WOOD, victimIndex, aggressorIndex);
				}
				else if(CheckIfVictimHasCard(ResourceType.SHEEP, victim))
				{
					giveReasource(ResourceType.SHEEP, victimIndex, aggressorIndex);
				}
				else if(CheckIfVictimHasCard(ResourceType.WHEAT, victim))
				{
					giveReasource(ResourceType.WHEAT, victimIndex, aggressorIndex);
				}
				else if(CheckIfVictimHasCard(ResourceType.ORE, victim))
				{
					giveReasource(ResourceType.ORE, victimIndex, aggressorIndex);
				}
			}
			//decrement the players soldier card
			try {
				model.getPlayer(new PlayerID(aggressorIndex)).getPlayerBank().getDevStack(DevCardType.SOLDIER).setQuantity(model.getPlayer(new PlayerID(aggressorIndex)).getPlayerBank().getDevStack(DevCardType.SOLDIER).getQuantity() - 1);
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
	
	private boolean CheckIfVictimHasCard(ResourceType type, Player victim)
	{
		return (victim.getPlayerBank().getResourceStack(type).getQuantity() > 0);
	}
	
	private void giveReasource(ResourceType type, int victimIndex, int aggressorIndex)
	{
		try {
			model.getPlayer(new PlayerID(victimIndex)).getPlayerBank().getResourceStack(type).setQuantity(model.getPlayer(new PlayerID(victimIndex)).getPlayerBank().getResourceStack(type).getQuantity() - 1);
			model.getPlayer(new PlayerID(aggressorIndex)).getPlayerBank().getResourceStack(type).setQuantity(model.getPlayer(new PlayerID(aggressorIndex)).getPlayerBank().getResourceStack(type).getQuantity() + 1);
		} catch (BankException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}