package extractor.book.model.wikipedia;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class WikipediaQueryParse {

	private String title;
	private Map<String, String> parseTree;
	
	
	public String getTitle() { return title; }
	public void setTitle(String title) { this.title = title; }

	@JsonProperty("parsetree")
	public Map<String, String> getParseTree() { return parseTree; }
	public void setParseTree(Map<String, String> parseTree) { this.parseTree = parseTree; }
}
