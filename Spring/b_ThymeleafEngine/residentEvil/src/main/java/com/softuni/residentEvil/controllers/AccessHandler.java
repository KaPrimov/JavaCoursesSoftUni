package com.softuni.residentEvil.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AccessHandler {

    @GetMapping("/unauthorized")
    public String unauthorized() {
        return "unauthorized";
    }
}
