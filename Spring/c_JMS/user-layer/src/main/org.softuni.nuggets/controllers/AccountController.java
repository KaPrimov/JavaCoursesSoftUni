package org.softuni.nuggets.controllers;

import org.softuni.nuggets.entities.User;
import org.softuni.nuggets.models.binding.RegisterBindingModel;
import org.softuni.nuggets.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AccountController {
    private final UserService userService;

    @Autowired
    public AccountController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public ModelAndView login(@RequestParam(required = false, name = "error") String error, ModelAndView modelAndView) {
        modelAndView.setViewName("login");

        if(error != null) modelAndView.addObject("error", error);

        return modelAndView;
    }

    @PostMapping("/logout")
    public ModelAndView logout(@RequestParam(required = false, name = "logout") String logout, ModelAndView modelAndView, RedirectAttributes redirectAttributes) {
        modelAndView.setViewName("redirect:/login");

        if(logout != null) redirectAttributes.addFlashAttribute("logout", logout);

        return modelAndView;
    }

    @GetMapping("/register")
    public ModelAndView register(ModelAndView modelAndView) {
        modelAndView.setViewName("register");

        return modelAndView;
    }

    @PostMapping("/register")
    public ModelAndView registerConfirm(@ModelAttribute RegisterBindingModel bindingModel, ModelAndView modelAndView) {
        modelAndView.setViewName("redirect:/login");

        if(bindingModel.getPassword().equals(bindingModel.getConfirmPassword())) {
            this.userService.register(bindingModel);
        }

        return modelAndView;
    }

    @GetMapping("/preferences")
    public ModelAndView preferences(ModelAndView mav) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        mav.setViewName("nuggets-all");
        mav.addObject("nuggets", user.getPreferences().split(","));
        mav.addObject("title", "Preferences");
        return mav;
    }
}
