package shared.model.ratios;

/**
 * 
 * @author Matt
 *
 */
public class TradeRatio {

	private int ratio;
	
	public TradeRatio() {
		ratio = 4;
	}

	public int getRatio() {
		return ratio;
	}

	public void setRatio(int ratio) throws Exception {
		if (ratio <= 4 && ratio >= 2) {
			this.ratio = ratio;
		}
		else
			throw new Exception();
	}
	
	public String toString() {
		return String.format("%d:1", ratio);
	}
}
