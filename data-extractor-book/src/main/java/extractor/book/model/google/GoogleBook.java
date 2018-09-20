package extractor.book.model.google;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class GoogleBook {
	private Integer totalItems;
	private List<GoogleItem> items;
	
	
	public Integer getTotalItems() { return totalItems; }
	public void setTotalItems(Integer totalItems) { this.totalItems = totalItems; }
	
	public List<GoogleItem> getItems() { return items; }
	public void setItems(List<GoogleItem> items) { this.items = items; }
}
