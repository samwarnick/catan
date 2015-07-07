package shared.model.player;

import shared.definitions.ResourceType;
import shared.model.bank.ResourceHand;

/**
 * 
 * @author Spencer Krieger
 *
 */

public interface IPlayerFacade {

	/**
	 * 
	 * @param location the location of the vertex on the board
	 * @pre the location is a valid location on the map
	 * @post returns true if: 1) the player has at least one unplayed city
	 *  2) the player has a settlement at that location
	 *  3) the player has at least 2 wheat and 3 ore resource cards in their bank
	 *  4) the player is the active player
	 *  returns false if either 1 or 2 or 3 or 4 aren't satisfied.
	 *  
	 */
	public boolean canBuildCity();
	/**
	 * 
	 * @pre none
	 * @post returns true if:
	 *  1) The player has at least one unplayed settlement
	 *  2) The player has at least 1 wood, 1 brick, 1 sheep, 1 wheat
	 *  3) the player is the active player
	 *  returns false if any of the constraints aren't met.
	 */
	public boolean canBuildSettlement();
	/**
	 * 
	 * @param location th
	 * @pre the location is a valid location on the map
	 * @post returns true if:
	 *  1) the player has at least one unplayed road
	 *  2) the player has at least 1 wood and 1 brick
	 *  3) the player is the active player
	 *  returns false if any of the constraints aren't met
	 */
	public boolean canBuildRoad();
	/**
	 * @pre none
	 * @post returns true if:
	 * 1) the player has at least 1 sheep, 1 ore, and 1 wheat
	 * 2) the player is the active player
	 * returns false if any of the constraints aren't met
	 */
	public boolean canBuyDevelopmentCard();
	/**
	 * @pre none
	 * @post returns true if the player hasn't played a card this turn and it is the active player
	 * returns false if any of the constraints aren't met
	 * 
	 */

	public boolean canPlayCard();
	
	/**
	 * 
	 * @pre none
	 * @post returns true if the player is the active player and has the resources to trade
	 * returns false if any of the constraints aren't met 
	 */
	public boolean canTrade(ResourceHand rh);
	
	/**
	 * 
	 * @pre none
	 * @post returns true if the player is the active player, has the resources to trade and the trade matches the player's trade ratio
	 * returns false if any of the constraints aren't met
	 */
	public boolean canMaritimeTrade(int amtOutput, ResourceType input, ResourceType output);
	/**
	 * @pre none
	 * @post returns true if the player is the active player and false if they are not the active player. 
	 *
	 */
	public boolean isActivePlayer();
	/**
	 * 
	 * @param list is a list of cards needed for the transaction
	 * @pre the list of cards is a valid list of resource cards
	 * @post returns true if the player has the cards needed for the trade, false if they don't
	 */
	public boolean canAcceptTrade(ResourceHand rh);
	/**
	 * 
	 * @pre none
	 * @post returns true if the player has more than 7 cards and it is their turn otherwise returns false
	 */
	public boolean canDiscard();
	/**
	 * @pre none
	 * @post returns true if the player is the active player.  Otherwise returns false.
	 * 
	 */
	public boolean canFinishTurn();
	/**
	 * @pre none
	 * @post returns true if it is not the player's turn and they have resource cards.  Otherwise returns false.
	 */
	public boolean canBeRobbed();
}