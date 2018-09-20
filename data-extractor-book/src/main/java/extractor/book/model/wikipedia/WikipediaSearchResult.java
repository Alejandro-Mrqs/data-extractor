package extractor.book.model.wikipedia;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class WikipediaSearchResult {
	
	private String title;
	private Long pageid;
	
	
	public String getTitle() { return title; }
	public void setTitle(String title) { this.title = title; }
	
	public Long getPageid() { return pageid; }
	public void setPageid(Long pageid) { this.pageid = pageid; }
}
