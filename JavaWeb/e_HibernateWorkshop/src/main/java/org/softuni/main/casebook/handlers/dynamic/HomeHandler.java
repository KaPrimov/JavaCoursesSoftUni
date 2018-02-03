package org.softuni.main.casebook.handlers.dynamic;

import org.softuni.main.casebook.annotations.ApplicationRequestHandler;
import org.softuni.main.casebook.annotations.Get;
import org.softuni.main.database.models.User;
import org.softuni.main.database.repositories.UserRepository;
import org.softuni.main.javache.http.HttpRequest;
import org.softuni.main.javache.http.HttpResponse;
import org.softuni.main.javache.http.HttpSessionStorage;
import org.softuni.main.javache.http.HttpStatus;

@ApplicationRequestHandler
public class HomeHandler extends BaseDynamicHandler {
	 protected HomeHandler(HttpSessionStorage sessionStorage) {
	        super(sessionStorage);
	    }

	    @Get(route = "/")
	    public HttpResponse index(HttpRequest request, HttpResponse response) {
	        return this.view("index", request, response);
	    }

	    @Get(route = "/home")
	    public HttpResponse home(HttpRequest request, HttpResponse response) {
	        if(!this.isLoggedIn(request)) {
	            return this.redirect("/login", request, response);
	        }
	        
	        UserRepository userRepository = new UserRepository();
	        
	        User[] allUsers = (User[]) userRepository.doAction("findAll");
	        StringBuilder usersHtml = new StringBuilder();
	        usersHtml.append("<ul class=\"list-group\">");
	        User currentUser = this.getCurrentUser(request, userRepository); 
	        
	        for (User user : allUsers) {
	        	if (currentUser.getId().equals(user.getId()) || currentUser.getFriends().contains(user)) {
	        		continue;
	        	}
				usersHtml.append("<li class=\"list-group-item\">")
					.append("<form method=\"post\" action=\"/add-friend\" class=\"col-sm-12\">")
					.append("<label class=\"float-left\" for=\"friendName\">").append(user.getUsername()).append("</label>")
					.append("<input id=\"friendName\" name=\"friend\" value=\"").append(user.getUsername()).append("\" type=\"hidden\" />")
					.append("<input class=\"float-right btn btn-default\" type=\"submit\" value=\"Add Friend\" />")
					.append("</form>")
					.append("</li>");
			}
	        usersHtml.append("</ul>");
	        this.viewData.remove("people");
	        this.viewData.putIfAbsent("people", usersHtml.toString());
	        
	        userRepository.dismiss();
	        
	        return this.view("home", request, response);
	    }
}
