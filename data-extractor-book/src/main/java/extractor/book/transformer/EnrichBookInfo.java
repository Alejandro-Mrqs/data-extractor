package extractor.book.transformer;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import extractor.book.model.Author;
import extractor.book.model.Book;
import extractor.book.model.CsvBook;
import extractor.book.model.Location;
import extractor.book.model.google.GoogleBook;
import extractor.book.model.google.GoogleId;
import extractor.book.model.google.GoogleItem;
import extractor.book.model.google.GoogleVolumeInfo;
import extractor.book.model.wikipedia.WikipediaWriterInfo;

public class EnrichBookInfo {
	
	private static final Logger logger = Logger.getLogger(EnrichBookInfo.class);
	
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	public static Book getBookFromCSV (CsvBook csvBook) {
		Book book = new Book();
		addCSVInfo(book, csvBook);
		return book;
	}
	
	public static void addCSVInfo (Book book, CsvBook csvBook) {
		if (null != csvBook) {
			book.setIsbn(csvBook.getIsbn());
			
			book.setTitle(normalizeString(csvBook.getOriginalTitle().trim()));
			book.addAlternateTitle(normalizeString(csvBook.getTitle()));
			
			book.setAuthor(csvBook.getAuthor());
			
			book.setPublicationYear(csvBook.getPublicationYear());
			
			book.setLanguageCode(csvBook.getLanguageCode());
			
			book.setRating(csvBook.getRating());
			book.setRatingCount(csvBook.getRatingCount());

			book.setImageUrl(csvBook.getImageUrl());
		}
	}
	
	public static void addGoogleInfo (Book book, GoogleBook googleBook) throws Exception {
		if (null == googleBook) {
			return;
		}
		
		Integer totalItems = googleBook.getTotalItems();
		GoogleVolumeInfo info = null;
		if (null != totalItems && totalItems > 0) {
			GoogleItem item = googleBook.getItems().get(0);
			
			if (null != item) {
				info = item.getVolumeInfo();
			}
		}
		
		if (null != info) {
			
			book.setIsbn13(getIsbn13(info));
			
			if (null != info.getTitle()) {
				book.addAlternateTitle(info.getTitle());
			}
			
			book.setSubtitle(info.getSubtitle());
			
			if (null != info.getAuthors()) {
				book.setAuthor(info.getAuthors());
			}
			
			book.setPublisher(info.getPublisher());
			book.setPublishedDate(normalizeDate(info.getPublishedDate()));
			
			book.setLanguage(info.getLanguage());
			
			book.setPageCount(info.getPageCount());
			book.setCategories(info.getCategories());
			book.setDescription(info.getDescription());
			
			book.setGoogleRating(info.getAverageRating());
			book.setGoogleRatingCount(info.getRatingsCount());
		}
	}
	
	public static void addWikipediaInfo (Book book, WikipediaWriterInfo writerInfo) throws Exception {
		if (null == writerInfo) {
			return;
		}
		
		Author author = new Author();
			author.setName(writerInfo.getName());

		if (null != writerInfo.getBirthPlace()) {
			Location location = new Location();
				location.setText(writerInfo.getBirthPlace());

			author.setBirthPlace(location);
		}
			
		book.setMainAuthor(author);
	}
	
	
	private static String getIsbn13 (GoogleVolumeInfo info) {
		List<GoogleId> identifiers = info.getIndustryIdentifiers();
		
		if (null != identifiers) {
			for (GoogleId identifier : identifiers) {
				if (null != identifier && "ISBN_13".equals(identifier.getType())) {
					return identifier.getIdentifier();
				}
			}
		}
		
		return null;
	}
	
	private static String normalizeString (String string) {
		return (null != string)? string.trim() : null;
	}
	
	private static String normalizeDate (String dateString) {
		Date date = null;
		if (null != dateString) {
			String[] dateParts = dateString.split("-");
			if (1 == dateParts.length) {
				// Only year is present so month and day are set
				dateString = dateParts[0] + "-01-01";
			}
			else if (2 == dateParts.length) {
				// Month and year are present so day is set
				dateString = dateParts[0] + "-" + dateParts[1] + "-01";
			}
			try {
				date = dateFormat.parse(dateString);
			}
			catch (Exception exception) {
				logger.warn("Wrong date format \"" + dateString + "\"" );
			}
		}
		return date != null? dateFormat.format(date) : null;
	}
}
