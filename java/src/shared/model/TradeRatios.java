package shared.model;

import java.util.HashMap;
import shared.definitions.ResourceType;

public class TradeRatios {

	private HashMap<ResourceType, TradeRatio> tradeRatios;
	
	public TradeRatios() {
		tradeRatios = new HashMap<ResourceType, TradeRatio>();
		
		for (ResourceType type : ResourceType.values()) {
			tradeRatios.put(type, new TradeRatio());
		}
	}

	public HashMap<ResourceType, TradeRatio> getTradeRatios() {
		return tradeRatios;
	}
	
	public void setRatio(ResourceType type, int newRatio) {
		tradeRatios.get(type).setRatio(newRatio);
	}
}
