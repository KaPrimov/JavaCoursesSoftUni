package org.softuni.main.javache;

import org.softuni.main.javache.http.HttpContext;
import org.softuni.main.javache.http.HttpSessionStorage;

public interface Application {
	 void initializeRoutes();

	    byte[] handleRequest(HttpContext httpContext);

	    HttpSessionStorage getSessionStorage();

	    void setSessionStorage(HttpSessionStorage sessionStorage);
}