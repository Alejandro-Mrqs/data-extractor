package extractor.book.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Author {
	private String name;
	private Date birthDate; 
	private Location birthPlace;
	
	
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }
	
	public Date getBirthDate() { return birthDate; }
	public void setBirthDate(Date birthDate) { this.birthDate = birthDate; }
	
	public Location getBirthPlace() { return birthPlace; }
	public void setBirthPlace(Location birthPlace) { this.birthPlace = birthPlace; }
}