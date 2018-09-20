package extractor.book.model.utils;

public class ProcessInfo {
	
	private int processed = 0;
	
	private int googleInfo = 0;
	private int googleQueries = 0;
	private long googleQueryTime = 0;
	
	private int wikiPediaInfo = 0;
	private int wikiPediaCompleteInfo = 0;
	private int wikipediaSearches = 0;
	private long wikipediaSearchTime = 0;
	private int wikipediaQueries = 0;
	private long wikipediaQueryTime = 0;
	
	private int completeInfo = 0;
	

	public int getProcessed() { return processed; }
	public void setProcessed(int processed) { this.processed = processed; }
	public void increaseProcessed() { this.processed++; }
	

	public int getGoogleInfo() { return googleInfo; }
	public void setGoogleInfo(int googleInfo) { this.googleInfo = googleInfo; }
	public void increaseGoogleInfo() { this.googleInfo++; }
	
	public int getGoogleQueries() { return googleQueries; }
	public void setGoogleQueries(int googleQueries) { this.googleQueries = googleQueries; }
	public void increaseGoogleQueries() { this.googleQueries++; }
	
	public long getGoogleQueryTime() { return googleQueryTime; }
	public void setGoogleQueryTime(long googleQueryTime) { this.googleQueryTime = googleQueryTime; }
	public void addGoogleQueryTime(long googleQueryTime) { this.googleQueryTime = + googleQueryTime; }
	
	
	public int getWikiPediaInfo() { return wikiPediaInfo; }
	public void setWikiPediaInfo(int wikiPediaInfo) { this.wikiPediaInfo = wikiPediaInfo; }
	public void increaseWikiPediaInfo() { this.wikiPediaInfo++; }

	public int getWikiPediaCompleteInfo() { return wikiPediaCompleteInfo; }
	public void setWikiPediaCompleteInfo(int wikiPediaCompleteInfo) { this.wikiPediaCompleteInfo = wikiPediaCompleteInfo; }
	public void increaseWikiPediaCompleteInfo() { this.wikiPediaCompleteInfo++; }
	
	public int getWikipediaSearches() { return wikipediaSearches; }
	public void setWikipediaSearches(int wikipediaSearches) { this.wikipediaSearches = wikipediaSearches; }
	public void increaseWikiPediaSearches() { this.wikipediaSearches++; }
	
	public long getWikipediaSearchTime() { return wikipediaSearchTime; }
	public void setWikipediaSearchTime(long wikipediaSearchTime) { this.wikipediaSearchTime = wikipediaSearchTime; }
	public void addWikipediaSearchTime(long wikipediaSearchTime) { this.wikipediaSearchTime = + wikipediaSearchTime; }
	
	public int getWikipediaQueries() { return wikipediaQueries; }
	public void setWikipediaQueries(int wikipediaQueries) { this.wikipediaQueries = wikipediaQueries; }
	public void increaseWikipediaQueries() { this.wikipediaQueries++; }
	
	public long getWikipediaQueryTime() { return wikipediaQueryTime; }
	public void setWikipediaQueryTime(long wikipediaQueryTime) { this.wikipediaQueryTime = wikipediaQueryTime; }
	public void addWikipediaQueryTime(long wikipediaQueryTime) { this.wikipediaQueryTime = + wikipediaQueryTime; }
	

	public int getCompleteInfo() { return completeInfo; }
	public void setCompleteInfo(int completeInfo) { this.completeInfo = completeInfo; }
	public void increaseCompleteInfo() { this.completeInfo++; }
	
	@Override
	public String toString() {
		return "[Total processed: " + processed + 
				" / Google Info: " + googleInfo + 
				" / Wikipedia Info: " + wikiPediaCompleteInfo + " (Queries: " + wikipediaQueries + " / Results: " + wikiPediaInfo +")" + 
				" / Complete Info: " + completeInfo + "]";
	}
	
	public String getAverageTimes () {
		return "Google [" + "Average query time: " + (googleQueries != 0? googleQueryTime / googleQueries : 0)  + "] / "
				+ "Wikipedia ["
					+ "Average search time: " + (wikipediaSearches != 0? wikipediaSearchTime / wikipediaSearches : 0)  + " / "
					+ "Average query time: " + (wikipediaQueries != 0? wikipediaQueryTime / wikipediaQueries : 0)  + "]";
	}
}
