package shared.model.bank;

import java.io.Serializable;

@SuppressWarnings("serial")
public class DevelopmentHand implements Serializable{
	
	private int soldier;
	private int monument;
	private int monopoly;
	private int yearOfPlenty;
	private int roadBuild;
	
	public DevelopmentHand() {
		this.soldier = 0;
		this.monument = 0;
		this.monopoly = 0;
		this.yearOfPlenty = 0;
		this.roadBuild = 0;
	}
	
	public DevelopmentHand(int soldier, int monument, int monopoly, int yearOfPlenty, int roadBuild) {
		this.soldier = soldier;
		this.monument = monument;
		this.monopoly = monopoly;
		this.yearOfPlenty = yearOfPlenty;
		this.roadBuild = roadBuild;
	}

	public int getSoldier() {
		return soldier;
	}

	public int getMonument() {
		return monument;
	}

	public int getMonopoly() {
		return monopoly;
	}

	public int getYearOfPlenty() {
		return yearOfPlenty;
	}

	public int getRoadBuild() {
		return roadBuild;
	}

	public void setSoldier(int soldier) {
		this.soldier = soldier;
	}

	public void setMonument(int monument) {
		this.monument = monument;
	}

	public void setMonopoly(int monopoly) {
		this.monopoly = monopoly;
	}

	public void setYearOfPlenty(int yearOfPlenty) {
		this.yearOfPlenty = yearOfPlenty;
	}

	public void setRoadBuild(int roadBuild) {
		this.roadBuild = roadBuild;
	}

}
