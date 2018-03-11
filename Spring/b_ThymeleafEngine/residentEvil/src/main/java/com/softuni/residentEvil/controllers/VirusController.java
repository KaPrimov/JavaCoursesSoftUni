package com.softuni.residentEvil.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.softuni.residentEvil.models.binding.FullVirusDTO;
import com.softuni.residentEvil.services.CapitalService;
import com.softuni.residentEvil.services.VirusService;

@Controller
public class VirusController {
	
	private VirusService virusService;
	private CapitalService capitalService;
	
	@Autowired
	public VirusController(VirusService virusService, CapitalService capitalService) {
		this.virusService = virusService;
		this.capitalService = capitalService;
	}

	@GetMapping("/viruses/add")
	public ModelAndView getAdd(@ModelAttribute("virusModel") FullVirusDTO virusModel) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("virusModel", virusModel);
		mav.addObject("capitals", this.capitalService.getAllCapitals());
		mav.setViewName("add-virus");
		return mav;
	}
	
	@PostMapping("/viruses/add") 
	public String addVirus(@Valid @ModelAttribute("virusModel") FullVirusDTO virusModel, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {

			return "add-virus";
		}
		this.virusService.saveVirus(virusModel);
		return "redirect:/";
	}
	
	@GetMapping("/viruses/show")
	public ModelAndView allViruses() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("viruses", this.virusService.gelAll());
		mav.setViewName("all-viruses");
		return mav;
	}
}
