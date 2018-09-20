package extractor.book.run;

import extractor.book.client.GoogleBooksClient;
import extractor.book.client.WikipediaClient;
import extractor.book.model.Book;
import extractor.book.model.CsvBook;
import extractor.book.model.google.GoogleBook;
import extractor.book.model.utils.ProcessInfo;
import extractor.book.model.wikipedia.WikipediaWriterInfo;
import extractor.book.parser.JsonParser;
import extractor.book.reader.CSVLineReader;
import extractor.book.transformer.CSVToBook;
import extractor.book.transformer.EnrichBookInfo;

public class GetBookInfo {

	
	public static void main(String[] args) throws Exception {
		CSVLineReader<CsvBook> reader = new CSVLineReader<CsvBook>("data/books.csv", new CSVToBook());
		ProcessInfo processInfo = new ProcessInfo();
		
		for (int i = 0; i < 10; i++) {
			CsvBook csvBook = reader.readLine();
			Book book = EnrichBookInfo.getBookFromCSV(csvBook);
			
			if (null != book.getIsbn()) {
				GoogleBook googleBook = GoogleBooksClient.getBookInfoByIsbn(book.getIsbn(), processInfo);
				if (null != googleBook) {
					EnrichBookInfo.addGoogleInfo(book, googleBook);
				}
			}
			
			if (null != book.getAuthor() && !book.getAuthor().isEmpty()) {
				String mainAuthor = book.getAuthor().get(0);
				WikipediaWriterInfo writerInfo = WikipediaClient.getWriterInfo(mainAuthor, processInfo);
				if (null != writerInfo) {
					EnrichBookInfo.addWikipediaInfo(book, writerInfo);
				}
			}
			
			System.out.println(JsonParser.getJson(book));
			Thread.sleep(1500);
		}
		
		System.out.println(processInfo.toString() + " -> " + processInfo.getAverageTimes());
		System.out.println("END!");
	}
}