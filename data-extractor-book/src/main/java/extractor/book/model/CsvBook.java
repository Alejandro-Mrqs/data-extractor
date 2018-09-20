package extractor.book.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class CsvBook {
	private String isbn;
	private String originalTitle;
	private String title;
	private List<String> author;
	private Integer publicationYear;
	private String languageCode;
	private Float rating;
	private Long ratingCount;
	private String imageUrl;
	
	
	public String getIsbn() { return isbn; }
	public void setIsbn(String isbn) { this.isbn = isbn; }
	
	public String getOriginalTitle() { return originalTitle; }
	public void setOriginalTitle(String originalTitle) { this.originalTitle = originalTitle; }
	
	public String getTitle() { return title; }
	public void setTitle(String title) { this.title = title; }
	
	public List<String> getAuthor() { return author; }
	public void setAuthor(List<String> author) { this.author = author; }
	
	public Integer getPublicationYear() { return publicationYear; }
	public void setPublicationYear(Integer publicationYear) { this.publicationYear = publicationYear; }
	
	public String getLanguageCode() { return languageCode; }
	public void setLanguageCode(String languageCode) { this.languageCode = languageCode; }
	
	public Float getRating() { return rating; }
	public void setRating(Float rating) { this.rating = rating; }
	
	public Long getRatingCount() { return ratingCount; }
	public void setRatingCount(Long ratingCount) { this.ratingCount = ratingCount; }
	
	public String getImageUrl() { return imageUrl; }
	public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
}
