package javache.http;

import java.util.Map;

public interface HttpRequest {
	Map<String, String> getHeaders();
	
	Map<String, String> getBodyParameters();
	
	String getMethod();
	
	void setMethod(String method);
	
	String getRequestUri();
	
	void setRequestUri(String requestUri);
	
	void addHeader(String header, String value);
	
	void addBodyParameter(String parameter, String value);
	
	boolean isResource();
}
