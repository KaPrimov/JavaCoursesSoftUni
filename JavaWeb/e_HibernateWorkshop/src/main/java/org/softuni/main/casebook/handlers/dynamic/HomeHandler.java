package org.softuni.main.casebook.handlers.dynamic;

import org.softuni.main.casebook.annotations.ApplicationRequestHandler;
import org.softuni.main.casebook.annotations.Get;
import org.softuni.main.javache.http.HttpRequest;
import org.softuni.main.javache.http.HttpResponse;
import org.softuni.main.javache.http.HttpSessionStorage;
import org.softuni.main.javache.http.HttpStatus;

@ApplicationRequestHandler
public class HomeHandler extends BaseDynamicHandler {
	 protected HomeHandler(HttpSessionStorage sessionStorage) {
	        super(sessionStorage);
	    }

	    @Get(route = "/")
	    public HttpResponse index(HttpRequest request, HttpResponse response) {
	        return this.view("index", request, response);
	    }

	    @Get(route = "/home")
	    public HttpResponse home(HttpRequest request, HttpResponse response) {
	        if(!this.isLoggedIn(request)) {
	            return this.redirect("/login", request, response);
	        }

	        return this.view("home", request, response);
	    }

	    @Get(route = "/all")
	    public HttpResponse all(HttpRequest request, HttpResponse response) {
	        if(!this.isLoggedIn(request)) {
	            return this.redirect("/login", request, response);
	        }

	        response.setStatusCode(HttpStatus.OK);

	        response.addHeader("Content-Type", "text/html");

	        StringBuilder asd = new StringBuilder();

	        asd.append("<h1>Pesho</h1>").append(System.lineSeparator());
	        asd.append("<h1>Gosho</h1>").append(System.lineSeparator());
	        asd.append("<h1>Tosho</h1>").append(System.lineSeparator());
	        asd.append("<h1>Sasho</h1>").append(System.lineSeparator());

	        response.setContent(asd.toString().getBytes());

	        return response;
	    }
}
