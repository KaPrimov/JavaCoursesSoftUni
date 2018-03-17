package com.softuni.residentEvil.controllers;

import com.softuni.residentEvil.models.binding.RegisterUser;
import com.softuni.residentEvil.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
	public String getLogin(@RequestParam(required = false) String error,Model model) {
		if (error != null) {
			model.addAttribute("error", error);
		}
		return "login";
	}
	
//	@PostMapping(path = "/login")
//	public ModelAndView login(@RequestParam(required = false) String error, @ModelAttribute LoginUser loginUser, HttpSession session) {
//		ModelAndView mav = new ModelAndView();
//		User user = this.userService.findByUsername(loginUser.getUsername());
//		if (user.getPassword().equals(loginUser.getPassword())) {
//			session.setAttribute("username", user.getUsername());
//			mav.setViewName("home");
//		} else {
//			mav.setViewName("login");
//			mav.addObject("error", "Invalid login");
//		}
//		return mav;
//	}
	
	@PostMapping(path = "/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/users/login";
	}
}
