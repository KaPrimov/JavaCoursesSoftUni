package org.softuni.main.javache;

import java.util.Map;
import java.util.function.Function;

import org.softuni.main.javache.http.HttpContext;
import org.softuni.main.javache.http.HttpSession;

public interface Application {
	byte[] handleRequest(HttpContext httpContext);
	
	Map<String, Function<HttpContext, byte[]>> getRoutes();

	HttpSession getSession();
	
	void setSession(HttpSession session);

}
