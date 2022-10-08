package trading_analysis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The Class TradingBroker.
 */
public class TradingBroker {
	
	/** The trading strategy of the broker. */
	private String name, tradingStrategy;
	
	/** The list of coins in which the broker is interested. */
	private List<String> coinList = new ArrayList<String>();
	
	/** The map of coins to their prices. */
	public Map<String, Double> coinMap = new HashMap<String, Double>();
	
	/**
	 * Instantiates a new trading broker.
	 */
	public TradingBroker() {
		this.name = null;
		this.tradingStrategy = null;
	}
	
	/**
	 * Instantiates a new trading broker.
	 *
	 * @param name the name of the broker
	 * @param tradingStrategy the trading strategy of the broker
	 * @param coinList the coin list of the broker
	 */
	public TradingBroker(String name, String tradingStrategy, List<String> coinList) {
		this.name = name;
		this.tradingStrategy = tradingStrategy;
		
		setCoinList(coinList);
	}

	/**
	 * Gets the name of the broker.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the trading strategy.
	 *
	 * @return the trading strategy
	 */
	public String getTradingStrategy() {
		return tradingStrategy;
	}

	/**
	 * Sets the trading strategy.
	 *
	 * @param tradingStrategy the new trading strategy
	 */
	public void setTradingStrategy(String tradingStrategy) {
		this.tradingStrategy = tradingStrategy;
	}

	/**
	 * Gets the coin list.
	 *
	 * @return the coin list
	 */
	public List<String> getCoinList() {
		return coinList;
	}

	
	/**
	 * Sets the coin list by translating the ticker of each coin to the real coin id.
	 *
	 * @param coinList the new coin list
	 */
	//"bitcoin", "ethereum", "cardano", "tether", "heco-peg-bnb", "heco-peg-xrp", "terra-luna", "solana", "avalanche-2", "dogecoin"
	//"BTC", "ETH", "ADA", "USDT", "BNB", "XRP", "LUNA", "SOL", "AVAX", "DOGE"
	public void setCoinList(List<String> coinList) {
		for(String coin : coinList) {
			switch(coin) {
			case "BTC":
				this.coinList.add("bitcoin");
				coinMap.put("bitcoin", null);
				break;
			case "ETH":
				this.coinList.add("ethereum");
				coinMap.put("ethereum", null);
				break;
			case "ADA":
				this.coinList.add("cardano");
				coinMap.put("cardano", null);
				break;
			case "USDT":
				this.coinList.add("tether");
				coinMap.put("tether", null);
				break;
			case "BNB":
				this.coinList.add("heco-peg-bnb");
				coinMap.put("heco-peg-bnb", null);
				break;
			case "XRP":
				this.coinList.add("heco-peg-xrp");
				coinMap.put("heco-peg-xrp", null);
				break;
			case "LUNA":
				this.coinList.add("terra-luna");
				coinMap.put("terra-luna", null);
				break;
			case "SOL":
				this.coinList.add("solana");
				coinMap.put("solana", null);
				break;
			case "AVAX":
				this.coinList.add("avalanche-2");
				coinMap.put("avalanche-2", null);
				break;
			case "DOGE":
				this.coinList.add("dogecoin");
				coinMap.put("dogecoin", null);
				break;
			}
		}
	}
	
	/**
	 * Adds a coin.
	 *
	 * @param coinName the coin name
	 */
	public void addCoin(String coinName) {
		coinList.add(coinName);
	}
	
	/**
	 * Removes a coin.
	 *
	 * @param coinName the coin name
	 */
	public void removeCoin(String coinName) {
		coinList.remove(coinName);
	}
	
	
	
	
	

}
