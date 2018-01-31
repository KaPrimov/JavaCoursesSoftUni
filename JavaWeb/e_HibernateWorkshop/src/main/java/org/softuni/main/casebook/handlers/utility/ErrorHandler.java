package org.softuni.main.casebook.handlers.utility;

import org.softuni.main.javache.http.HttpRequest;
import org.softuni.main.javache.http.HttpResponse;
import org.softuni.main.javache.http.HttpStatus;

public final class ErrorHandler {
    public final HttpResponse badRequest(HttpRequest httpRequest, HttpResponse httpResponse) {
        httpResponse.setStatusCode(HttpStatus.BAD_REQUEST);
        httpResponse.addHeader("Content-Type", "text/html");
        httpResponse.setContent("<h1>Error (400): Malformed request.</h1>".getBytes());

        return httpResponse;
    }

    public final HttpResponse notFound(HttpRequest httpRequest, HttpResponse httpResponse) {
        httpResponse.setStatusCode(HttpStatus.NOT_FOUND);
        httpResponse.addHeader("Content-Type", "text/html");
        httpResponse.setContent("<h1>Error (404): The page or resource you are looking for is invalid.</h1>".getBytes());

        return httpResponse;
    }
}
