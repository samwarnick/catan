package shared.model.player;


import shared.communication.input.move.ResourceHand;
import shared.locations.EdgeLocation;
import shared.locations.VertexLocation;

/**
 * 
 * @author Spencer Krieger
 *
 */
public class InactivePlayerFacade implements IPlayerFacade{

	private Player player;
	public InactivePlayerFacade(Player player){
		this.player = player;
	}
	@Override
	public boolean canBuildCity(VertexLocation location) {
		return false;
	}

	@Override
	public boolean canBuildSettlement() {
		return false;
	}

	@Override
	public boolean canBuildRoad(EdgeLocation location) {
		return false;
	}

	@Override
	public boolean canPlayCard() {
		return false;
	}

	@Override
	public boolean canTrade() {
		return false;
	}

	@Override
	public boolean isActivePlayer() {
		return false;
	}

	@Override
	public boolean canBuyDevelopmentCard() {
		return false;
	}

	@Override
	public boolean canAcceptTrade(ResourceHand rh) {
		if (player.getPlayerBank().hasRC(rh))
			return true;
		else
			return false;
	}

	@Override
	public boolean canDiscard() {
		return (player.getPlayerBank().getNumResourceCards() > 7);
	}

	@Override
	public boolean canFinishTurn() {
		return false;
	}

	@Override
	public boolean canBeRobbed() {
		return player.getPlayerBank().getNumResourceCards() > 0;
	}

}
