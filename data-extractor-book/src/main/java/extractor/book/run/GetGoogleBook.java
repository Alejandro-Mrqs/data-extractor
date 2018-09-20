package extractor.book.run;

import java.util.HashMap;
import java.util.Map;

import extractor.book.client.RestClient;
import extractor.book.model.google.GoogleBook;
import extractor.book.parser.JsonParser;

public class GetGoogleBook {

	private static String googleApi = "https://www.googleapis.com/books/v1/volumes";
	private static String isbn10 = "0618260307";
	
	public static void main(String[] args) throws Exception {

		Map<String, String> isbnMap = new HashMap<>();
		isbnMap.put("q", "isbn:" + isbn10);
		
		RestClient<GoogleBook> client = new RestClient<GoogleBook>(googleApi, GoogleBook.class);
		
		GoogleBook book = client.getJsonResponseAsObject(isbnMap);
		
		System.out.println(JsonParser.getJson(book));
	}
}
