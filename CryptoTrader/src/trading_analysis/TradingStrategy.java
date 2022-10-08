package trading_analysis;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * The Class TradingStrategy.
 */
public class TradingStrategy {
	
	/** The trade strat name. */
	private String tradeStrat;
	
	/** The coin list. */
	private List<String> coinList = new ArrayList<String>();
	
	/** The count of how many times each strategy has executed successfully. */
	private int aCount = 0, bCount = 0, cCount = 0, dCount = 0, eCount = 0;
	
	/**
	 * Instantiates a new trading strategy.
	 */
	public TradingStrategy() {
		tradeStrat = "Strategy-A";
		coinList.add("bitcoin");
		coinList.add("ethereum");
	}
	
	/**
	 * Instantiates a new trading strategy.
	 *
	 * @param tradeStrat the trade strat
	 */
	public TradingStrategy(String tradeStrat) {
		this.tradeStrat = tradeStrat;
		
		if(tradeStrat.equals("Strategy-A")) {
			coinList.add("bitcoin");
			coinList.add("dogecoin");
		}
		
		else if(tradeStrat.equals("Strategy-B")) {
			coinList.add("bitcoin");
		}
		
		else if(tradeStrat.equals("Strategy-C")) {
			coinList.add("tether");
			coinList.add("heco-peg-bnb");
			coinList.add("cardano");
		}
		else if(tradeStrat.equals("Strategy-D")) {
			coinList.add("terra-luna");
			coinList.add("avalanche-2");
			coinList.add("solara");
		}
		else if(tradeStrat.equals("Strategy-E")) {
			coinList.add("bitcoin");
			coinList.add("ethereum");
			coinList.add("heco-peg-xrp");
		}
		else {
			this.tradeStrat = "Strategy-A";
			coinList.add("bitcoin");
			coinList.add("ethereum");
		}
		
	}
	
	
	/**
	 * Make trade.
	 *
	 * @param tradeStrat the trade strategy
	 * @param traderName the trader name
	 * @param traderMap the trader map
	 * @return the trade result
	 */
	//"bitcoin", "ethereum", "cardano", "tether", "heco-peg-bnb", "heco-peg-xrp", "terra-luna", "solana", "avalanche-2", "dogecoin"
	public TradeResult makeTrade(String tradeStrat, String traderName, Map<String, Double> traderMap) {
		TradeResult result;
		if(tradeStrat.equals("Strategy-A")) {
			this.tradeStrat = "Strategy-A";
			Double btcPrice = traderMap.get("bitcoin");
			result = tradingStrategyA(traderName, btcPrice);
			if(result != null) {
				aCount++;
			}
			return result;
		}
		else if(tradeStrat.equals("Strategy-B")) {
			this.tradeStrat = "Strategy-B";
			Double btcPrice = traderMap.get("bitcoin");
			Double dogePrice = traderMap.get("dogecoin");
			result = tradingStrategyB(traderName, btcPrice, dogePrice);
			if(result != null) {
				bCount++;
			}
			return result;
		}
		else if(tradeStrat.equals("Strategy-C")) {
			this.tradeStrat = "Strategy-C";
			Double usdtPrice = traderMap.get("tether");
			Double bnbPrice = traderMap.get("heco-peg-bnb");
			Double adaPrice = traderMap.get("cardano");
			result = tradingStrategyC(traderName, usdtPrice, adaPrice, bnbPrice);
			if(result != null) {
				cCount++;
			}
			return result;
		}
		else if(tradeStrat.equals("Strategy-D")) {
			this.tradeStrat = "Strategy-D";
			Double lunaPrice = traderMap.get("terra-luna");
			Double avaxPrice = traderMap.get("avalanche-2");
			Double solPrice = traderMap.get("solana");
			result = tradingStrategyD(traderName, lunaPrice, avaxPrice, solPrice);
			if(result != null) {
				dCount++;
			}
			return result;
		}
		//double btcPrice, double ethPrice, double xrpPrice
		else if(tradeStrat.equals("Strategy-E")) {
			this.tradeStrat = "Strategy-E";
			Double btcPrice = traderMap.get("bitcoin");
			Double ethPrice = traderMap.get("ethereum");
			Double xrpPrice = traderMap.get("heco-peg-xrp");
			result = tradingStrategyE(traderName, btcPrice, ethPrice, xrpPrice);
			if(result != null) {
				eCount++;
			}
			return result;
		}
		else {
			return null;
		}
	}
	
	
	/**
	 * Trading strategy A.
	 *
	 * @param traderName the trader name
	 * @param btcPrice the price of bitcoin
	 * @return the trade result
	 */
	public TradeResult tradingStrategyA(String traderName, double btcPrice) {
		if(btcPrice < 60000) { // if below 60k, buy
			TradeResult trade = new TradeResult(traderName, tradeStrat, "BTC", "BUY", btcPrice, 10);
			return trade;
		}
		else if(btcPrice > 70000) { // sell if above 70k
			TradeResult trade = new TradeResult(traderName, tradeStrat, "BTC", "SELL", btcPrice, 10);
			return trade;
		}
		else {
			return null;
		}
	}
	
	
	/**
	 * Trading strategy B.
	 *
	 * @param traderName the trader name
	 * @param ethPrice the price of ethereum
	 * @param dogePrice the price of dogecoin
	 * @return the trade result
	 */
	//if BTC > 60,000 SELL 200 BTC
	public TradeResult tradingStrategyB(String traderName, double ethPrice, double dogePrice) {
		if(ethPrice > 5000 && dogePrice < 0.17) { // If Ethereum is above 5000 dollars, and Dogecoin is below 0.17 cents, buy 60000 Dogecoin.
			TradeResult trade = new TradeResult(traderName, tradeStrat, "DOGE", "BUY", dogePrice, 60000);
			return trade;
		}
		else if(ethPrice < 3500 && dogePrice > 0.20) { // If Ethereum is below 3500 dollars and Dogecoin is above 0.20 cents, sell 60000 Dogecoin.
			TradeResult trade = new TradeResult(traderName, tradeStrat, "DOGE", "SELL", dogePrice, 60000);
			return trade;
		}
		else { // Otherwise, hold
			return null;
		}
	}
	
	
	/**
	 * Trading strategy C.
	 *
	 * @param traderName the trader name
	 * @param usdtPrice the price of tether
	 * @param adaPrice the price of cardano
	 * @param bnbPrice the price of binance coin
	 * @return the trade result
	 */
	//if BTC > 50,000 and ETH < 5,000 BUY 4700 ADA
	public TradeResult tradingStrategyC(String traderName, double usdtPrice, double adaPrice, double bnbPrice) {
		if((usdtPrice < 1.40) && (adaPrice < 1.24)) { // If Cardano is below 1.40 and Tether is below 1.24, buy 5000 Cardano.
			TradeResult trade = new TradeResult(traderName, tradeStrat, "ADA", "BUY", adaPrice, 5000);
			return trade;
		}
		else { // Otherwise, buy 6 Binance Coin
			TradeResult trade = new TradeResult(traderName, tradeStrat, "BNB", "BUY", bnbPrice, 6);
			return trade;
		}
	}
	
	
	/**
	 * Trading strategy D.
	 *
	 * @param traderName the trader name
	 * @param lunaPrice the price of terra
	 * @param avaxPrice the price of avalanche
	 * @param solPrice the price of solana
	 * @return the trade result
	 */
	//if BTC > 40,000 and ETH > 5,000 SELL 50 ETH
	public TradeResult tradingStrategyD(String traderName, double lunaPrice, double avaxPrice, double solPrice) {
		if((lunaPrice > 115) && (avaxPrice > 97)) { // If Terra is more than 115 dollars and Avalanche is more than 97 dollars, sell 100 Avalanche.
			TradeResult trade = new TradeResult(traderName, tradeStrat, "AVAX", "SELL", avaxPrice, 100);
			return trade;
		}
		else if((solPrice < 120) && (avaxPrice < 91)) { // If Solana is less than 120 dollars and Avalanche is less than 91 dollars, buy 80 Solana.
			TradeResult trade = new TradeResult(traderName, tradeStrat, "SOL", "BUY", solPrice, 80);
			return trade;
		}
		else { // Otherwise, buy 50 Avalanche.
			TradeResult trade = new TradeResult(traderName, tradeStrat, "AVAX", "BUY", avaxPrice, 50);
			return trade;
		}
	}
	
