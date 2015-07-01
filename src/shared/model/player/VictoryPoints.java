package shared.model.player;


/**
 * 
 * @author Spencer Krieger
 *
 *keeps track of the total number of victory point a player has.
 *keeps track of the total number of visible victory points a player has.
 */
public class VictoryPoints {
	
	private int totalVictoryPoints;
	private int publicVictoryPoints;
	
	public int getTotalVictoryPoints() {
		return totalVictoryPoints;
	}
	public void addPrivateVictoryPoint() {
		totalVictoryPoints++;
	}
	public int getPublicVictoryPoints() {
		return publicVictoryPoints;
	}
	public void addPublicVictoryPoint() {
		totalVictoryPoints++;
		publicVictoryPoints++;
	}
	public void subtractPublicVictoryPoints(int numToSubtract){
		totalVictoryPoints -= numToSubtract;
		publicVictoryPoints -= numToSubtract;
	}
	
	
	

}
