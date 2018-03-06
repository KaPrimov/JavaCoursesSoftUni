package com.softuni.carDealer.controllers;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.softuni.carDealer.dtos.binding.add.CustomerAddDto;
import com.softuni.carDealer.dtos.view.CustomerView;
import com.softuni.carDealer.dtos.view.TotalCustomerSalesView;
import com.softuni.carDealer.entities.Customer;
import com.softuni.carDealer.services.apis.CustomerService;

@RequestMapping("/customers")
@Controller
public class CusotmerController {
	
	@Autowired
	private CustomerService<Customer, Long> customerService;
	
	@GetMapping("/all/{type}")
	public ModelAndView all(@PathVariable final String type) {
		ModelAndView mav = new ModelAndView();
		List<CustomerView> orderedCustomers = customerService.findAllOrderedCustomers(type.equalsIgnoreCase("descending") ? false : true);
		mav.setViewName("ordered-customers");
		mav.addObject("customers", orderedCustomers);
		
		return mav;
	}
	
	@GetMapping("/{id}")
	public ModelAndView totalSales(@PathVariable final Long id) {
		ModelAndView mav = new ModelAndView();
		TotalCustomerSalesView customer = customerService.totalCustomerSales(id);
		mav.setViewName("customer-view");
		mav.addObject("customer", customer);
		
		return mav;
	}
	
	@GetMapping("/add")
	public ModelAndView showAddCustomer() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("add-customer");
		
		return mav;
	}
	
	@PostMapping("/add")
	public String saveCustomer(@ModelAttribute CustomerAddDto customerToAdd) {
		try {
			this.customerService.save(customerToAdd);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return "redirect:/";
	}
	

	@GetMapping("/edit/{id}")
	public ModelAndView showEditCustomer(@PathVariable final Long id) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("edit-customer");
		mav.addObject("customer", this.customerService.getById(id));
		return mav;
	}
	
	@PostMapping("/edit")
	public String showEditCustomer(@ModelAttribute CustomerAddDto customerToAdd) {
		try {
			this.customerService.updateCustomer(customerToAdd);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return "redirect:/";
	}
}
