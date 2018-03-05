package com.softuni.carDealer.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.softuni.carDealer.dtos.view.LocalSupplierView;
import com.softuni.carDealer.entities.Supplier;
import com.softuni.carDealer.services.apis.SupplierService;

@RequestMapping("/suppliers")
@Controller
public class SupplierController {
	
	private SupplierService<Supplier, Long> supplierService;

	@Autowired
	public SupplierController(SupplierService<Supplier, Long> supplierService) {
		this.supplierService = supplierService;
	}
	
	@GetMapping("/{type}")
	public ModelAndView all(@PathVariable final String type) {
		ModelAndView mav = new ModelAndView();
		List<LocalSupplierView> suppliers =type.equals("importers") ? supplierService.findAllImportSuppliers() : supplierService.findAllLocalSuppliers();
		mav.setViewName("suppliers");
		mav.addObject("suppliers", suppliers);
		
		return mav;
	}
	
	
}
