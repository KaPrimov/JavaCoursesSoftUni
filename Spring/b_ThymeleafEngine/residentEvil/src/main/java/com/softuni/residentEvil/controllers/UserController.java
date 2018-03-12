package com.softuni.residentEvil.controllers;

import com.softuni.residentEvil.entities.User;
import com.softuni.residentEvil.models.binding.LoginUser;
import com.softuni.residentEvil.models.binding.RegisterUser;
import com.softuni.residentEvil.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@RequestMapping("/users")
@Controller
public class UserController {
	
	private UserService userService;
	
	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping(path = "/register")
	public String getRegister() {
		return "register";
	}
	
	@PostMapping(path = "/register")
	public String register(@ModelAttribute RegisterUser registerUser) {
		this.userService.saveUser(registerUser);
		return "redirect:/users/login";
	}
	
	@GetMapping(path = "/login")
	public String getLogin() {
		return "login";
	}
	
	@PostMapping(path = "/login")
	public ModelAndView login(@ModelAttribute LoginUser loginUser, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		User user = this.userService.findByUsername(loginUser.getUsername());
		if (user.getPassword().equals(loginUser.getPassword())) {
			session.setAttribute("username", user.getUsername());
			mav.setViewName("home");
		} else {
			mav.setViewName("login");
			mav.addObject("error", "Invalid login");
		}
		return mav;
	}
	
	@PostMapping(path = "/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/users/login";
	}
}
