package org.softuni.main.casebook.handlers.dynamic;

import org.softuni.main.casebook.annotations.ApplicationRequestHandler;
import org.softuni.main.casebook.annotations.Get;
import org.softuni.main.casebook.annotations.Post;
import org.softuni.main.javache.http.HttpRequest;
import org.softuni.main.javache.http.HttpResponse;
import org.softuni.main.javache.http.HttpStatus;

@ApplicationRequestHandler
public class UsersHandler extends BaseHandler {
    @Get(route = "/login")
    public HttpResponse login(HttpRequest request, HttpResponse response) {
        response.setStatusCode(HttpStatus.OK);
        response.addHeader("Content-Type", "text/html");
        response.setContent(this.getView("login").getBytes());
        return response;
    }

    @Post(route = "/login")
    public HttpResponse loginConfirm(HttpRequest request, HttpResponse response) {
        response.setStatusCode(HttpStatus.OK);
        response.addHeader("Content-Type", "text/html");
        response.setContent(("<h2>YOU POSTED!!!</h2>").getBytes());
        return response;
    }
}
