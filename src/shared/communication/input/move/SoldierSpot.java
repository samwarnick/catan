package shared.communication.input.move;

public class SoldierSpot {
	private String x;
	private String y;
	
	public SoldierSpot(String x, String y) {
		super();
		this.x = x;
		this.y = y;
	}
	
	public SoldierSpot() {
		super();
		this.x = "0";
		this.y = "0";
	}
	
	public String getX() {
		return x;
	}
	public String getY() {
		return y;
	}

	public void setX(String x) {
		this.x = x;
	}

	public void setY(String y) {
		this.y = y;
	}
	
	
}
