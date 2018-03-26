package com.softuni.residentEvil.controllers;

import com.softuni.residentEvil.models.binding.EditUserModel;
import com.softuni.residentEvil.models.binding.RegisterUser;
import com.softuni.residentEvil.models.view.ViewUser;
import com.softuni.residentEvil.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RequestMapping("/users")
@Controller
public class UserController extends AbstractController {
	
	private UserService userService;
	
	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping(path = "/register")
    @PreAuthorize("isAnonymous()")
	public ModelAndView getRegister() {
		return super.view("register");
	}
	
	@PostMapping(path = "/register")
    @PreAuthorize("isAnonymous()")
	public ModelAndView register(@ModelAttribute RegisterUser registerUser) {
		this.userService.saveUser(registerUser);
		return super.redirect("/users/login");
	}
	
	@GetMapping(path = "/login")
    @PreAuthorize("isAnonymous()")
	public ModelAndView getLogin(@RequestParam(required = false) String error, Model model) {
		if (error != null) {
			model.addAttribute("error", error);
		}
		return super.view("login");
	}
	
	@GetMapping(path = "/all")
    @PreAuthorize("hasRole('ADMIN')")
	public ModelAndView showAllUsers(ModelAndView mav, Principal principal) {
		List<ViewUser> allUsers = this.userService.findAllUsers(principal);
		return super.view("all-users", "users", allUsers);
	}
	
	@PostMapping(path = "/logout")
    @PreAuthorize("!isAnonymous()")
	public ModelAndView logout(HttpSession session) {
		session.invalidate();
		return super.redirect("/users/login");
	}

	@GetMapping(path = "/edit/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ModelAndView getEditUser(@PathVariable Long id) {
        ViewUser user = this.userService.findUserById(id);
	    return super.view("edit-user", "user", user);
    }

    @PostMapping("/edit/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ModelAndView editVirus(@Valid @ModelAttribute("user") EditUserModel editUserModel, BindingResult bindingResult, @PathVariable Long id) {
        if (bindingResult.hasErrors()) {
			super.redirect("/users/edit/");
        }
        editUserModel.setId(id);
        this.userService.editUser(editUserModel);
        return super.redirect("/");
    }
}
