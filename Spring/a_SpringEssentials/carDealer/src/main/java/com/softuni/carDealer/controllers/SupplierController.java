package com.softuni.carDealer.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.softuni.carDealer.dtos.binding.add.SupplierAddDto;
import com.softuni.carDealer.dtos.binding.relations.PartDto;
import com.softuni.carDealer.dtos.binding.relations.SupplierDto;
import com.softuni.carDealer.dtos.view.LocalSupplierView;
import com.softuni.carDealer.dtos.view.SupplierView;
import com.softuni.carDealer.entities.OperationEnum;
import com.softuni.carDealer.entities.Part;
import com.softuni.carDealer.entities.Supplier;
import com.softuni.carDealer.services.apis.LogService;
import com.softuni.carDealer.services.apis.PartService;
import com.softuni.carDealer.services.apis.SupplierService;
import com.softuni.carDealer.services.apis.UserService;

@RequestMapping("/suppliers")
@Controller
public class SupplierController {

	private SupplierService<Supplier, Long> supplierService;
	private PartService<Part, Long> partService;
	private UserService userService;
	private LogService logService;

	@Autowired
	public SupplierController(SupplierService<Supplier, Long> supplierService, PartService<Part, Long> partService, 
			UserService userService, LogService logService) {
		this.supplierService = supplierService;
		this.partService = partService;
		this.userService = userService;
		this.logService = logService;
	}
	
	@GetMapping("/{type}")
	public ModelAndView allTyped(@PathVariable final String type) {
		ModelAndView mav = new ModelAndView();
		List<LocalSupplierView> suppliers =type.equals("importers") ? supplierService.findAllImportSuppliers() : supplierService.findAllLocalSuppliers();
		mav.setViewName("suppliers");
		mav.addObject("suppliers", suppliers);
		
		return mav;
	}
	
	@GetMapping("/all")
	public ModelAndView all() {
		ModelAndView mav = new ModelAndView();
		List<SupplierView> suppliers = this.supplierService.allSuppliers();
		mav.setViewName("all-suppliers");		
		mav.addObject("suppliers", suppliers);
		
		return mav;
	}
	
	@GetMapping("/add")
	public ModelAndView showAddSupplier(HttpSession session) {
		ModelAndView mav = new ModelAndView();
		if (session.getAttribute("username") == null) {
			mav.setViewName("redirect:/users/login");
		} else {
			List<PartDto> parts = this.partService.findAllPartDtos();
			mav.setViewName("add-supplier");
			mav.addObject("parts", parts);
		}
		return mav;
	}
	
	@PostMapping("/add")
	public String saveSupplier(@ModelAttribute SupplierAddDto supplierToAdd, HttpSession session) {
		this.logService.log(OperationEnum.ADD, this.userService.findByUsername(session.getAttribute("username").toString()), "SUPPLIERS");
		if (session.getAttribute("username") == null) {
			return "redirect:/users/login";
		}
		this.supplierService.save(supplierToAdd);		
		return "redirect:/";
	}
	
	@GetMapping("/edit/{id}")
	public ModelAndView showEditSupplier(@PathVariable final Long id, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		if (session.getAttribute("username") == null) {
			mav.setViewName("redirect:/users/login");
		} else {
			mav.setViewName("edit-supplier");
			mav.addObject("supplier", this.supplierService.getById(id));
		}
		return mav;
	}
	
	@PostMapping("/edit")
	public String showEditSupplier(@ModelAttribute SupplierDto supplierToEdit, HttpSession session) {
		this.logService.log(OperationEnum.EDIT, this.userService.findByUsername(session.getAttribute("username").toString()), "SUPPLIERS");
		if (session.getAttribute("username") == null) {
			return "redirect:/users/login";
		}
		this.supplierService.upldateSupplier(supplierToEdit);
		return "redirect:/";
	}
	
	@GetMapping("/delete/{id}")
	public ModelAndView showDeleteSupplier(@PathVariable final Long id, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		if (session.getAttribute("username") == null) {
			mav.setViewName("redirect:/users/login");
		} else {
			mav.setViewName("delete-supplier");
			SupplierDto supplier = this.supplierService.getById(id);
			mav.addObject("supplier", supplier);
		}
		return mav;
	}
	
	@PostMapping("/delete/{id}")
	public String deleteSupplier(@PathVariable final Long id, HttpSession session) {
		this.logService.log(OperationEnum.DELETE, this.userService.findByUsername(session.getAttribute("username").toString()), "SUPPLIERS");
		this.supplierService.delete(id);
	
		return "redirect:/";
	}
	
}
