package extractor.book.transformer;

import org.apache.log4j.Logger;

import extractor.book.model.mapquest.MapQuestLocation;

public class CSVToLocation extends CSVToObject<MapQuestLocation>{
	
	private static final Logger logger = Logger.getLogger(CSVToObject.class);

	@Override
	public	MapQuestLocation transform(String[] data) {
		MapQuestLocation location = new MapQuestLocation();		
			location.setLatitude(getDouble("latitude", data[0]));
			location.setLongitude(getDouble("longitude", data[1]));
			location.setOriginalName(data[2]);
			location.setName(data[3]);
			location.setDesc(data[4]);
			location.setPrecision(data[7]);
		
		return location;
	}
	
	protected Double getDouble (String name, String data) {
		Double number = null;
		try {
			number = Double.parseDouble(data);
		}
		catch (Exception exception) {
			logger.warn(String.format("Wrong %s format \"%s\"", name, data));
		}
		return number;
	}
}