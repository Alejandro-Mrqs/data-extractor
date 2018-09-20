package extractor.book.run;

import extractor.book.client.WikipediaClient;
import extractor.book.model.utils.ProcessInfo;
import extractor.book.model.wikipedia.WikipediaQuery;
import extractor.book.model.wikipedia.WikipediaSearch;
import extractor.book.model.wikipedia.WikipediaWriterInfo;
import extractor.book.parser.JsonParser;
import extractor.book.parser.WikipediaParser;

public class GetWikipediaInfo {
	
	private static String search = "J. K. ROWLING";

	public static void main(String[] args) throws Exception {
		ProcessInfo processInfo = new ProcessInfo();
		
		WikipediaSearch searchResult = WikipediaClient.search(search, processInfo);
		System.out.println(JsonParser.getJson(searchResult));
		
		String pageId = searchResult.getQuery().getSearch().get(0).getPageid().toString();
		WikipediaQuery queryResult = WikipediaClient.query(pageId, processInfo);
		WikipediaWriterInfo writerInfo = WikipediaParser.getWriterInfo(search, "", queryResult.getParse().getParseTree().get("*"));
		System.out.println(JsonParser.getJson(writerInfo));

		System.out.println(processInfo.toString() + " -> " + processInfo.getAverageTimes());
		System.out.println("END!");
	}
}
