package extractor.book.model.wikipedia;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class WikipediaSearchQuery {
	
	private List<WikipediaSearchResult> search;

	
	public List<WikipediaSearchResult> getSearch() { return search; }
	public void setSearch(List<WikipediaSearchResult> search) { this.search = search; }
}
