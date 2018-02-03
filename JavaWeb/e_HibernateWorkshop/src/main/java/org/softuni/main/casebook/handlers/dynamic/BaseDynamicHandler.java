package org.softuni.main.casebook.handlers.dynamic;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.softuni.main.casebook.handlers.BaseHandler;
import org.softuni.main.casebook.utilities.TemplateEngine;
import org.softuni.main.database.models.User;
import org.softuni.main.database.repositories.UserRepository;
import org.softuni.main.javache.WebConstants;
import org.softuni.main.javache.http.HttpCookie;
import org.softuni.main.javache.http.HttpRequest;
import org.softuni.main.javache.http.HttpResponse;
import org.softuni.main.javache.http.HttpSessionStorage;
import org.softuni.main.javache.http.HttpStatus;

abstract class BaseDynamicHandler extends BaseHandler {
    private static final String VIEWS_FULL_PATH = System.getProperty("user.dir")
            + "\\src\\main\\java\\org\\softuni\\main\\casebook\\resources\\templates\\";
    
    private static final String BASE_TEMPLATE_VIEW_PATH = System.getProperty("user.dir")
            + "\\src\\main\\java\\org\\softuni\\main\\casebook\\resources\\base-template.html";
    
    private static final String HEADERS_PATH = System.getProperty("user.dir")
            + "\\src\\main\\java\\org\\softuni\\main\\casebook\\resources\\headers\\";

    private static final String VIEWS_EXTENSION = ".html";
    
    private static final TemplateEngine TEMPLATE_ENGINE = new TemplateEngine();
    
    protected Map<String, String> viewData;
    
    protected BaseDynamicHandler(HttpSessionStorage sessionStorage) {
        super(sessionStorage);
        this.viewData = new HashMap<>();
    }
    
    private String getBaseView() {
    	try {
            List<String> content =
                    Files.readAllLines(Paths.get(BASE_TEMPLATE_VIEW_PATH));
            return String.join("", content);
        } catch (IOException e) {
            return null;
        }
    }

    private String getView(String viewName) {
        try {
            List<String> content =
                    Files.readAllLines(Paths.get(
                            VIEWS_FULL_PATH
                                    + viewName
                                    + VIEWS_EXTENSION));
            return String.join("", content);
        } catch (IOException e) {
            return null;
        }
    }
    
    private String getHeaderView(String view) {
    	try {
            List<String> content =
                    Files.readAllLines(Paths.get(
                            HEADERS_PATH
                                    + view
                                    + VIEWS_EXTENSION));
            return String.join("", content);
        } catch (IOException e) {
            return "";
        }
    }
    
    private String getHeader(HttpRequest request) {
		if (this.isLoggedIn(request)) {
			return this.getHeaderView("logged");
		} else {
			return this.getHeaderView("guest");
		}
	}
    
    protected HttpResponse view(String view, HttpRequest request,  HttpResponse response) {
    	
        String viewContent = this.getView(view);
        String baseView = this.getBaseView();
    	String renderedContent = TEMPLATE_ENGINE.renderHtml(viewContent, viewData);
        String headerContent = this.getHeader(request);
        baseView = baseView.replace("${header}", headerContent);
        String fullView = baseView.replace("${view}", renderedContent);
        
        
        if (viewContent == null) {
        	return this.notFound(request, response);
        }     	
    	response.setContent(fullView.getBytes());
    	response.setStatusCode(HttpStatus.OK);
        response.addHeader("Content-Type", "text/html");
       
        return response;
    }

	protected HttpResponse redirect(String location, HttpRequest request, HttpResponse response) {
    	response.setStatusCode(HttpStatus.SEE_OTHER);
    	response.addHeader("Location", location);
    	
    	return response;
    }
	
	protected User getCurrentUser(HttpRequest request, UserRepository userRepository) {
    	
    	HttpCookie cookie = request.getCookies().get(WebConstants.SERVER_SESSION_TOKEN);
    	String userId = this.sessionStorage.getSessionData(cookie.getValue()).getAttributes().get("user-id").toString();
    	
    	User user = (User) userRepository.doAction("findById", userId);
    	
    	return user;    	
	}
}
