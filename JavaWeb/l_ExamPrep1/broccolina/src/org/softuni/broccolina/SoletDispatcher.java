package org.softuni.broccolina;

import org.softuni.broccolina.solet.*;
import org.softuni.broccolina.util.ApplicationLoader;
import org.softuni.broccolina.util.SessionManager;
import org.softuni.javache.RequestHandler;
import org.softuni.javache.io.Reader;
import org.softuni.javache.io.Writer;

import java.io.*;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Map;

public class SoletDispatcher implements RequestHandler {

    private final String SERVER_ROOT_PATH;

    private boolean intercepted;

    private ApplicationLoader applicationLoader;

    private SessionManager sessionManager;

    public SoletDispatcher(String serverRootPath) {
        this.SERVER_ROOT_PATH = serverRootPath;
        this.intercepted = false;
        this.applicationLoader = new ApplicationLoader(SERVER_ROOT_PATH);
        this.applicationLoader.loadApplications();
        this.sessionManager = new SessionManager();
    }

    @Override
    public void handleRequest(InputStream inputStream, OutputStream outputStream) {
        try {
            String requestContent = new Reader().readAllLines(inputStream);

            HttpSoletRequest request = new HttpSoletRequestImpl(requestContent, null);
            HttpSoletResponse response = new HttpSoletResponseImpl(outputStream);

            this.sessionManager.initializeSession(request);

            Object soletCandidate = null;

            String genericApplicationRequestPath = request
                    .getRequestUrl()
                    .substring(0, request
                            .getRequestUrl()
                            .indexOf("/",
                                    request.getRequestUrl().indexOf("/") + 1) + 1)
                    + "*";

            if(!genericApplicationRequestPath.contains("/")) genericApplicationRequestPath = "/" + genericApplicationRequestPath;

            String requestPath = request.getRequestUrl();

            if (this.applicationLoader.getSolets().containsKey(requestPath)) {
                soletCandidate = this.applicationLoader.getSolets().get(requestPath);
            } else if (this.applicationLoader.getSolets().containsKey(genericApplicationRequestPath)) {
                soletCandidate = this.applicationLoader.getSolets().get(genericApplicationRequestPath);
            } else if(this.applicationLoader.getSolets().containsKey("/*")) {
                soletCandidate = this.applicationLoader.getSolets().get("/*");
            }

            if (!(boolean)soletCandidate
                    .getClass()
                    .getMethod("isInitialized")
                    .invoke(soletCandidate)) {
                soletCandidate
                        .getClass()
                        .getMethod("init")
                        .invoke(soletCandidate);
            }

            if (soletCandidate != null && (boolean)soletCandidate
                    .getClass()
                    .getMethod("isInitialized")
                    .invoke(soletCandidate)) {

                Class[] requiredParameters = Arrays.stream(soletCandidate
                        .getClass()
                        .getMethods())
                        .filter((x) -> x.getName().equals("service"))
                        .findFirst()
                        .get()
                        .getParameterTypes();

                Object proxyReq = Proxy.newProxyInstance(
                        soletCandidate.getClass().getClassLoader(),
                        new Class[]{requiredParameters[0]},
                        (proxy, method, args) -> Arrays.stream(request
                                .getClass()
                                .getMethods())
                                .filter((x) -> x.getName().equals(method.getName()))
                                .findFirst()
                                .get()
                                .invoke(request, args));

                Object proxyResp = Proxy.newProxyInstance(
                        soletCandidate.getClass().getClassLoader(),
                        new Class[]{requiredParameters[1]},
                        (proxy, method, args) -> Arrays.stream(response
                                .getClass()
                                .getMethods())
                                .filter((x) -> x.getName().equals(method.getName()))
                                .findFirst()
                                .get()
                                .invoke(response, args));

                soletCandidate
                        .getClass()
                        .getMethod("service", requiredParameters[0], requiredParameters[1])
                        .invoke(soletCandidate, proxyReq, proxyResp);

                this.sessionManager.attachSession(request, response);
                this.sessionManager.refreshSessions();

                new Writer().writeBytes(response.getBytes(), outputStream);
                this.intercepted = true;
            }
        } catch (IOException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
            this.intercepted = false;
        }
    }

    @Override
    public boolean hasIntercepted() {
        return this.intercepted;
    }
}
