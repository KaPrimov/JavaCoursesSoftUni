package controllers;

import entities.User;
import org.softuni.broccolina.solet.HttpSoletRequest;
import org.softuni.summer.api.Controller;
import org.softuni.summer.api.GetMapping;
import org.softuni.summer.api.Model;
import repositories.UserRepository;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class HomeController {
    private UserRepository userRepository;

    public HomeController() {
        this.userRepository = new UserRepository();
    }

    private boolean isLoggedIn(HttpSoletRequest request) {
        return request.getSession().getAttributes().containsKey("user-id");
    }

    @GetMapping(route = "/")
    public String index(HttpSoletRequest request) {
        if(this.isLoggedIn(request)) {
            return "redirect:/home";
        }

        return "template:index";
    }

    @GetMapping(route = "/home")
    public String home(HttpSoletRequest request, Model model) {
        if(!this.isLoggedIn(request)) {
            return "redirect:/login";
        }
        final String currentUserUsername = request.getSession().getAttributes().get("username").toString();
        model.addAttribute("display", "style=\"display: none\"");
        model.addAttribute("username", currentUserUsername);
        model.addAttribute("id", request.getSession().getAttributes().get("user-id"));

        //<div class="row mb-4 d-flex justify-content-around">

        StringBuilder result = new StringBuilder();
        List<User> allUsers = this.userRepository.findAll();

        User currentLoggedInUser = this.userRepository.findByUsername(currentUserUsername);
        Set<String> friendsUserNames = currentLoggedInUser.getFriends().stream().map(User::getUsername).collect(Collectors.toSet());
        List<User> notFriends = new LinkedList<>();

        for (User singleUser : allUsers) {
            if (friendsUserNames.contains(singleUser.getUsername()) || singleUser.getUsername().equalsIgnoreCase(currentUserUsername)) {
                continue;
            }
            if (!notFriends.contains(singleUser)) {
                notFriends.add(singleUser);
            }
        }

        for (int i = 0; i < notFriends.size(); i++) {
            User currentUser = notFriends.get(i);
            if(i == 0) {
                result
                        .append("<div class=\"row mb-4 mt-4 d-flex justify-content-around\">")
                        .append(currentUser.toString());
            } else if (i % 4 == 0) {
                result
                        .append("</div>")
                        .append("<div class=\"row mb-4 d-flex justify-content-around\">")
                        .append(currentUser.toString());
            } else {
                result.append(currentUser.toString());
            }
        }

        if(!allUsers.isEmpty()) result.append("</div>");

        model.addAttribute("users", result.toString());
        return "template:home";
    }
}
