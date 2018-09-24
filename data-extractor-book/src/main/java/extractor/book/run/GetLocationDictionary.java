package extractor.book.run;

import extractor.book.utils.LocationsDictionary;

public class GetLocationDictionary {

	
	public static void main(String[] args) throws Exception {

		LocationsDictionary locationsDictionary = new LocationsDictionary("data/map-locations.csv");
		System.out.println(locationsDictionary.getLocation("[[Salamis Island|Salamis]]".toLowerCase()).getCountry());
		
		System.out.println("END!");
	}
}