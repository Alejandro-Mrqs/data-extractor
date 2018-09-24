package extractor.book.run;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.fasterxml.jackson.core.type.TypeReference;

import extractor.book.model.Book;
import extractor.book.parser.JsonParser;
import extractor.book.writer.FileWriter;

public class BooksToElastic {
	
	private static final String BOOKS_FILE = "data/improved-books.json"; 
	private static final String ELASTIC_OPERATION = "{ \"index\" : { \"_index\" : \"books\", \"_type\" : \"book\", \"_id\" : \"%s\" } }";
	
	private static BufferedReader bufferedReader;
	private static FileWriter elasticBooksFile;

	public static void main(String[] args) throws Exception {
		initialize();
		processBooks();
		finish();
		
		System.out.println("END!");
	}
	
	
	private static void initialize () throws Exception {
		InputStream inputStream = BooksToElastic.class.getClassLoader().getResourceAsStream(BOOKS_FILE);
        if (null == inputStream) {
            throw new IOException("File '" + BOOKS_FILE + "' not found in resources");
        }
        bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
        elasticBooksFile = new FileWriter("elastic-books.json");
	}
	
	private static void finish () throws Exception {
		bufferedReader.close();
		elasticBooksFile.finish();
	}
	
	
	private static void processBooks () throws Exception {
		String line = null;
		while (null != (line = bufferedReader.readLine())) {
			Book book = JsonParser.getObject(line, new TypeReference<Book>() {});

			if (null == book.getIsbn() || book.getIsbn().isEmpty()) {
				System.out.println("Empty ISBN!!! " + line);
			}

			elasticBooksFile.writeLine(String.format(ELASTIC_OPERATION, book.getIsbn()));
			elasticBooksFile.writeLine(line);
		}
		elasticBooksFile.writeLine("");
	}
	
}
