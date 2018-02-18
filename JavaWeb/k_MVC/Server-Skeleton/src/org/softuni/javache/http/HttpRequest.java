package org.softuni.javache.http;

import java.util.Map;

public interface HttpRequest {
    String getMethod();

    String getRequestUrl();

    Map<String, String> getHeaders();

    Map<String, String> getBodyParameters();

    Map<String, String> getQueryParameters();

    Map<String, HttpCookie> getCookies();
}
