package extractor.book.model.mapquest;

public class MapQuestLocation {

	private Double latitude;
	private Double longitude;
	private String originalName;
	private String name;
	
	private String desc;
	private String precision;
	
	public Double getLatitude() { return latitude; }
	public void setLatitude(Double latitude) { this.latitude = latitude; }
	
	public Double getLongitude() { return longitude; }
	public void setLongitude(Double longitude) { this.longitude = longitude; }
	
	public String getOriginalName() { return originalName; }
	public void setOriginalName(String originalName) { this.originalName = originalName; }
	
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }
	
	public String getDesc() { return desc; }
	public void setDesc(String desc) { this.desc = desc; }
	
	public String getPrecision() { return precision; }
	public void setPrecision(String precision) { this.precision = precision; }
}
