package shared.model.player;

import shared.definitions.ResourceType;
import shared.locations.EdgeLocation;
import shared.locations.VertexLocation;
import shared.model.bank.ResourceHand;
/**
 * 
 * @author Spencer Krieger
 *
 */
public class ActivePlayerFacade implements IPlayerFacade{

	private Player player;
	
	public ActivePlayerFacade(Player player){
		this.player = player;
	}
	
	@Override
	public boolean canBuildCity() {
		if (player.getCities().getCitiesLeft() > 0 && player.getPlayerBank().hasRC(new ResourceHand(0,0,0,2,3)))
			return true;
		else
			return false;
	}

	@Override
	public boolean canBuildSettlement() {
		if (player.getSettlements().getSettlementsLeft() > 0
				&& player.getPlayerBank().hasRC(new ResourceHand(1,1,1,1,0)))
			return true;
		else
			return false;
	}

	@Override
	public boolean canBuildRoad() {
		if (player.getRoads().getRoadsLeft() > 0 && player.getPlayerBank().hasRC(new ResourceHand(1,1,0,0,0)))
			return true;
		else
			return false;
	}

	@Override
	public boolean canPlayCard() {
		return !player.getHasPlayedCard();
	}

	@Override
	public boolean canTrade(ResourceHand rh) {
		return player.getPlayerBank().hasRC(rh);
	}

	@Override
	public boolean isActivePlayer() {
		return true;
	}

	@Override
	public boolean canBuyDevelopmentCard() {
		if (player.getPlayerBank().hasRC(new ResourceHand(0,0,1,1,1)))
			return true;
		else
			return false;
	}

	@Override
	public boolean canAcceptTrade(ResourceHand rh) {
		return false;
	}

	@Override
	public boolean canDiscard() {
		return (player.getPlayerBank().getNumResourceCards() > 7);
	}

	@Override
	public boolean canFinishTurn() {
		return true;
	}

	@Override
	public boolean canBeRobbed() {
		return false;
	}

	@Override
	public boolean canMaritimeTrade(int amtOutput, ResourceType input, ResourceType output) {
		int ratio = player.getTradeRatios().getTradeRatio(output).getRatio();
		ResourceHand rh = null;
		switch(input){
		case WOOD:	rh = new ResourceHand(ratio*amtOutput,0,0,0,0);
		case BRICK:	rh = new ResourceHand(0,ratio*amtOutput,0,0,0);
		case SHEEP:	rh = new ResourceHand(0,0,ratio*amtOutput,0,0);
		case WHEAT:	rh = new ResourceHand(0,0,0,ratio*amtOutput,0);
		case ORE:	rh = new ResourceHand(0,0,0,0,ratio*amtOutput);
		default: 	assert false;
		}
		
		return player.getPlayerBank().hasRC(rh);
	}


}
