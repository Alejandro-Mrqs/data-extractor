package extractor.book.model.google;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class GoogleVolumeInfo {
	
	private String title;
	private String subtitle;
	private List<String> authors;
	private String publisher;
	private String publishedDate;
	private String description;
	private List<GoogleId> industryIdentifiers;
	private Integer pageCount;
	private String printType;
	private List<String> categories;
	private Float averageRating;
	private Long ratingsCount;
	private String language;
	
	
	public String getTitle() { return title; }
	public void setTitle(String title) { this.title = title; }
	
	public String getSubtitle() { return subtitle; }
	public void setSubtitle(String subtitle) { this.subtitle = subtitle; }
	
	public List<String> getAuthors() { return authors; }
	public void setAuthors(List<String> authors) { this.authors = authors; }

	public String getPublisher() { return publisher; }
	public void setPublisher(String publisher) { this.publisher = publisher; }
	
	public String getPublishedDate() { return publishedDate; }
	public void setPublishedDate(String publishedDate) { this.publishedDate = publishedDate; }

	public String getDescription() { return description; }
	public void setDescription(String description) { this.description = description; }
	
	public List<GoogleId> getIndustryIdentifiers() { return industryIdentifiers; }
	public void setIndustryIdentifiers(List<GoogleId> industryIdentifiers) { this.industryIdentifiers = industryIdentifiers; }
	
	public Integer getPageCount() { return pageCount; }
	public void setPageCount(Integer pageCount) { this.pageCount = pageCount; }
	
	public String getPrintType() { return printType; }
	public void setPrintType(String printType) { this.printType = printType; }
	
	public List<String> getCategories() { return categories; }
	public void setCategories(List<String> categories) { this.categories = categories; }

	public Float getAverageRating() { return averageRating; }
	public void setAverageRating(Float averageRating) { this.averageRating = averageRating; }
	
	public Long getRatingsCount() { return ratingsCount; }
	public void setRatingsCount(Long ratingsCount) { this.ratingsCount = ratingsCount; }
	
	public String getLanguage() { return language; }
	public void setLanguage(String language) { this.language = language; }
}