package extractor.book.parser;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonParser{
	
	private static ObjectMapper mapper = new ObjectMapper();
	
	public static <T> String getJson (T object) throws Exception {
		try {
			return mapper.writeValueAsString(object);
		}
		catch (Exception exception) {
			throw new Exception("Error while parsing object as Json (Reason: " + exception.getMessage() + ")");
		}
	}
}
