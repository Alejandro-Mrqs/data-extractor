package extractor.book.transformer;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import extractor.book.model.CsvBook;

public class CSVToBook extends CSVToObject<CsvBook>{
	
	private static final Logger logger = Logger.getLogger(CSVToObject.class);

	@Override
	public	CsvBook transform(String[] data) {
		CsvBook book = new CsvBook();
		
		book.setIsbn(getIsbn(data[5]));
			book.setAuthor(getAuthor(data[7]));
			book.setPublicationYear(getPublicationYear(data[8]));
			book.setOriginalTitle(data[9]);
			book.setTitle(data[10]);
			book.setLanguageCode(data[11]);
			book.setRating(getRating(data[12]));;
			book.setRatingCount(getRatingCount(data[13]));
			book.setImageUrl(data[21]);
			
		return book;
	}
	
	private String getIsbn (String data) {
		return StringUtils.leftPad(data, 10, '0');
	}
	
	private List<String> getAuthor (String data) {
		return Arrays.asList(data.split(","));
	}
	
	private Integer getPublicationYear (String data) {
		Float decimalYear = getFloat("publication year", data);
		return null != decimalYear? decimalYear.intValue() : null;
	}
	
	private Float getRating (String data) {
		return getFloat("rating", data);
	}
	
	private Long getRatingCount (String data) {
		return getLong("rating count", data);
	}

	
	protected Integer getInteger (String name, String data) {
		Integer number = null;
		try {
			number = Integer.parseInt(data);
		}
		catch (Exception exception) {
			logger.warn(String.format("Wrong %s format \"%s\"", name, data));
		}
		return number;
	}

	
	protected Long getLong (String name, String data) {
		Long number = null;
		try {
			number = Long.parseLong(data);
		}
		catch (Exception exception) {
			logger.warn(String.format("Wrong %s format \"%s\"", name, data));
		}
		return number;
	}

	
	protected Float getFloat (String name, String data) {
		Float number = null;
		try {
			number = Float.parseFloat(data);
		}
		catch (Exception exception) {
			logger.warn(String.format("Wrong %s format \"%s\"", name, data));
		}
		return number;
	}
}
