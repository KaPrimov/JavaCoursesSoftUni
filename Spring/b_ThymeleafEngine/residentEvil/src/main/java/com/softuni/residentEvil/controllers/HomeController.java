package com.softuni.residentEvil.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController extends AbstractController {
	
	@GetMapping("/")
	public ModelAndView index() {
		return super.view("home");
	}
}
