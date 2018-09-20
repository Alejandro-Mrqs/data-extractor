package extractor.book.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Coordinates {
		
	private Long longitude;
	private Long latitude;
	
	
	public Long getLongitude() { return longitude; }
	public void setLongitude(Long longitude) { this.longitude = longitude; }
	
	public Long getLatitude() { return latitude; }
	public void setLatitude(Long latitude) { this.latitude = latitude; }
}
