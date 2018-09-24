package extractor.book.client;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import extractor.book.model.Location;
import extractor.book.utils.LocationsDictionary;

public class GoogleMapsClient {
	
	private static final Logger logger = Logger.getLogger(GoogleMapsClient.class);

	public static Map<String, Location> locations = new HashMap<>();

	
	public static Location query (String text, LocationsDictionary dictionary) throws Exception {
		Location location = null;
		text = text.toLowerCase().trim();
		
		if (locations.containsKey(text)) {
			logger.info("Location for text \"" + text + "\" is already stored so there is no need to make the google maps search");
			location = locations.get(text);
		}
		else {
			location = dictionary.getLocation(text);
		}
		
		return location; 
	}
}
