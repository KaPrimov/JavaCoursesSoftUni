package org.softuni.main.javache;

import org.softuni.main.javache.http.*;

class RequestHandler {
    private HttpContext httpContext;

    private Application application;

    RequestHandler(Application application) {
        this.application = application;
    }

    byte[] handleRequest(String requestContent) {
        HttpRequest httpRequest = new HttpRequestImpl(requestContent);
        HttpResponse httpResponse = new HttpResponseImpl();

        this.httpContext = new HttpContextImpl(httpRequest, httpResponse);

        return this.application.handleRequest(this.httpContext);
    }
}
