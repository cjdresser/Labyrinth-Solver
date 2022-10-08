package api;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


/**
 * The Class CG_API_DataFetcher is an API client which makes requests to the CoinGecko API 
 * and parses the results.
 */
public class CG_API_DataFetcher {
	
	/** The base URL. */
	String baseURL = "https://api.coingecko.com/api/v3/simple/price?ids=";
	
	/**
	 * Instantiates a new CG_API_DataFetcher.
	 */
	public CG_API_DataFetcher() {

	}
	
	/**
	 * Builds the request URL.
	 *
	 * @param coins the coins
	 * @return the string
	 */
	public String buildRequestURL(List<String> coins) {
		StringBuilder sb = new StringBuilder();
		sb.append(baseURL);
		for(String coin : coins) {//add each coin in the coin list to the request url, seperating them with "%2C"
			sb.append(coin);
			sb.append("%2C");
		}
		sb.append("&vs_currencies=cad");
		return sb.toString();
		
		
	}
	
	/**
	 * Makes a request.
	 *
	 * @param requestURLString the request URL string
	 * @return the base url string
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws ParseException the parse exception
	 */
	public String makeRequest(String requestURLString) throws IOException, ParseException {
		URL requestURL = new URL(requestURLString);
		
		HttpURLConnection http = (HttpURLConnection) requestURL.openConnection();
		
		http.setRequestProperty("accept", "application/json");
		
		
		InputStream is = http.getInputStream();
		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObject = (JSONObject)jsonParser.parse(
		      new InputStreamReader(is, "UTF-8"));
		

		http.disconnect();
		
		return jsonObject.toJSONString();
	}
	
	/**
	 * Makes an API request.
	 *
	 * @param coinList the coin list
	 * @return the string representation of the returned JSONObject
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws ParseException the parse exception
	 */
	public String makeRequest(List<String> coinList) throws IOException, ParseException {
		
		
		URL requestURL = new URL(buildRequestURL(coinList));
		
		HttpURLConnection http = (HttpURLConnection) requestURL.openConnection();
		
		http.setRequestProperty("accept", "application/json");
	
		
		InputStream is = http.getInputStream();
		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObject = (JSONObject)jsonParser.parse(
		      new InputStreamReader(is, "UTF-8"));
		

		http.disconnect();
	
		
		return jsonObject.toJSONString();
	}
	
	
	/**
	 * Makes an API request.
	 *
	 * @param coinList the coin list
	 * @return a map with coin names as keys and their prices as values
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws ParseException the parse exception
	 */
	public Map<String, Double> makeRequestMap(List<String> coinList) throws IOException, ParseException {
		Map<String, Double> priceMap = new HashMap<String, Double>();
		
		URL requestURL = new URL(buildRequestURL(coinList));
		
		HttpURLConnection http = (HttpURLConnection) requestURL.openConnection();
		
		http.setRequestProperty("accept", "application/json");

		
		InputStream is = http.getInputStream();
		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObject = (JSONObject)jsonParser.parse(
		      new InputStreamReader(is, "UTF-8"));
		

		http.disconnect();
		String key;
		String jsonPrice;
		Double price;
		for(String coin : coinList) {
			key = coin;
			jsonPrice = jsonObject.get(key).toString();
			jsonPrice = jsonPrice.substring((jsonPrice.indexOf(":") + 1), jsonPrice.indexOf("}"));
			price = Double.parseDouble(jsonPrice);
			priceMap.put(key, price);
		}
		
		return priceMap;
	}
	


}
