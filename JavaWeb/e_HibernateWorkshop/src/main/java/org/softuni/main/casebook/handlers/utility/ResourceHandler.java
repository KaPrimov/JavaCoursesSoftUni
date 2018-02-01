package org.softuni.main.casebook.handlers.utility;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.softuni.main.casebook.handlers.BaseHandler;
import org.softuni.main.javache.http.HttpRequest;
import org.softuni.main.javache.http.HttpResponse;
import org.softuni.main.javache.http.HttpSessionStorage;
import org.softuni.main.javache.http.HttpStatus;

public class ResourceHandler extends BaseHandler {
    private static final String STATIC_RESOURCES_FULL_PATH =
            System.getProperty("user.dir")
                    + "\\src\\main\\java\\org\\softuni\\main\\casebook\\resources\\public\\";
    
    public ResourceHandler(HttpSessionStorage sessionStorage) {
        super(sessionStorage);
    }

    public final HttpResponse getResource(HttpRequest request, HttpResponse response) {
        try {
            String resourceUrl = request.getRequestUrl();
            String resourceExtension = resourceUrl.substring(resourceUrl.lastIndexOf(".") + 1);

            byte[] content =
                    Files.readAllBytes(Paths.get(
                            STATIC_RESOURCES_FULL_PATH
                                    + resourceUrl));

            response.setStatusCode(HttpStatus.OK);

            response.addHeader("Content-Type", this.getContentType(resourceExtension));
            response.addHeader("Content-Length", content.length + "");
            response.addHeader("Content-Disposition", "inline");

            response.setContent(content);

            return response;
        } catch (IOException e) {
            return this.notFound(request, response);
        }
    }

    //TODO: Refactor this, PROPOSITION: MIME CONTENT TYPE SERVER ENUM
    private String getContentType(String resourceExtension) {
        switch(resourceExtension) {
            case "html": return "text/html";
            case "css": return "text/css";
            case "png": return "image/png";
            case "jpg":
            case "jpeg":
                    return "image/jpeg";
            default: return "text/plain";
        }
    }
}
