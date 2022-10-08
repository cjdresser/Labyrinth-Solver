package trading_analysis;

import java.time.LocalDate;


/**
 * The Class TradeResult holds all data associated with a completed trade.
 */
public class TradeResult {
	
	/** The trader name, strategy applied, coin of interest, and action taken("buy" or "sell"). */
	private String traderName, tradeStrategy, coinName, action;
	
	/** The price at which the coin was traded. */
	private double price; 
	
	/** The quantity of coin traded. */
	private int quantity;
	
	/** The date on which the trader took place. */
	private LocalDate date;
	
	/**
	 * Instantiates a new trade result.
	 */
	public TradeResult() {
		traderName = null;
		tradeStrategy = null;
		coinName = null;
		action = null;
		price = 0;
		quantity = 0;
		date = null;
	}
	
	/**
	 * Instantiates a new trade result.
	 *
	 * @param traderName the trader name
	 * @param tradeStrategy the trade strategy
	 * @param coinName the coin name
	 * @param action the action
	 * @param price the price
	 * @param quantity the quantity
	 */
	public TradeResult(String traderName, String tradeStrategy, String coinName, String action, double price, int quantity) {
		this.traderName = traderName;
		this.tradeStrategy = tradeStrategy;
		this.coinName = coinName;
		this.action = action;
		this.price = price;
		this.quantity = quantity;
		this.date = LocalDate.now();
	}

	/**
	 * Gets the trader name.
	 *
	 * @return the trader name
	 */
	public String getTraderName() {
		return traderName;
	}

	/**
	 * Sets the trader name.
	 *
	 * @param traderName the new trader name
	 */
	public void setTraderName(String traderName) {
		this.traderName = traderName;
	}

	/**
	 * Gets the trade strategy.
	 *
	 * @return the trade strategy
	 */
	public String getTradeStrategy() {
		return tradeStrategy;
	}

	/**
	 * Sets the trade strategy.
	 *
	 * @param tradeStrategy the new trade strategy
	 */
	public void setTradeStrategy(String tradeStrategy) {
		this.tradeStrategy = tradeStrategy;
	}

	/**
	 * Gets the coin name.
	 *
	 * @return the coin name
	 */
	public String getCoinName() {
		return coinName;
	}

	/**
	 * Sets the coin name.
	 *
	 * @param coinName the new coin name
	 */
	public void setCoinName(String coinName) {
		this.coinName = coinName;
	}

	/**
	 * Gets the action.
	 *
	 * @return the action
	 */
	public String getAction() {
		return action;
	}

	/**
	 * Sets the action.
	 *
	 * @param action the new action
	 */
	public void setAction(String action) {
		this.action = action;
	}

	/**
	 * Gets the price.
	 *
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * Sets the price.
	 *
	 * @param price the new price
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * Gets the quantity.
	 *
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * Sets the quantity.
	 *
	 * @param quantity the new quantity
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	/**
	 * Gets the date.
	 *
	 * @return the date
	 */
	public LocalDate getDate() {
		return date;
	}

	/**
	 * Sets the date.
	 *
	 * @param date the new date
	 */
	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	/**
	 * Gets the result.
	 *
	 * @return the result
	 */
	public String[] getResult() {
		String[] arr = {traderName, tradeStrategy, coinName, action, Integer.toString(quantity), Double.toString(price), date.toString()};
		return arr;
	}
	
	
}
