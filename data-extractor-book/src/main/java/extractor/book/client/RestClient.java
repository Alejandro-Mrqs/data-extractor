package extractor.book.client;

import java.util.Map;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class RestClient <T> {
	
	private Client client;
	private String target;
	private Class<T> clazz;

	
	public RestClient(String target, Class<T> clazz) {
		client = ClientBuilder.newClient();
		this.target = target; 
		this.clazz = clazz;
	}
	
	public T getJsonResponseAsObject (Map<String, String> parameters) throws Exception {
		WebTarget webTarget = client.target(target);
		for (String key : parameters.keySet()) {
			webTarget = webTarget.queryParam(key, parameters.get(key));
		}
		
		Response response = webTarget.request(MediaType.APPLICATION_JSON).get();
		return response.readEntity(clazz);
	}
}
