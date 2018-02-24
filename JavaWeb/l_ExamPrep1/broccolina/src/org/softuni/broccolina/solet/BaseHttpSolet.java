package org.softuni.broccolina.solet;

import org.softuni.javache.http.HttpStatus;

public abstract class BaseHttpSolet implements HttpSolet {
    private static final String NOT_FOUND_MESSAGE = "<h1>Error: %s Not Found</h1>";

    private boolean initialized;

    private SoletConfig soletConfig;

    protected BaseHttpSolet(SoletConfig soletConfig) {
        this.soletConfig = soletConfig;
    }

    @Override
    public void init() {
        this.initialized = true;
    }

    @Override
    public boolean isInitialized() {
        return this.initialized;
    }

    @Override
    public SoletConfig getSoletConfig() {
        return this.soletConfig;
    }

    @Override
    public void service(HttpSoletRequest request, HttpSoletResponse response) {
        if (request.getMethod().equals("GET")) {
            this.doGet(request, response);
        } else if (request.getMethod().equals("POST")) {
            this.doPost(request, response);
        } else if (request.getMethod().equals("PUT")) {
            this.doPut(request, response);
        } else if (request.getMethod().equals("DELETE")) {
            this.doDelete(request, response);
        }
    }

    @Override
    public void doGet(HttpSoletRequest request, HttpSoletResponse response) {
        response.setStatusCode(HttpStatus.NOT_FOUND);

        response.addHeader("Content-Type", "text/html");

        response.setContent(String.format(NOT_FOUND_MESSAGE, "GET Action").getBytes());
    }

    @Override
    public void doPost(HttpSoletRequest request, HttpSoletResponse response) {
        response.setStatusCode(HttpStatus.NOT_FOUND);

        response.addHeader("Content-Type", "text/html");

        response.setContent(String.format(NOT_FOUND_MESSAGE, "POST Action").getBytes());
    }

    @Override
    public void doPut(HttpSoletRequest request, HttpSoletResponse response) {
        response.setStatusCode(HttpStatus.NOT_FOUND);

        response.addHeader("Content-Type", "text/html");

        response.setContent(String.format(NOT_FOUND_MESSAGE, "PUT Action").getBytes());
    }

    @Override
    public void doDelete(HttpSoletRequest request, HttpSoletResponse response) {
        response.setStatusCode(HttpStatus.NOT_FOUND);

        response.addHeader("Content-Type", "text/html");

        response.setContent(String.format(NOT_FOUND_MESSAGE, "DELETE Action").getBytes());
    }
}
