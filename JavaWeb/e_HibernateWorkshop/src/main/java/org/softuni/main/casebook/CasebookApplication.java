package org.softuni.main.casebook;

import org.softuni.main.casebook.handlers.utility.ErrorHandler;
import org.softuni.main.casebook.handlers.utility.ResourceHandler;
import org.softuni.main.casebook.utils.HandlerLoader;
import org.softuni.main.javache.Application;
import org.softuni.main.javache.http.HttpContext;
import org.softuni.main.javache.http.HttpResponse;
import org.softuni.main.javache.http.HttpSession;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class CasebookApplication implements Application {
    private HttpSession session;

    private HashMap<String, HashMap<String, Function<HttpContext, byte[]>>> routesTable;

    private final ErrorHandler errorHandler = new ErrorHandler();

    private final ResourceHandler resourceHandler = new ResourceHandler();

    public CasebookApplication() {
        this.initializeRoutes();
    }

    private void loadActionsForMethod(String method, HandlerLoader handlerLoader) {
        Map<String, Method> actions = handlerLoader.retrieveActionsMap(method);

        for (Map.Entry<String, Method> actionEntry : actions.entrySet()) {
            try {
                Object handlerObject = actionEntry.getValue()
                        .getDeclaringClass()
                        .getConstructor()
                        .newInstance();

                this.routesTable.putIfAbsent(actionEntry.getKey(), new HashMap<>());

                this.routesTable.get(actionEntry.getKey()).put(method, (HttpContext httpContext) -> {
                    try {
                        return ((HttpResponse) actionEntry
                                .getValue()
                                .invoke(handlerObject,
                                        httpContext.getHttpRequest(),
                                        httpContext.getHttpResponse())).getBytes();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    return null;
                });
            } catch (Exception e) { e.printStackTrace();}
        }
    }

    private void initializeRoutes() {
        this.routesTable = new HashMap<>();

        HandlerLoader handlerLoader = new HandlerLoader();

        this.loadActionsForMethod("GET", handlerLoader);
        this.loadActionsForMethod("POST", handlerLoader);
    }

    @Override
    public Map<String, HashMap<String, Function<HttpContext, byte[]>>> getRoutes() {
        return Collections.unmodifiableMap(this.routesTable);
    }

    @Override
    public byte[] handleRequest(HttpContext httpContext) {
        String requestMethod = httpContext.getHttpRequest().getMethod();
        String requestUrl = httpContext.getHttpRequest().getRequestUrl();

        if (this.getRoutes().containsKey(requestUrl) &&
                this.getRoutes().get(requestUrl).containsKey(requestMethod)) {
            return this.getRoutes().get(requestUrl).get(requestMethod).apply(httpContext);
        } else {
            HttpResponse httpResponse = this
                    .resourceHandler
                    .getResource(httpContext.getHttpRequest(), httpContext.getHttpResponse());

            if(httpResponse == null) {
                return this.errorHandler
                        .notFound(httpContext.getHttpRequest(), httpContext.getHttpResponse())
                        .getBytes();
            }

            return httpResponse.getBytes();
        }
    }

    @Override
    public HttpSession getSession() {
        return this.session;
    }

    @Override
    public void setSession(HttpSession session) {
        this.session = session;
    }
}
