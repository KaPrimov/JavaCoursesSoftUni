package org.softuni.main.casebook.handlers.dynamic;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.softuni.main.casebook.annotations.ApplicationRequestHandler;
import org.softuni.main.casebook.annotations.Get;
import org.softuni.main.casebook.annotations.Post;
import org.softuni.main.database.models.User;
import org.softuni.main.database.repositories.UserRepository;
import org.softuni.main.javache.WebConstants;
import org.softuni.main.javache.http.HttpCookie;
import org.softuni.main.javache.http.HttpCookieImpl;
import org.softuni.main.javache.http.HttpRequest;
import org.softuni.main.javache.http.HttpResponse;
import org.softuni.main.javache.http.HttpSession;
import org.softuni.main.javache.http.HttpSessionImpl;
import org.softuni.main.javache.http.HttpSessionStorage;

@ApplicationRequestHandler
public class UsersHandler extends BaseDynamicHandler {
	protected UsersHandler(HttpSessionStorage sessionStorage) {
        super(sessionStorage);
    }

    @Get(route = "/register")
    public HttpResponse register(HttpRequest request, HttpResponse response) {
        return this.view("register", request, response);
    }

    @Post(route = "/register")
    public HttpResponse registerConfirm(HttpRequest request, HttpResponse response) {
        UserRepository userRepository = new UserRepository();

        String username = request.getBodyParameters().get("username");
        String password = request.getBodyParameters().get("password");

        userRepository.doAction("create", username.toString(), password.toString());

        userRepository.dismiss();
        return this.redirect("/", request, response);
    }

    @Get(route = "/login")
    public HttpResponse login(HttpRequest request, HttpResponse response) {
        return this.view("login", request, response);
    }

    @Post(route = "/login")
    public HttpResponse loginConfirm(HttpRequest request, HttpResponse response) {
        UserRepository userRepository = new UserRepository();

        String username = request.getBodyParameters().get("username");
        String password = request.getBodyParameters().get("password");

        User user = (User) userRepository.doAction("findByUsername", username);

        if(user == null) {
            return this.redirect("/login", request, response);
        } else if (!user.getPassword().equals(password)) {
            return this.redirect("/login", request, response);
        }

        String sessionId = UUID.randomUUID().toString();
        HttpSession session = new HttpSessionImpl(sessionId);
        session.addAttribute("user-id", user.getId());
        this.sessionStorage.setSessionData(session.getId(), session);

        response.addCookie(new HttpCookieImpl(WebConstants.SERVER_SESSION_TOKEN, session.getId()));

        userRepository.dismiss();
        return this.redirect("/home", request, response);
    }

    @Post(route = "/logout")
    public HttpResponse logout(HttpRequest request, HttpResponse response) {
        if(!this.isLoggedIn(request)) {
            return this.redirect("/login", request, response);
        }

        HttpCookie cookie = request.getCookies().get(WebConstants.SERVER_SESSION_TOKEN);
        
        this.sessionStorage.getSessionData(cookie.getValue()).invalidate();
        this.sessionStorage.removeSession(cookie.getValue());
        
        response.addCookie(new HttpCookieImpl("Javache", "token=deleted; path=/; expires=Thu, 01 Jan 1970 00:00:00 GMT"));

        return this.redirect("/", request, response);
    }
    
    @Get(route = "/profile")
    public HttpResponse profile(HttpRequest request, HttpResponse response) {
    	if (!this.isLoggedIn(request)) {
    		return this.redirect("/login", request, response);
    	}
    	
    	
    	UserRepository repository = new UserRepository();
    	User user = this.getCurrentUser(request, repository);
    	
    	StringBuilder friendsHtml = new StringBuilder();
    	friendsHtml.append("<ul class=\"list-group\">");
    	for (User friend : user.getFriends()) {
			friendsHtml.append("<li class=\"list-group-item\">").append(friend.getUsername()).append("</li>");
		}
    	friendsHtml.append("</ul>");
    	
    	this.viewData.remove("username");
    	this.viewData.remove("friends");
    	this.viewData.putIfAbsent("username", user.getUsername());
    	this.viewData.putIfAbsent("friends", friendsHtml.toString());
    	repository.dismiss();
    	
    	return this.view("profile", request, response);
    }
    
    @Post(route = "/add-friend")
    public HttpResponse addFriend(HttpRequest request, HttpResponse response) {
    	if (!this.isLoggedIn(request)) {
    		return this.redirect("/login", request, response);
    	}    	
    	 
        UserRepository userRepository = new UserRepository();
        User currentUser = this.getCurrentUser(request, userRepository); 
        
        userRepository.doAction("addFriend", currentUser.getUsername(), request.getBodyParameters().get("friend"));
        userRepository.dismiss();
        
        return this.redirect("/profile", request, response);
    }
}
