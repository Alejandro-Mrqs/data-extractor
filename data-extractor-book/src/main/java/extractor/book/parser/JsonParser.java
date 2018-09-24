package extractor.book.parser;

import com.fasterxml.jackson.core.type.TypeReference;
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
	
    public static <T> T getObject (String json, TypeReference<T> typeReference) throws Exception {
    	
    	if (null == json) {
    		return null;
    	}
    	
    	if (null == typeReference) {
			throw new Exception("Missing json type reference");
    	}
    	
    	try {
    		// Parses the json as an object of the given class 
			return mapper.readValue(json, typeReference);
		}
    	catch (Exception exception) {
			throw new Exception("Error while parsing Json as object (Reason: " + exception.getMessage() + ")");
		}
    }
}
