package shared.model.player;

import java.util.List;

import shared.definitions.ResourceType;
import shared.locations.EdgeLocation;
import shared.locations.VertexLocation;
import shared.model.bank.ResourceCard;
/**
 * 
 * @author Spencer Krieger
 *
 */
public class ActivePlayerFacade implements IPlayerFacade{

	private Player player;
	
	
	@Override
	public boolean canBuildCity(VertexLocation location) {
		if (player.getCities().getCitiesLeft() > 0 && player.getPlayerBank().hasRC(ResourceType.WHEAT, 2) 
				&& player.getPlayerBank().hasRC(ResourceType.ORE, 3))
			return true;
		else
			return false;
	}

	@Override
	public boolean canBuildSettlement() {
		if (player.getSettlements().getSettlementsLeft() > 0
				&& player.getPlayerBank().hasRC(ResourceType.WHEAT, 1)
				&& player.getPlayerBank().hasRC(ResourceType.WOOD, 1)
				&& player.getPlayerBank().hasRC(ResourceType.BRICK, 1)
				&& player.getPlayerBank().hasRC(ResourceType.SHEEP, 1))
			//also needs to check for adjacent road belonging to player
			return true;
		else
			return false;
	}

	@Override
	public boolean canBuildRoad(EdgeLocation location) {
		if (player.getRoads().getRoadsLeft() > 0 && player.getPlayerBank().hasRC(ResourceType.WOOD, 1)
				&& player.getPlayerBank().hasRC(ResourceType.BRICK, 1))
			// also needs to check for an adjacent road belonging to player
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
		if (player.getPlayerBank().hasRC(ResourceType.SHEEP, 1)
				&& player.getPlayerBank().hasRC(ResourceType.ORE, 1)
				&& player.getPlayerBank().hasRC(ResourceType.WHEAT, 1))
			return true;
		else
			return false;
	}

	@Override
	public boolean canAcceptTrade(List<ResourceCard> list) {
		return false;
	}

	@Override
	public boolean canDiscard() {
		return false;//still need to be implemented
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
