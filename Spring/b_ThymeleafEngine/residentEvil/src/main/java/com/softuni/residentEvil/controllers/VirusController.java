package com.softuni.residentEvil.controllers;

import com.softuni.residentEvil.models.binding.FullVirusDTO;
import com.softuni.residentEvil.services.CapitalService;
import com.softuni.residentEvil.services.VirusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("/viruses")
public class VirusController extends AbstractController {
	
	private VirusService virusService;
	private CapitalService capitalService;
	
	@Autowired
	public VirusController(VirusService virusService, CapitalService capitalService) {
		this.virusService = virusService;
		this.capitalService = capitalService;
	}

	@GetMapping("/add")
	@PreAuthorize("hasAnyRole('ADMIN', 'MODERATOR')")
	public ModelAndView getAdd(@ModelAttribute("virusModel") FullVirusDTO virusModel) {
		ModelAndView mav = super.view("add-virus");
		mav.addObject("virusModel", virusModel);
		mav.addObject("capitals", this.capitalService.getAllCapitals());
		mav.setViewName("add-virus");
		return mav;
	}

	@PostMapping("/add")
	@PreAuthorize("hasAnyRole('ADMIN', 'MODERATOR')")
	public ModelAndView addVirus(@Valid @ModelAttribute("virusModel") FullVirusDTO virusModel, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {

			return super.view("add-virus");
		}
		this.virusService.saveVirus(virusModel);
		return super.redirect("/");
	}

	@GetMapping("/show")
	@PreAuthorize("isAuthenticated()")
	public ModelAndView allViruses(@PageableDefault Pageable pageable) {
		return super.view("all-viruses", "viruses", this.virusService.findAllByPage(pageable));
	}
	
	@GetMapping("/edit/{id}")
	@PreAuthorize("hasAnyRole('ADMIN', 'MODERATOR')")
	public ModelAndView getEdit(@PathVariable String id) {
		ModelAndView mav = super.view("edit-virus");
		FullVirusDTO virusModel = this.virusService.findById(id);
		mav.addObject("virusModel", virusModel);
		mav.addObject("allCapitals", this.capitalService.getAllCapitals());
		return mav;
	}
	
	@PostMapping("/edit/{id}")
	@PreAuthorize("hasAnyRole('ADMIN', 'MODERATOR')")
	public ModelAndView editVirus(@Valid @ModelAttribute("virusModel") FullVirusDTO virusModel, BindingResult bindingResult, @PathVariable String id) {
		if (bindingResult.hasErrors()) {
			return super.redirect("/viruses/edit/" + id);
		}
		this.virusService.editVirus(virusModel);
		return super.redirect("/" + id);
	}
	
	@GetMapping("/delete/{id}")
	@PreAuthorize("hasAnyRole('ADMIN', 'MODERATOR')")
	public ModelAndView getDelete(@PathVariable String id) {
		ModelAndView mav = super.view("delete-virus");
		FullVirusDTO virusModel = this.virusService.findById(id);
		mav.addObject("virusModel", virusModel);
		mav.addObject("allCapitals", this.capitalService.getAllCapitals());
		return mav;
	}
	
	@PostMapping("/delete/{id}")
	@PreAuthorize("hasAnyRole('ADMIN', 'MODERATOR')")
	public ModelAndView deleteVirus(@PathVariable String id) {
		this.virusService.deleteVirus(id);
		return super.redirect("/");
	}
}
