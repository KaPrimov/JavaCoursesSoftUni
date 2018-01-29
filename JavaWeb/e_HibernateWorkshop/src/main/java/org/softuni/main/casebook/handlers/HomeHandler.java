package org.softuni.main.casebook.handlers;

import org.softuni.main.javache.http.HttpRequest;
import org.softuni.main.javache.http.HttpResponse;
import org.softuni.main.javache.http.HttpStatus;

public class HomeHandler {
	public HttpResponse index(HttpRequest request, HttpResponse response) {
		response.setStatusCode(HttpStatus.OK);
		response.addHeader("Content-Type", "text/html");
		response.setContent("<h1>Hello index</h1>".getBytes());
		
		return response;
	}
}
