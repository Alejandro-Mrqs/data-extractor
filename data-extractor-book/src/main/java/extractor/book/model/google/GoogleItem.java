package extractor.book.model.google;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class GoogleItem {
	
	private GoogleVolumeInfo volumeInfo;

	
	public GoogleVolumeInfo getVolumeInfo() { return volumeInfo; }
	public void setVolumeInfo(GoogleVolumeInfo volumeInfo) { this.volumeInfo = volumeInfo; }
}
