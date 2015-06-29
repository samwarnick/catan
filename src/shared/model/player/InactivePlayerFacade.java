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
		//need to do this part
		return false;
	}

}
