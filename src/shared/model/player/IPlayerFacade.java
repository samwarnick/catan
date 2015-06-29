package shared.model.player;

import shared.locations.EdgeLocation;
import shared.locations.VertexLocation;

public interface IPlayerFacade {

	/**
	 * 
	 * @param location
	 * pre the location is a valid location on the map
	 * post returns true if: 1) the player has at least one unplayed city
	 *  2) the player has a settlement at that location
	 *  3) the player has at least 2 wheat and 3 ore resource cards in their bank
	 *  4) the player is the active player
	 *  returns false if either 1 or 2 or 3 or 4 aren't satisfied.
	 *  
	 */
	public boolean canBuildCity(VertexLocation location);
	/**
	 * 
	 * pre none
	 * post returns true if:
	 *  1) The player has at least one unplayed settlement
	 *  2) The player has at least 1 wood, 1 brick, 1 sheep, 1 wheat
	 *  3) the player is the active player
	 *  returns false if any of the constraints aren't met.
	 */
	public boolean canBuildSettlement();
	/**
	 * 
	 * @param location
	 * pre the location is a valid location on the map
	 * post returns true if:
	 *  1) the player has at least one unplayed road
	 *  2) the player has at least 1 wood and 1 brick
	 *  3) the player is the active player
	 *  returns false if any of the constraints aren't met
	 */
	public boolean canBuildRoad(EdgeLocation location);
	/**
	 * pre none
	 * post returns true if the player hasn't played a card this turn and it is the active player
	 * returns false if any of the constraints aren't met
	 * 
	 */
	public boolean canPlayCard();
	/**
	 * 
	 * pre none
	 * post returns true if the player is the active player
	 * returns false if they aren't the active player
	 */
	public boolean canTrade();
}