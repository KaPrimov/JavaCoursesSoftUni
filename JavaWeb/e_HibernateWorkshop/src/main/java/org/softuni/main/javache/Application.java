package org.softuni.main.javache;

import org.softuni.main.javache.http.HttpContext;
import org.softuni.main.javache.http.HttpSession;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public interface Application {
    Map<String, HashMap<String, Function<HttpContext, byte[]>>> getRoutes();

    byte[] handleRequest(HttpContext httpContext);

    HttpSession getSession();

    void setSession(HttpSession session);
}