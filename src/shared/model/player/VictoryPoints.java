package shared.model.player;

import java.io.Serializable;


/**
 * 
 * @author Spencer Krieger
 *
 *keeps track of the total number of victory point a player has.
 *keeps track of the total number of visible victory points a player has.
 */
@SuppressWarnings("serial")
public class VictoryPoints implements Serializable{
	
	private int totalVictoryPoints;
	private int publicVictoryPoints;
	
	public VictoryPoints() {
		totalVictoryPoints = 0;
		publicVictoryPoints = 0;
	}
	
	public VictoryPoints(int totalVictoryPoints, int publicVictoryPoints) {
		this.totalVictoryPoints = totalVictoryPoints;
		this.publicVictoryPoints = publicVictoryPoints;
	}
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
