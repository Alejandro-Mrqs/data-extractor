package extractor.book.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import extractor.book.model.utils.Crono;
import extractor.book.model.wikipedia.WikipediaWriterInfo;

public class WikipediaParser {
	private static final Logger logger = Logger.getLogger(WikipediaParser.class);
	
	private static String birthPlacePatternString = ".*<name>\\s*birth_place\\s*</name><equals>\\s*=\\s*</equals><value>\\s*([^<]*)\\s*</value>.*";
	private static Pattern birthPlacePattern = Pattern.compile(birthPlacePatternString);
	
	
	public static WikipediaWriterInfo getWriterInfo(String name, String pageId, String wikipediaContent) {
		Crono crono = new Crono();
		WikipediaWriterInfo info = new WikipediaWriterInfo();
		addBirthPlaceFromContent(name, pageId, wikipediaContent, info);
		long ellapsed = crono.get();
		
		if (ellapsed > 2000) {
			logger.warn("Wikipedia parser spent " + ellapsed + "ms");
		}

		return info;
	}
	
	private static void addBirthPlaceFromContent(String name, String pageId, String content, WikipediaWriterInfo info) {
		content = getWriterInfoSnippet(content);
		if (null == content) {
			logger.warn("Missing infobox for \"" + name + "\" [" + pageId + "]");
		}
		else {
			Matcher matcher = birthPlacePattern.matcher(content);
			
			if (matcher.find()) {
				info.setBirthPlace(matcher.group(1));
			}
		}
	}
	
	private static String getWriterInfoSnippet (String content) {
		int infoBoxStart = content.indexOf("Infobox");
		if (-1 != infoBoxStart) {
			content = content.substring(infoBoxStart);
			int infoBoxEnd = content.indexOf("'''");
			if (-1 == infoBoxEnd) {
				infoBoxEnd = content.indexOf("<ext>");
			}
			if (-1 != infoBoxEnd) {
				content = content.substring(0, infoBoxEnd);
			}
		}
		else {
			return null;
		}
		
		content = content.replaceAll("\n", "");
		return content;
	}
}
