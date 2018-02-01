package org.softuni.main.casebook;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.softuni.main.casebook.handlers.BaseHandler;
import org.softuni.main.casebook.handlers.utility.ResourceHandler;
import org.softuni.main.casebook.utils.HandlerLoader;
import org.softuni.main.javache.Application;
import org.softuni.main.javache.http.HttpContext;
import org.softuni.main.javache.http.HttpResponse;
import org.softuni.main.javache.http.HttpSessionStorage;

public class CasebookApplication implements Application {
	private HttpSessionStorage sessionStorage;

    private HashMap<String, HashMap<String, Function<HttpContext, byte[]>>> routesTable;

    private final ResourceHandler resourceHandler = new ResourceHandler(this.sessionStorage);

    public CasebookApplication() { }

    private void loadActionsForMethod(String method, HandlerLoader handlerLoader) {
        Map<String, Method> actions = handlerLoader.retrieveActionsMap(method);

        for (Map.Entry<String, Method> actionEntry : actions.entrySet()) {
            try {
                Constructor<?> handlerConstructor = actionEntry.getValue()
                        .getDeclaringClass()
                        .getDeclaredConstructor(HttpSessionStorage.class);

                handlerConstructor.setAccessible(true);

                Object handlerObject = handlerConstructor.newInstance(this.sessionStorage);

                BaseHandler handlerHandler = (BaseHandler) handlerObject;

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

    public void initializeRoutes() {
        this.routesTable = new HashMap<>();

        HandlerLoader handlerLoader = new HandlerLoader();

        this.loadActionsForMethod("GET", handlerLoader);
        this.loadActionsForMethod("POST", handlerLoader);
    }

    @Override
    public byte[] handleRequest(HttpContext httpContext) {
        String requestMethod = httpContext.getHttpRequest().getMethod();
        String requestUrl = httpContext.getHttpRequest().getRequestUrl();

        if (this.routesTable.containsKey(requestUrl) &&
                this.routesTable.get(requestUrl).containsKey(requestMethod)) {
            return this.routesTable.get(requestUrl).get(requestMethod).apply(httpContext);
        } else {
            HttpResponse httpResponse = this
                    .resourceHandler
                    .getResource(httpContext.getHttpRequest(), httpContext.getHttpResponse());

            return httpResponse.getBytes();
        }
    }

    @Override
    public HttpSessionStorage getSessionStorage() {
        return this.sessionStorage;
    }

    @Override
    public void setSessionStorage(HttpSessionStorage sessionStorage) {
        this.sessionStorage = sessionStorage;
    }
}
