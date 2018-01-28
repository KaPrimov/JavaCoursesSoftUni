package org.softuni.main.javache.http;

public class HttpContextImpl implements HttpContext {

	private HttpRequest httpRequest;
	private HttpResponse httpResponse;	
	
	public HttpContextImpl(HttpRequest httpRequest, HttpResponse httpResponse) {
		this.setHttpResponse(httpResponse);
		this.setHttpRequest(httpRequest);
	}

	@Override
	public HttpRequest getHttpRequest() {
		return this.httpRequest;
	}

	@Override
	public HttpResponse getHttpResponse() {
		return this.httpResponse;
	}

	public void setHttpRequest(HttpRequest httpRequest) {
		this.httpRequest = httpRequest;
	}

	public void setHttpResponse(HttpResponse httpResponse) {
		this.httpResponse = httpResponse;
	}
}
