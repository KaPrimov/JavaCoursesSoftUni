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
import com.softuni.carDealer.dtos.binding.add.PartAddDto;
import com.softuni.carDealer.dtos.binding.relations.PartDto;
import com.softuni.carDealer.dtos.binding.relations.SupplierDto;
import com.softuni.carDealer.entities.Part;
import com.softuni.carDealer.entities.Supplier;
import com.softuni.carDealer.services.apis.PartService;
import com.softuni.carDealer.services.apis.SupplierService;

@RequestMapping("/parts")
@Controller
public class PartController {
	
	private PartService<Part, Long> partService;
	private SupplierService<Supplier, Long> supplierService;
	
	@Autowired
	public PartController(PartService<Part, Long> partService, SupplierService<Supplier, Long> supplierService) {
		this.partService = partService;
		this.supplierService = supplierService;
	}
	
	@GetMapping("/all")
	public ModelAndView getAll() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("all-parts");
		mav.addObject("parts", this.partService.findAllPartDtos());
		return mav;
	}

	@GetMapping("/add")
	public ModelAndView showAddPart() {
		ModelAndView mav = new ModelAndView();
		List<SupplierDto> suppliers = this.supplierService.findAllDtos();
		mav.setViewName("add-part");
		mav.addObject("suppliers", suppliers);
		return mav;
	}
	
	@PostMapping("/add")
	public String savePart(@ModelAttribute PartAddDto partToAdd) {
		this.partService.save(partToAdd);		
		return "redirect:/";
	}
	

	@GetMapping("/edit/{id}")
	public ModelAndView showEditPart(@PathVariable final Long id) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("edit-part");
		mav.addObject("part", this.partService.getById(id));
		mav.addObject("suppliers", this.supplierService.findAllDtos());
		return mav;
	}
	
	@PostMapping("/edit")
	public String editPart(@ModelAttribute final PartAddDto partAddDto) {

		this.partService.updatePart(partAddDto);
	
		return "redirect:/";
	}
	
	@GetMapping("/delete/{id}")
	public ModelAndView showDeletePart(@PathVariable final Long id) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("delete-part");
		PartDto part = this.partService.getById(id);
		mav.addObject("part", part);
		mav.addObject("supplier", this.supplierService.getById(part.getSupplierId()));
		return mav;
	}
	
	@PostMapping("/delete/{id}")
	public String deleteart(@PathVariable final Long id) {

		this.partService.delete(id);
	
		return "redirect:/";
	}
}
