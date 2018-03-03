package controllers;

import bindingModel.UserLoginBindingModel;
import bindingModel.UserRegisterBindingModel;
import entities.PersonGender;
import entities.User;
import org.softuni.broccolina.solet.HttpSoletRequest;
import org.softuni.summer.api.*;
import repositories.UserRepository;

@Controller
public class UserController {
    private UserRepository userRepository;

    public UserController() {
        this.userRepository = new UserRepository();
    }

    private boolean isLoggedIn(HttpSoletRequest request) {
        return request.getSession().getAttributes().containsKey("user-id");
    }

    @GetMapping(route = "/login")
    public String login(HttpSoletRequest request, Model model) {
        if (this.isLoggedIn(request)) {
            return "redirect:/home";
        }

        model.addAttribute("display", "style=\"display: none\"");

        return "template:login";
    }

    @PostMapping(route = "/login")
    public String loginConfirm(HttpSoletRequest request, UserLoginBindingModel bindingModel, Model model) {
        if (this.isLoggedIn(request)) {
            return "redirect:/home";
        }

        User registeredUser = this.userRepository.findByUsername(bindingModel.getUsername());

        if (registeredUser == null
                || !registeredUser.getPassword()
                .equals(bindingModel.getPassword())) {
            model.addAttribute("display", "style=\"display: block\"");
            model.addAttribute("type", "danger");
            model.addAttribute("message", "Incorrect password.");

            return "template:login";
        }

        request.getSession().addAttribute("user-id", registeredUser.getId());
        request.getSession().addAttribute("username", registeredUser.getUsername());

        return "redirect:/home";
    }

    @GetMapping(route = "/register")
    public String register(HttpSoletRequest request) {
        if (this.isLoggedIn(request)) {
            return "redirect:/home";
        }

        return "template:register";
    }

    @PostMapping(route = "/register")
    public String registerConfirm(HttpSoletRequest request, UserRegisterBindingModel bindingModel) {
        if (this.isLoggedIn(request)) {
            return "redirect:/home";
        }

        if (!bindingModel.getPassword().equals(
                bindingModel.getConfirmPassword())) {
            return "redirect:/register";
        }

        User user = new User();

        user.setUsername(bindingModel.getUsername());
        user.setPassword(bindingModel.getPassword());
        user.setGender(PersonGender.parseValue(bindingModel.getGender()));
        this.userRepository.createUser(user);

        return "redirect:/login";
    }

    @GetMapping(route = "/logout")
    public String logout(HttpSoletRequest request) {
        if (!this.isLoggedIn(request)) {
            return "redirect:/login";
        }

        request.getSession().invalidate();

        return "redirect:/";
    }

    @GetMapping(route = "/profile/{id}")
    @PreAuthorize(loggedin = true)
    public String profile(@PathVariable String id, HttpSoletRequest request, Model model) {
        if (!this.isLoggedIn(request)) {
            return "redirect:/login";
        }
        User user = this.userRepository.findById(id);
        model.addAttribute("genderImg", PersonGender.getSimpleValue(user.getGender()));
        model.addAttribute("name", user.getUsername());
        model.addAttribute("gender", PersonGender.getComplexValue(user.getGender()));

        return "template:profile";
    }

    @PostMapping(route = "/add/friend/{username}")
    @PreAuthorize(loggedin = true)
    public String addFriend(@PathVariable String username, HttpSoletRequest request, Model model) {
        if (!this.isLoggedIn(request)) {
            return "redirect:/login";
        }
        this.userRepository.addFriend(this.userRepository.findByUsername(request.getSession().getAttributes().get("username").toString()), this.userRepository.findByUsername(username));
        model.addAttribute("display", "style=\"display: block\"");
        model.addAttribute("type", "success");
        model.addAttribute("message", "Friend added!");
        return "redirect:/home";
    }

    @GetMapping(route = "/friends")
    @PreAuthorize(loggedin = true)
    public String friends(@PathVariable String username, HttpSoletRequest request, Model model) {
        if (!this.isLoggedIn(request)) {
            return "redirect:/login";
        }
        User user = this.userRepository.findByUsername(request.getSession().getAttributes().get("username").toString());
        StringBuilder result = new StringBuilder();
        for (User friend : user.getFriends()) {
            result
                    .append("<div class=\"single-user row justify-content-around col-lg-6 offset-lg-3\">")
                    .append("<h6 class=\"align-self-center unfriend-username\"><a class=\"user-friend\" href=\"/profile/").append(friend.getId()).append("\">").append(friend.getUsername()).append("</a>").append("</h6>")
                    .append("<form method=\"POST\" class=\"unfriend-form\" action=\"/unfriend/" + friend.getUsername() + "\"><input type=\"submit\" class=\"btn btn-danger\" value=\"Unfriend\" /></form>")
                    .append("</div>");
        }
        model.addAttribute("friends", result);
        return "template:friends";
    }

    @PostMapping(route = "/unfriend/{friendName}")
    @PreAuthorize(loggedin = true)
    public String unfriend(@PathVariable String friendName, HttpSoletRequest request, Model model) {
        if (!this.isLoggedIn(request)) {
            return "redirect:/login";
        }
        User user = this.userRepository.findByUsername(request.getSession().getAttributes().get("username").toString());
        this.userRepository.unfriend(user, friendName);
        model.addAttribute("display", "style=\"display: block\"");
        model.addAttribute("type", "success");
        model.addAttribute("message", "Friend removed!");
        return "redirect:/home";
    }
}
