package extractor.book.model.wikipedia;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class WikipediaSearch {
	
	private WikipediaSearchQuery query;

	
	public WikipediaSearchQuery getQuery() { return query; }
	public void setQuery(WikipediaSearchQuery query) { this.query = query; }
}
