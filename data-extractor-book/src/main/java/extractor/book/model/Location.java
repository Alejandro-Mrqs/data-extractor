package extractor.book.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Location {
	private String text;
	private String town;
	private String city;
	private String region;
	private String country;
	private Coordinates coordinates;
	
	
	public String getText() { return text; }
	public void setText(String text) { this.text = text.trim(); }
	
	public String getTown() { return town; }
	public void setTown(String town) { this.town = town.trim(); }
	
	public String getCity() { return city; }
	public void setCity(String city) { this.city = city.trim(); }
	
	public String getRegion() { return region; }
	public void setRegion(String region) { this.region = region.trim(); }
	
	public String getCountry() { return country; }
	public void setCountry(String country) { this.country = country.trim(); }
	
	public Coordinates getCoordinates() { return coordinates; }
	public void setCoordinates(Coordinates coordinates) { this.coordinates = coordinates; }
}
