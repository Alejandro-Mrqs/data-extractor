package extractor.book.client;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import extractor.book.model.utils.ProcessInfo;
import extractor.book.model.wikipedia.WikipediaQuery;
import extractor.book.model.wikipedia.WikipediaSearch;
import extractor.book.model.wikipedia.WikipediaSearchResult;
import extractor.book.model.wikipedia.WikipediaWriterInfo;
import extractor.book.parser.WikipediaParser;

public class WikipediaClient {
	
	private static final Logger logger = Logger.getLogger(WikipediaClient.class);

	private static final String ID_SEPARATOR = "<::>";
	
	private static Map<String, String> wikipediaWriterIds = new HashMap<>();
	private static Map<String, WikipediaWriterInfo> wikipediaWriterInfo = new HashMap<>();

	
	// Search properties
	private static String wikipediaSearchtarget = "https://en.wikipedia.org/w/api.php";
	private static RestClient<WikipediaSearch> searchClient = new RestClient<>(wikipediaSearchtarget, WikipediaSearch.class);
	private static Map<String, String> baseSearchParameters = new HashMap<String, String>() {
		private static final long serialVersionUID = 3038750476803856226L;
		{
			put("action", "query");
			put("format", "json");
			put("list", "search");
			put("srlimit", "1");
			put("srprop", "");
		}
	};

	// Query properties
	private static String wikipediaQuerytarget = "https://en.wikipedia.org/w/api.php";
	private static RestClient<WikipediaQuery> queryClient = new RestClient<>(wikipediaQuerytarget, WikipediaQuery.class);
	private static Map<String, String> baseQueryParameters = new HashMap<String, String>() {
		private static final long serialVersionUID = 8730937980485029630L;
		{
			put("action", "parse");
			put("format", "json");
			put("prop", "parsetree");
		}
	};

	
	public static WikipediaSearch search (String text, ProcessInfo processInfo) throws Exception {
		long start = System.currentTimeMillis();
		processInfo.increaseWikiPediaSearches();
		
		Map<String, String> parameters = new HashMap<>();
			parameters.putAll(baseSearchParameters);
			parameters.put("srsearch", text);
		WikipediaSearch wikipediaSearch = searchClient.getJsonResponseAsObject(parameters);

		long ellapsed = System.currentTimeMillis() - start;
		if (ellapsed > 2000) {
			logger.warn("Wikipedia search waited for " + ellapsed + "ms");
		}
		processInfo.addWikipediaSearchTime(ellapsed);
		return wikipediaSearch;
	}

	
	public static WikipediaQuery query (String id, ProcessInfo processInfo) throws Exception {
		long start = System.currentTimeMillis();
		processInfo.increaseWikipediaQueries();
		
		Map<String, String> parameters = new HashMap<>();
			parameters.putAll(baseQueryParameters);
			parameters.put("pageid", id);
		
		WikipediaQuery wikipediaQuery = queryClient.getJsonResponseAsObject(parameters);

		long ellapsed = System.currentTimeMillis() - start;
		if (ellapsed > 2000) {
			logger.warn("Wikipedia query waited for " + ellapsed + "ms");
		}
		processInfo.addWikipediaQueryTime(ellapsed);
		return wikipediaQuery; 
	}
	
	
	public static WikipediaWriterInfo getWriterInfo (String name, ProcessInfo processInfo) {
		if (null == name) {
			return null;
		}
		
		String pageId = null;
		WikipediaWriterInfo info = null;
		boolean cache = false;
		
		try {
			if (null != wikipediaWriterIds.get(name.toLowerCase())) {
				String[] writerId = wikipediaWriterIds.get(name.toLowerCase()).split(ID_SEPARATOR);
				if (writerId.length != 2) {
					throw new Exception("Invalid writer ID " + wikipediaWriterIds.get(name));
				}
				pageId = writerId[0];
				name = writerId[1];
				logger.info("Page ID for author \"" + name + "\" is already stored so there is no need to make the wikipedia search");
				cache = true;
			}
			else {
				WikipediaSearch search = search(name, processInfo);
				if (null != search && null != search.getQuery() && null != search.getQuery().getSearch() && !search.getQuery().getSearch().isEmpty()) {
					WikipediaSearchResult result = search.getQuery().getSearch().get(0);
					pageId = null != result.getPageid()? result.getPageid().toString() : null;
					String normalizedName = null != result.getTitle()? result.getTitle() : name;
					wikipediaWriterIds.put(name.toLowerCase(), pageId + ID_SEPARATOR + normalizedName);
					name = normalizedName;
				}
			}
		}
		catch (Exception exception) {
			logger.warn("Error searching \"" + name + "\" in wikipedia (Reason: " + exception.getMessage() + ")");
		}
		
		if (null != pageId) {		
			try {
				if (null != wikipediaWriterInfo.get(pageId)) {
					info = wikipediaWriterInfo.get(pageId);
					if (!cache) {
						logger.info("Info on wikipedia page \"" + pageId + "\" is already stored so there is no need to make the wikipedia query");
					}
				}
				else {
					WikipediaQuery query = query(pageId, processInfo);
					if (null != query && null != query.getParse() && null != query.getParse().getParseTree() && null != query.getParse().getParseTree().get("*")) {
						info = WikipediaParser.getWriterInfo(name, pageId, query.getParse().getParseTree().get("*"));
						wikipediaWriterInfo.put(pageId, info);
					}
				}
				if (null != info) {
					info.setName(name);
					processInfo.increaseWikiPediaInfo();
					if (null != info.getBirthPlace()) {
						processInfo.increaseWikiPediaCompleteInfo();
					}
				}
			} 
			catch (Exception exception) {
				logger.warn("Error querying page \"" + pageId + "\" in wikipedia (Reason: " + exception.getMessage() + ")");
			}
		}
		
		return info;
	}
	
}
