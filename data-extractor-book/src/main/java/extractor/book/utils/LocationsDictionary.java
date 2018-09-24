package extractor.book.utils;

import java.util.HashMap;
import java.util.Map;

import extractor.book.model.Coordinates;
import extractor.book.model.Location;
import extractor.book.model.mapquest.MapQuestLocation;
import extractor.book.reader.CSVLineReader;
import extractor.book.transformer.CSVToLocation;

public class LocationsDictionary {
	
	private Map<String, Location> locationDictionary = new HashMap<>();
	
	private CSVLineReader<MapQuestLocation> csvReader;
	
	public LocationsDictionary(String file) throws Exception {
		csvReader = new CSVLineReader<>(file, new CSVToLocation());
		MapQuestLocation map = null;
		while (null != (map = csvReader.readLine())) {
			locationDictionary.put(map.getOriginalName(), mapToLocation(map));
		}
		System.out.println("Retrieved " + locationDictionary.size() + " locations");
	}
	
	public Location getLocation (String name) {
		return locationDictionary.get(name);
	}
	
	private Location mapToLocation (MapQuestLocation map) {
		Location location = new Location();
			location.setText(map.getName());
			
		String[] components = map.getDesc().split(",");
		int length = components.length;
			
		String precision = map.getPrecision();	
		
		if ("street".equals(precision)) {
			if (length != 5) {
				System.out.println("Error: street is not 5 " + map.getDesc() );
			}
			location.setCity(components[length-3]);
			location.setRegion(components[length-2]);
			location.setCountry(components[length-1]);
		}
		else if ("NEIGHBORHOOD".equals(precision)) {
			if (! (length == 4 || length == 3)) {
				System.out.println("Error: neigborhood is not 3 or 4 " + map.getDesc() );
			}
			location.setCity(components[0]);
			location.setRegion(components[length-2]);
			location.setCountry(components[length-1]);
		}
		else if ("city/town".equals(precision)) {
			if (! (length == 4 || length == 3)) {
				System.out.println("Error: city is not 3 or 4 " + map.getDesc() );
			}
			location.setCity(components[0]);
			location.setRegion(components[length-2]);
			location.setCountry(components[length-1]);
		}
		else if ("county/district".equals(precision)) {
			if (length != 3) {
				System.out.println("Error: county is not 3 " + map.getDesc() );
			}
			location.setRegion(components[length-2]);
			location.setCountry(components[length-1]);
		}
		else if ("state/province".equals(precision)) {
			if (length != 2) {
				System.out.println("Error: state is not 2 " + map.getDesc() );
			}
			location.setRegion(components[length-2]);
			location.setCountry(components[length-1]);
		}
		else if ("country".equals(precision)) {
			location.setCountry(map.getDesc());
		}
		else {
			System.out.println("######## " + map.getPrecision());
		}
		
		Coordinates coordinates = new Coordinates();
			coordinates.setLatitude(map.getLatitude());
			coordinates.setLongitude(map.getLongitude());
			
		location.setCoordinates(coordinates);
		
		return location;
	}

}
