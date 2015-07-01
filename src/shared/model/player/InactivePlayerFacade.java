package shared.model.player;

import java.util.List;

import shared.locations.EdgeLocation;
import shared.locations.VertexLocation;
import shared.model.bank.ResourceCard;

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
	public boolean canAcceptTrade(List<ResourceCard> list) {
		for (ResourceCard rc : list)//still needs to be implemented, waiting for bank
		{
			if (player.getPlayerBank().hasRC(rc.getType(), 1))
				return true;
		}
		
		return false;
	}

	@Override
	public boolean canDiscard() {
		return false;//still needs to be implemented, waiting for bank
	}

	@Override
	public boolean canFinishTurn() {
		return false;
	}

	@Override
	public boolean canBeRobbed() {
		return false;//still needs to be implemented, waiting for bank
	}

}
