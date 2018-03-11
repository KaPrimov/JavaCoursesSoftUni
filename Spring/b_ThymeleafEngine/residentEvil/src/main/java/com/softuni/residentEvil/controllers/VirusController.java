package com.softuni.residentEvil.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.softuni.residentEvil.models.binding.FullVirusDTO;
import com.softuni.residentEvil.services.CapitalService;
import com.softuni.residentEvil.services.VirusService;

@Controller
@RequestMapping("/viruses")
public class VirusController {
	
	private VirusService virusService;
	private CapitalService capitalService;
	
	@Autowired
	public VirusController(VirusService virusService, CapitalService capitalService) {
		this.virusService = virusService;
		this.capitalService = capitalService;
	}

	@GetMapping("/add")
	public ModelAndView getAdd(@ModelAttribute("virusModel") FullVirusDTO virusModel) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("virusModel", virusModel);
		mav.addObject("capitals", this.capitalService.getAllCapitals());
		mav.setViewName("add-virus");
		return mav;
	}
	
	@PostMapping("/add") 
	public String addVirus(@Valid @ModelAttribute("virusModel") FullVirusDTO virusModel, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {

			return "add-virus";
		}
		this.virusService.saveVirus(virusModel);
		return "redirect:/";
	}
	
	@GetMapping("/show")
	public ModelAndView allViruses() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("viruses", this.virusService.gelAll());
		mav.setViewName("all-viruses");
		return mav;
	}
	
	@GetMapping("/edit/{id}")
	public ModelAndView getEdit(@PathVariable String id) {
		ModelAndView mav = new ModelAndView();
		FullVirusDTO virusModel = this.virusService.findById(id);
		mav.addObject("virusModel", virusModel);
		mav.addObject("allCapitals", this.capitalService.getAllCapitals());
		mav.setViewName("edit-virus");
		return mav;
	}
	
	@PostMapping("/edit/{id}") 
	public String editVirus(@Valid @ModelAttribute("virusModel") FullVirusDTO virusModel, BindingResult bindingResult, @PathVariable String id) {
		if (bindingResult.hasErrors()) {
			return "redirect:/viruses/edit/" + id;
		}
		this.virusService.editVirus(virusModel);
		return "redirect:/";
	}
	
	@GetMapping("/delete/{id}")
	public ModelAndView getDelete(@PathVariable String id) {
		ModelAndView mav = new ModelAndView();
		FullVirusDTO virusModel = this.virusService.findById(id);
		mav.addObject("virusModel", virusModel);
		mav.addObject("allCapitals", this.capitalService.getAllCapitals());
		mav.setViewName("delete-virus");
		return mav;
	}
	
	@PostMapping("/delete/{id}") 
	public String deleteVirus(@PathVariable String id) {
		this.virusService.deleteVirus(id);
		return "redirect:/";
	}
}
