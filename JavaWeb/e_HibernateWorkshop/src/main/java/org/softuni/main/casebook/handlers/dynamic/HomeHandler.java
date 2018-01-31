package org.softuni.main.casebook.handlers.dynamic;

import org.softuni.main.casebook.annotations.ApplicationRequestHandler;
import org.softuni.main.casebook.annotations.Get;
import org.softuni.main.casebook.annotations.Post;
import org.softuni.main.javache.http.HttpRequest;
import org.softuni.main.javache.http.HttpResponse;
import org.softuni.main.javache.http.HttpStatus;

@ApplicationRequestHandler
public class HomeHandler extends BaseHandler {
    @Get(route = "/")
    public HttpResponse index(HttpRequest request, HttpResponse response) {
        response.setStatusCode(HttpStatus.OK);
        response.addHeader("Content-Type", "text/html");
        response.setContent(this.getView("index").getBytes());
        return response;
    }
}
