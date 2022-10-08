package trading_analysis;

import java.util.ArrayList;
import java.util.List;


/**
 * The Class TraderList holds the real list of all user created trading brokers.
 */
public class TraderList {
	
	/** The broker list. */
	private List<TradingBroker> brokerList;
	
	
	/**
	 * Instantiates a new trader list.
	 */
	public TraderList() {
		brokerList = new ArrayList<TradingBroker>();
	}
	
	/**
	 * Adds a broker.
	 *
	 * @param broker the broker
	 */
	public void addBroker(TradingBroker broker) {
		brokerList.add(broker);
	}
	
	/**
	 * Removes a broker.
	 *
	 * @param brokerName the broker name belonging to the broker to be removed
	 */
	public void removeBroker(String brokerName) {
		TradingBroker remBroker = null;
		for(TradingBroker broker : brokerList) {
			if(broker.getName().equals(brokerName)) {
				remBroker = broker;
			}
		}
		if(remBroker != null) {
			brokerList.remove(remBroker);
		}
	}
	
	/**
	 * Gets the broker list.
	 *
	 * @return the broker list
	 */
	public List<TradingBroker> getList(){
		return brokerList;
	}
	
	/**
	 * Compiles the list of all cryptocoins required by all trading brokers.
	 *
	 * @return the list of cryptocoins
	 */
	public List<String> compileCoinList(){
		List<String> coinList = new ArrayList<String>();
		List<String> tmpList;
		for(TradingBroker broker : brokerList) {
			tmpList = broker.getCoinList();
			for(String coin : tmpList) {
				if(!coinList.contains(coin)) {
					coinList.add(coin);
				}
			}
		}
		
		return coinList;
	}
	
	/**
	 * Checks if the broker list contains a broker with a given name.
	 *
	 * @param brokerName the broker's name
	 * @return true, if successful
	 */
	public boolean contains(String brokerName) {
		for(TradingBroker broker : brokerList) {
			if(broker.getName().equals(brokerName)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Gets the broker with a given name.
	 *
	 * @param brokerName the broker name
	 * @return the broker
	 */
	public TradingBroker getBroker(String brokerName) {
		for(TradingBroker broker : brokerList) {
			if(broker.getName().equals(brokerName)) {
				return broker;
			}
		}
		return null;
	}
	
	/**
	 * Edits a broker.
	 *
	 * @param oldBrokerName the old broker name
	 * @param newBroker the new broker
	 * @return 0 if successful
	 */
	public int editBroker(String oldBrokerName, TradingBroker newBroker) {
		for(TradingBroker broker : brokerList) {
			if(broker.getName().equals(oldBrokerName)) {
				brokerList.set(brokerList.indexOf(broker), newBroker);
				return 0;
			}
		}
		return -1;
	}
	
	/**
	 * Checks if the broker list is empty.
	 *
	 * @return true, if is empty
	 */
	public boolean isEmpty() {
		if(brokerList == null) {
			return true;
		}
		else {
			return false;
		}
	}
}
