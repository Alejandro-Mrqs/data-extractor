package extractor.book.run;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.fasterxml.jackson.core.type.TypeReference;

import extractor.book.client.GoogleMapsClient;
import extractor.book.model.Book;
import extractor.book.model.Location;
import extractor.book.parser.JsonParser;
import extractor.book.utils.LocationsDictionary;
import extractor.book.writer.FileWriter;

public class ReProcessBooks {
	
	private static final String BOOKS_FILE = "data/complete-books.json"; 
	
	private static BufferedReader bufferedReader;
	private static FileWriter improvedBooksFile;

	private static LocationsDictionary locationsDictionary;
	
	public static void main(String[] args) throws Exception {
		initialize();
		processBooks();
		finish();
		
		System.out.println("END!");
	}
	
	
	private static void initialize () throws Exception {
		InputStream inputStream = ReProcessBooks.class.getClassLoader().getResourceAsStream(BOOKS_FILE);
        if (null == inputStream) {
            throw new IOException("File '" + BOOKS_FILE + "' not found in resources");
        }
        bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
        improvedBooksFile = new FileWriter("improved-books.json");
        locationsDictionary = new LocationsDictionary("data/map-locations.csv");
	}
	
	private static void finish () throws Exception {
		bufferedReader.close();
		improvedBooksFile.finish();
	}
	
	
	private static void processBooks () throws Exception {
		String line = null;
		while (null != (line = bufferedReader.readLine())) {
			Book book = JsonParser.getObject(line, new TypeReference<Book>() {});
			if (!improveBookInfo(book)) {
				System.out.println("removed!");
				continue;
			}
			String bookJson = JsonParser.getJson(book);
			improvedBooksFile.writeLine(bookJson);
		}
	}
	
	private static boolean improveBookInfo (Book book) throws Exception {
		if (null == book.getDescription() || book.getDescription().trim().isEmpty() || 
				null == book.getMainAuthor() || null == book.getMainAuthor().getBirthPlace() || null == book.getMainAuthor().getBirthPlace().getText() || book.getMainAuthor().getBirthPlace().getText().trim().isEmpty()) {
			return false;
		}
		if (null == book.getTitle() || book.getTitle().trim().isEmpty() && null != book.getAlternateTitle()) {
			book.setTitle(book.getAlternateTitle().get(book.getAlternateTitle().size() - 1));
		}
		Location authorBrithPlace = GoogleMapsClient.query(book.getMainAuthor().getBirthPlace().getText(), locationsDictionary);
		if (null == authorBrithPlace) {
			System.out.println("Unable to find location for " + book.getMainAuthor().getBirthPlace().getText());
		}
		book.getMainAuthor().setBirthPlace(authorBrithPlace);
		return true;
	}

}
