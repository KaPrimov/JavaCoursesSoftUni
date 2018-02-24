package controllers;

import org.softuni.broccolina.solet.HttpSoletRequest;
import org.softuni.summer.api.Controller;
import org.softuni.summer.api.GetMapping;
import org.softuni.summer.api.Model;

@Controller
public class HomeController {

    private boolean isLoggedIn(HttpSoletRequest request) {
        return request.getSession().getAttributes().containsKey("user-id");
    }

    @GetMapping(route = "/")
    public String index(HttpSoletRequest request, Model model) {
        if(this.isLoggedIn(request)) {
            model.addAttribute("username",
                    request.getSession().getAttributes().get("username"));

            return "template:loggedin";
        }

        return "template:index";
    }
}
