package shared.model.player;

import shared.locations.EdgeLocation;
import shared.locations.VertexLocation;

public class ActivePlayerFacade implements IPlayerFacade{

	private Player player;
	
	
	@Override
	public boolean canBuildCity(VertexLocation location) {
		if (player.getCities().getCitiesLeft() > 0)
			return true;
		else
			return false;
	}

	@Override
	public boolean canBuildSettlement() {
		if (player.getSettlements().getSettlementsLeft() > 0)
			return true;
		else
			return false;
	}

	@Override
	public boolean canBuildRoad(EdgeLocation location) {
		if (player.getRoads().getRoadsLeft() > 0)
			return true;
		else
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

}
