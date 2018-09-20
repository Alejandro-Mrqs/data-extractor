package extractor.book.client;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import extractor.book.model.google.GoogleBook;
import extractor.book.model.utils.ProcessInfo;

public class GoogleBooksClient {
	
	private static final Logger logger = Logger.getLogger(GoogleBooksClient.class);
	
	private static final String GOOGLE_API = "https://www.googleapis.com/books/v1/volumes";
	private static RestClient<GoogleBook> googleClient = new RestClient<GoogleBook>(GOOGLE_API, GoogleBook.class);
	
	
	public static GoogleBook getBookInfoByIsbn (String isbn, ProcessInfo processInfo)  {
		GoogleBook googleBook = null;
		if (null != isbn) {
			long start = System.currentTimeMillis();
			try {
				processInfo.increaseGoogleQueries();
				googleBook = googleClient.getJsonResponseAsObject(getIsbnAsMap(isbn));
				if (null != googleBook && null != googleBook.getTotalItems() && googleBook.getTotalItems() > 0) {
					processInfo.increaseGoogleInfo();
				}
				else {
					return null;
				}
			}
			catch (Exception exception) {
				logger.warn("Error while trying to recover Google Books info for " + isbn + " (Reason: " + exception.getMessage() + ")");
			}
			long ellapsed = System.currentTimeMillis() - start;
			if (ellapsed > 2000) {
				logger.warn("Google query waited for " + ellapsed + "ms");
			}
			processInfo.addGoogleQueryTime(ellapsed);
		}
		else {
			logger.warn("Null isbn received");
		}
		
		return googleBook;
	}
	
	private static Map<String, String> getIsbnAsMap (String isbn){
		Map<String, String> isbnMap = new HashMap<>();
		isbnMap.put("q", "isbn:" + isbn);
		return isbnMap;
	}
}
