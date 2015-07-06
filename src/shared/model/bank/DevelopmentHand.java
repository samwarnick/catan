package shared.model.bank;

public class DevelopmentHand {
	
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