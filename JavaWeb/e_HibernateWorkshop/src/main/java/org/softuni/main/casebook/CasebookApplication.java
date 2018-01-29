package org.softuni.main.casebook;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.softuni.main.casebook.utils.HandlerLoader;
import org.softuni.main.javache.Application;
import org.softuni.main.javache.http.HttpContext;
import org.softuni.main.javache.http.HttpResponse;
import org.softuni.main.javache.http.HttpSession;
import org.softuni.main.javache.http.HttpStatus;

public class CasebookApplication implements Application {
	
	private HttpSession session;
	
	private Map<String, Function<HttpContext, byte[]>> routesTable;

	public CasebookApplication() {
		this.initializeRoutes();
	}

	private void initializeRoutes() {
		this.routesTable = new HashMap<>();

		HandlerLoader loader = new HandlerLoader();
		Map<String, Method> getActions = loader.retrieveActions("GET");
		
		for (Map.Entry<String, Method> kvp : getActions.entrySet()) {
			try {
				Object handlerObj = kvp.getValue()
						.getDeclaringClass()
						.getConstructor()
						.newInstance();
				
				this.routesTable.put(kvp.getKey(), 
						(HttpContext httpContext) -> {
							if (httpContext.getHttpRequest().getMethod().equalsIgnoreCase("GET")) {
								
							}
							try {
								return ((HttpResponse) kvp
									.getValue()
									.invoke(handlerObj, httpContext.getHttpRequest(), httpContext.getHttpResponse())).getBytes();
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							return null;
						});
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	@Override
	public byte[] handleRequest(HttpContext httpContext) {
		String requestUrl = httpContext.getHttpRequest().getRequestUrl();
		String method = httpContext.getHttpRequest().getMethod();
		
		if(!this.getRoutes().containsKey(requestUrl)) {
			return this.notFound(httpContext).getContent();
		}
		
		return this.getRoutes().get(requestUrl).apply(httpContext);
	}	

	private HttpResponse notFound(HttpContext httpContext) {
		HttpResponse response = httpContext.getHttpResponse();
		response.setStatusCode(HttpStatus.NOT_FOUND);
		response.addHeader("Content-Type", "text/html");
		response.setContent("<h1>404 Not Found</h1>".getBytes());
		
		return response;
	}

	@Override
	public Map<String, Function<HttpContext, byte[]>> getRoutes() {
		return Collections.unmodifiableMap(this.routesTable);
	}

	@Override
	public HttpSession getSession() {
		return this.session;
	}

	@Override
	public void setSession(HttpSession session) {
		this.session = session;
		
	}

}
