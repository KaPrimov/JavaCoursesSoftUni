package javache.http;

import java.util.HashMap;
import java.util.Map;

public class HttpRequestImpl implements HttpRequest {

	private String method;
	private String requestUrl;
	private Map<String, String> headers;
	private Map<String, String> bodyParameters;

	public HttpRequestImpl(String requestContent) {
		this.headers = new HashMap<>();
		this.bodyParameters = new HashMap<>();
		this.processRequest(requestContent);
	}
	
	@Override
	public Map<String, String> getHeaders() {
		return this.headers;
	}

	@Override
	public Map<String, String> getBodyParameters() {
		return this.bodyParameters;
	}

	@Override
	public String getMethod() {
		return this.method;
	}

	@Override
	public void setMethod(String method) {
		this.method = method;
	}

	@Override
	public String getRequestUri() {
		return this.requestUrl;
	}

	@Override
	public void setRequestUri(String requestUri) {
		this.requestUrl = requestUri;
	}

	@Override
	public void addHeader(String header, String value) {
		this.headers.putIfAbsent(header, value);		
	}

	@Override
	public void addBodyParameter(String parameter, String value) {
		this.bodyParameters.putIfAbsent(parameter, value);		
	}

	@Override
	public boolean isResource() {		
		return this.requestUrl.contains(".");
	}

	private void processRequest(String requestContent) {
		String[] requestTokens = requestContent.split("\r\n");
		String[] requestData = requestTokens[0].split("\\s+");
		this.setMethod(requestData[0]);
		this.setRequestUri(requestData[1]);
		this.extractHeaders(requestTokens);
		this.extractBodyParams(requestTokens[requestTokens.length - 1]);
	}

	private void extractBodyParams(String bodyParams) {
		String[] bodyParamsTokens = bodyParams.split("&");
		for (String bodyParamToken : bodyParamsTokens) {
			String[] singleBodyParamData = bodyParamToken.split("=");
			this.addBodyParameter(singleBodyParamData[0], singleBodyParamData[1]);
		}
	}

	private void extractHeaders(String[] requestTokens) {
		for (int i = 1; i < requestTokens.length - 2; i++) {
				String[] headerTokens = requestTokens[i].split(":\\s+");
				this.addHeader(headerTokens[0], headerTokens[1]);
		}
	}

	
}
