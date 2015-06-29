package shared.model.player;

import shared.locations.EdgeLocation;
import shared.locations.VertexLocation;

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

}
