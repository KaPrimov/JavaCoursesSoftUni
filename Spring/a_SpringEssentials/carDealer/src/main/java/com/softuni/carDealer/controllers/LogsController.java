package com.softuni.carDealer.controllers;

import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.softuni.carDealer.dtos.view.LogDTO;
import com.softuni.carDealer.services.apis.LogService;

@Controller
@RequestMapping("/logs")
public class LogsController {
	private LogService logService;

	@Autowired
	public LogsController(LogService logService) {
		this.logService = logService;
	}
	
	@GetMapping(path = "/all")
	public ModelAndView allLogs(HttpSession session) {
		ModelAndView mav = new ModelAndView();
		if (session.getAttribute("username") == null) {
			mav.setViewName("redirect:/users/login");
			return mav;		
		} else {
			Set<LogDTO> allLogs = this.logService.allLogs();
			mav.addObject("logs", allLogs);
			mav.setViewName("all-logs");
			return mav;
		}
	}
	
	@GetMapping(path = "/search")
	public ModelAndView searchedLogs(HttpSession session, @RequestParam("text") String text) {
		ModelAndView mav = new ModelAndView();
		if (session.getAttribute("username") == null) {
			mav.setViewName("redirect:/users/login");
			return mav;		
		} else {
			Set<LogDTO> allLogs = this.logService.queryLogsByUsername(text);
			mav.addObject("logs", allLogs);
			mav.setViewName("all-logs");
			return mav;
		}
	}
	
	@PostMapping("delete-all")
	public String deleteAll(HttpSession session) {
		if (session.getAttribute("username") == null) {
			return "redirect:/users/login";		
		} else {
			this.logService.deleteAll();
			return "redirect:/";
		}
	}
}
