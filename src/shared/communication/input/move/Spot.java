package shared.communication.input.move;

public class Spot {
	
	private int x;
	private int y;
	private String direction;
	
	public Spot(int x, int y, String direction){
		this.direction = direction;
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public String getDirection() {
		return direction;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

}
