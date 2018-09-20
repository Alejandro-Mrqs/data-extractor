package extractor.book.run;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import extractor.book.client.GoogleBooksClient;
import extractor.book.client.WikipediaClient;
import extractor.book.model.Book;
import extractor.book.model.CsvBook;
import extractor.book.model.google.GoogleBook;
import extractor.book.model.utils.Crono;
import extractor.book.model.utils.ProcessInfo;
import extractor.book.model.wikipedia.WikipediaWriterInfo;
import extractor.book.parser.JsonParser;
import extractor.book.reader.CSVLineReader;
import extractor.book.transformer.CSVToBook;
import extractor.book.transformer.EnrichBookInfo;
import extractor.book.writer.FileWriter;

public class ProcessBooks {
	
	private static final Logger logger = Logger.getLogger(ProcessBooks.class);

	private static final String CSV_FILE = "data/books.csv";
	private static final String ELASTIC_OPERATION = "{ \"index\" : { \"_index\" : \"books\", \"_type\" : \"book\", \"_id\" : \"%s\" } }";
	
	private static boolean getGoogleInfo = true;
	private static boolean getWikipediaInfo = true;
	
//	private static int waitMillis = 1000;
	private static int limit = -1;
	private static int infoFrequency = 100;

	private static ProcessInfo processInfo = new ProcessInfo();
	private static Crono processCrono = new Crono();
	private static Crono bookCrono = new Crono();
	private static Crono functionCrono = new Crono();
	private static Map<String, Long> functionTimes = new HashMap<>();
	
	private static FileWriter locationsFile;
	private static FileWriter booksFile;
	private static FileWriter completeBooksFile;
	private static FileWriter elasticCompleteBooksFile;

	private static CSVLineReader<CsvBook> csvReader;
	
	public static void main(String[] args) throws Exception {
		
		initialize();
		
		CsvBook csvBook = null;
		while (null != (csvBook = csvReader.readLine()) && (limit ==-1 || processInfo.getProcessed() < limit)) {
			try {
//				Crono crono = new Crono();
				ProcessBook(csvBook);
//				long ellapsed = crono.get();
				
//				if (ellapsed < waitMillis) {
//					Thread.sleep(waitMillis - ellapsed);
//				}
				
				if (0 == processInfo.getProcessed() % infoFrequency) {
					logger.info("Process info: \n\t" + processInfo.toString() + "\n\t" + processInfo.getAverageTimes());
				}
			}
			catch (Exception exception) {
				logger.warn("Error processing book \"" + csvBook.getOriginalTitle() + " [" + csvBook.getIsbn() + "]. Reason: " + exception.getMessage());
			}
		}
		finish();
		System.out.println("END! [Ellapsed time: " + processCrono.get() + "]");
	}
	
	private static void initialize () throws Exception {
		csvReader = new CSVLineReader<CsvBook>(CSV_FILE, new CSVToBook());
		
		locationsFile = new FileWriter("locations.csv");
		booksFile = new FileWriter("books.json");
		completeBooksFile = new FileWriter("complete-books.json");
		elasticCompleteBooksFile = new FileWriter("elastic-complete-books.txt");
		
		logger.info("Book processor initialized");
	}
	
	private static void finish () throws Exception {
		locationsFile.finish();
		booksFile.finish();
		completeBooksFile.finish();
		elasticCompleteBooksFile.finish();
	}
	
	private static void ProcessBook (CsvBook csvBook) throws Exception {
		
		boolean hasGoogleInfo = false;
		boolean hasWikipediaInfo = false;
		boolean hasWikipediaCompleteInfo = false;
		
		bookCrono.set();
		functionCrono.set();
		
		Book book = EnrichBookInfo.getBookFromCSV(csvBook);
		processInfo.increaseProcessed();
		functionTimes.put("csv", functionCrono.get());
		
		
		if (getGoogleInfo && null != book.getIsbn()) {
			GoogleBook googleBook = GoogleBooksClient.getBookInfoByIsbn(book.getIsbn(), processInfo);
			if (null != googleBook) {
				EnrichBookInfo.addGoogleInfo(book, googleBook);
				hasGoogleInfo = true;
			}
			functionTimes.put("google", functionCrono.get());
		}
		
		if (getWikipediaInfo && null != book.getAuthor() && !book.getAuthor().isEmpty()) {
			String mainAuthor = book.getAuthor().get(0);
			WikipediaWriterInfo writerInfo = WikipediaClient.getWriterInfo(mainAuthor, processInfo);
			if (null != writerInfo) {
				EnrichBookInfo.addWikipediaInfo(book, writerInfo);
				hasWikipediaInfo = true;
				if (null != writerInfo.getBirthPlace()) {
					hasWikipediaCompleteInfo = true;
				}
			}
			functionTimes.put("wikipedia", functionCrono.get());
		}
		
		String bookJson = JsonParser.getJson(book);
		functionTimes.put("json", functionCrono.get());
		
		booksFile.writeLine(bookJson);
		functionTimes.put("write-base", functionCrono.get());
		
		if (hasWikipediaCompleteInfo) {
			locationsFile.writeLine("\"" + book.getMainAuthor().getBirthPlace().getText() + "\"");
			functionTimes.put("write-location", functionCrono.get());
		}
		
		if (hasGoogleInfo && hasWikipediaCompleteInfo) {
			processInfo.increaseCompleteInfo();
			completeBooksFile.writeLine(bookJson);
			functionTimes.put("write-complete", functionCrono.get());
			elasticCompleteBooksFile.writeLine(String.format(ELASTIC_OPERATION, book.getIsbn()));
			elasticCompleteBooksFile.writeLine(bookJson);
			functionTimes.put("write-elastic", functionCrono.get());
		}
		
		long ellapsed = bookCrono.get();
		
		logger.info("Processed" + 
				" [Google: " + hasGoogleInfo + " / Wikipedia: " + hasWikipediaInfo + " / Wikipedia birthplace: " + hasWikipediaCompleteInfo + "]" + 
				" \t(" + ellapsed + "ms)" + 
				" \t\"" + book.getTitle() + "\"" +
				" [isbn: " + book.getIsbn() + " / author: " + (null != book.getMainAuthor()? book.getMainAuthor().getName() : book.getAuthor()) + "]" + 
				" Total: " + processInfo.getProcessed());
		
		if (ellapsed > 2000) {
			logger.warn("Slow process: " + functionTimes);
		}
	}
}
