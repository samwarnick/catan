package shared.model.player;


import shared.communication.input.move.ResourceHand;
import shared.locations.EdgeLocation;
import shared.locations.VertexLocation;
/**
 * 
 * @author Spencer Krieger
 *
 */
public class ActivePlayerFacade implements IPlayerFacade{

	private Player player;
	
	
	@Override
	public boolean canBuildCity(VertexLocation location) {
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
	public boolean canBuildRoad(EdgeLocation location) {
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
	public boolean canTrade() {
		return true;
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

}
