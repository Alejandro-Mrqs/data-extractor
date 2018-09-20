package extractor.book.model.wikipedia;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class WikipediaQuery {
	
	private WikipediaQueryParse parse;

	
	public WikipediaQueryParse getParse() { return parse; }
	public void setParse(WikipediaQueryParse parse) { this.parse = parse; }
}
