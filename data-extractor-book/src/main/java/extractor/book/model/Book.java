package extractor.book.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Book {
	
	private String isbn;
	private String isbn13;

	private String title;
	private String subtitle;
	private List<String> alternateTitle;
	
	private List<String> author;
	private Author mainAuthor;

	private String publisher;
	private Integer publicationYear;
	private String publishedDate;

	private String languageCode;
	private String language;

	private Integer pageCount;
	private List<String> categories;
	private String description;
	
	private Float rating;
	private Float googleRating;
	private Long ratingCount;
	private Long googleRatingCount;
	
	private String imageUrl;
	
	
	public String getIsbn() { return isbn; }
	public void setIsbn(String isbn) { this.isbn = isbn; }
	
	public String getIsbn13() { return isbn13; }
	public void setIsbn13(String isbn13) { this.isbn13 = isbn13; }
	
	
	public String getTitle() { return title; }
	public void setTitle(String title) { this.title = title; }
	
	public String getSubtitle() { return subtitle; }
	public void setSubtitle(String subtitle) { this.subtitle = subtitle; }
	
	public List<String> getAlternateTitle() { return alternateTitle; }
	public void setAlternateTitle(List<String> alternateTitle) { this.alternateTitle = alternateTitle; }
	public void addAlternateTitle(String alternateTitle) { 
		if (null == this.alternateTitle) {
			this.alternateTitle = new ArrayList<>();
		}
		this.alternateTitle.add(alternateTitle); 
	}
	
	
	public List<String> getAuthor() { return author; }
	public void setAuthor(List<String> author) { this.author = author; }

	public Author getMainAuthor() { return mainAuthor; }
	public void setMainAuthor(Author mainAuthor) { this.mainAuthor = mainAuthor; }
	
	
	public String getPublisher() { return publisher; }
	public void setPublisher(String publisher) { this.publisher = publisher; }
	
	public Integer getPublicationYear() { return publicationYear; }
	public void setPublicationYear(Integer publicationYear) { this.publicationYear = publicationYear; }
	
	public String getPublishedDate() { return publishedDate; }
	public void setPublishedDate(String publishedDate) { this.publishedDate = publishedDate; }
	
	
	public String getLanguageCode() { return languageCode; }
	public void setLanguageCode(String languageCode) { this.languageCode = languageCode; }
	
	public String getLanguage() { return language; }
	public void setLanguage(String language) { this.language = language; }
	
	
	public Integer getPageCount() { return pageCount; }
	public void setPageCount(Integer pageCount) { this.pageCount = pageCount; }
	
	public List<String> getCategories() { return categories; }
	public void setCategories(List<String> categories) { this.categories = categories; }
	
	
	public String getDescription() { return description; }
	public void setDescription(String description) { this.description = description; }
	
	
	public Float getRating() { return rating; }
	public void setRating(Float rating) { this.rating = rating; }


	public Float getGoogleRating() { return googleRating; }
	public void setGoogleRating(Float googleRating) { this.googleRating = googleRating; }
	
	public Long getRatingCount() { return ratingCount; }
	public void setRatingCount(Long ratingCount) { this.ratingCount = ratingCount; }
	
	public Long getGoogleRatingCount() { return googleRatingCount; }
	public void setGoogleRatingCount(Long googleRatingCount) { this.googleRatingCount = googleRatingCount; }
	
	
	public String getImageUrl() { return imageUrl; }
	public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
}
