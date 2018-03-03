package com.softuni.carDealer.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.softuni.carDealer.dtos.view.CustomerView;
import com.softuni.carDealer.services.apis.CustomerService;

@RequestMapping("/customers")
@Controller
public class CusotmerController {
	
	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/all/{type}")
	public ModelAndView all(@PathVariable final String type) {
		ModelAndView mav = new ModelAndView();
		List<CustomerView> orderedCustomers = customerService.findAllOrderedCustomers(type.equalsIgnoreCase("descending") ? false : true);
		mav.setViewName("ordered-customers");
		mav.addObject("customers", orderedCustomers);
		
		return mav;
	}
}
