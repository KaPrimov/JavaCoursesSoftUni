package org.softuni.toyote;

import org.softuni.javache.RequestHandler;
import org.softuni.javache.http.*;
import org.softuni.javache.io.Reader;
import org.softuni.javache.io.Writer;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ToyoteResourceHandler implements RequestHandler {
    private boolean intercepted;

    private final String SERVER_ROOT_PATH;

    public ToyoteResourceHandler(String serverRootPath) {
        this.SERVER_ROOT_PATH = serverRootPath;
    }

    private boolean isResource(String requestUrl) {
        return requestUrl.contains(".");
    }

    private void retrieveResource(HttpRequest request, HttpResponse response) throws IOException {
        String resourceName = request
                .getRequestUrl()
                .split("\\/")[request.getRequestUrl().split("\\/").length - 1];

        String resourceExtension = resourceName
                .split("\\.")[resourceName.split("\\.").length - 1];

        Path resourcePath = Paths.get(SERVER_ROOT_PATH
                + "static"
                + File.separator
                + resourceExtension
                + File.separator
                + resourceName);

        byte[] fileContentData = Files.readAllBytes(resourcePath);
        String fileContentType = Files.probeContentType(resourcePath);

        response.setStatusCode(HttpStatus.OK);

        response.addHeader("Content-Type", fileContentType);
        response.addHeader("Content-Length", fileContentData.length + "");
        response.addHeader("Content-Disposition", "inline");

        response.setContent(fileContentData);
    }

    @Override
    public void handleRequest(InputStream inputStream, OutputStream outputStream) {
        try {
            String requestContent = new Reader().readAllLines(inputStream);

            HttpRequest request = new HttpRequestImpl(requestContent);
            HttpResponse response = new HttpResponseImpl();

            if(!this.isResource(request.getRequestUrl())) {
                this.intercepted = false;
                return;
            }

            this.retrieveResource(request, response);

            new Writer().writeBytes(response.getBytes(), outputStream);

            this.intercepted = true;
        } catch (IOException e) {
            this.intercepted = false;
        }
    }

    @Override
    public boolean hasIntercepted() {
        return this.intercepted;
    }
}
