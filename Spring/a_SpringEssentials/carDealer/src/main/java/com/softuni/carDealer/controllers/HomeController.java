package com.softuni.carDealer.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("")
@Controller
public class HomeController {
	
	@GetMapping
	public ModelAndView index() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("home");
		return mav; 
	}
}