	/**
	 * Trading strategy E.
	 *
	 * @param traderName the trader name
	 * @param btcPrice the price of bitcoin
	 * @param ethPrice the price of ethereum
	 * @param xrpPrice the price of xrp
	 * @return the trade result
	 */
	public TradeResult tradingStrategyE(String traderName, double btcPrice, double ethPrice, double xrpPrice) {
		// If Ethereum is less than 4000 dollars and Bitcoin is less than 55000 dollars, and Ripple is less than 0.82 cents, buy 11000 Ripple
		if(((ethPrice < 4000) && (btcPrice < 55000)) || (xrpPrice < 0.82)) {
			TradeResult trade = new TradeResult(traderName, tradeStrat, "XRP", "BUY", xrpPrice, 11000);
			return trade;
		}
		else {
			return null;
		}
	}
	
	/**
	 * Sets the strat.
	 *
	 * @param strat the new strat
	 */
	public void setStrat(String strat) {
		this.tradeStrat = strat;
	}
	
	/**
	 * Gets the a count.
	 *
	 * @return the a count
	 */
	public int getACount() {
		return aCount;
	}
	
	/**
	 * Gets the b count.
	 *
	 * @return the b count
	 */
	public int getBCount() {
		return bCount;
	}
	
	/**
	 * Gets the c count.
	 *
	 * @return the c count
	 */
	public int getCCount() {
		return cCount;
	}
	
	/**
	 * Gets the d count.
	 *
	 * @return the d count
	 */
	public int getDCount() {
		return dCount;
	}
	
	/**
	 * Gets the e count.
	 *
	 * @return the e count
	 */
	public int getECount() {
		return eCount;
	}
	
	
	
	
}
