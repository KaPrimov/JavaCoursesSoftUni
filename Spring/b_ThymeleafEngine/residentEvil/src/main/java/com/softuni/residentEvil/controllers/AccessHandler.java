package com.softuni.residentEvil.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AccessHandler extends AbstractController {

    @GetMapping("/unauthorized")
    public ModelAndView unauthorized() {
        return super.view("unauthorized");
    }
}
