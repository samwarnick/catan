package server.commands.move;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

import shared.communication.input.move.RobPlayerInput;
import shared.definitions.ResourceType;
import shared.locations.HexLocation;
import shared.model.GameModel;
import shared.model.bank.BankException;
import shared.model.board.PlayerID;
import shared.model.player.Player;

public class RobPlayerCommand extends MoveCommand {

	//private GameModel model;
	public RobPlayerCommand() {
		super();
	}
	
	/**
	 * @param input is a valid RobPlayerInput object
	 * @pre The specified location is not the current location of the robber. The victim index must be a valid player index and the player must have at least one resource (unless no player is being robbed).
	 * @post The robber location is set to the specified location. If a player is being robbed, that player has one less resource and the robbing player received that resource.
	 * @return The GameModel after executing the changes
	 */
	@Override
	public Object execute(String input) {
		//System.out.println(input);
		RobPlayerInput robPlayerInput;
		try {
			//System.out.println("c");
			robPlayerInput = new ObjectMapper().readValue(input, RobPlayerInput.class);
			//robber location is set to specified location
			HexLocation newLocation = robPlayerInput.getHexLocation(); //add this function to RobPlayerInput;
			try {
				//System.out.println("d");
				model.getBoard().getRobber().moveRobber(newLocation);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//victim gives a card to the player who moved the robber
			int victimIndex = robPlayerInput.getVictimIndex();
			int aggressorIndex = robPlayerInput.getPlayerIndex();
			//System.out.println("e");
			if(victimIndex != -1)
			{
				//System.out.println("f");
				Player victim = model.getPlayer(new PlayerID(victimIndex));
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
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		//System.out.println("g");
		model.getTurnTracker().setStatus("Playing");
		return model;
	}

	public void setModel(GameModel model) {
		this.model = model;
	}
	
	private boolean CheckIfVictimHasCard(ResourceType type, Player victim)
	{
		//System.out.println("a");
		return (victim.getPlayerBank().getResourceStack(type).getQuantity() > 0);
	}
	
	private void giveReasource(ResourceType type, int victimIndex, int aggressorIndex)
	{
		try {
			//System.out.println("b");
			model.getPlayer(new PlayerID(victimIndex)).getPlayerBank().getResourceStack(type).setQuantity(model.getPlayer(new PlayerID(victimIndex)).getPlayerBank().getResourceStack(type).getQuantity() - 1);
			model.getPlayer(new PlayerID(aggressorIndex)).getPlayerBank().getResourceStack(type).setQuantity(model.getPlayer(new PlayerID(aggressorIndex)).getPlayerBank().getResourceStack(type).getQuantity() + 1);
		} catch (BankException e) {
			e.printStackTrace();
		}
	}

}
