package com.softuni.residentEvil.controllers;

import com.softuni.residentEvil.services.VirusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/map")
public class MapController {
	private final VirusService virusService;

	@Autowired
	public MapController(VirusService virusService) {
		this.virusService = virusService;
	}

	@GetMapping("")
    @PreAuthorize("isAuthenticated()")
	public ModelAndView getMap() {
	    ModelAndView mav = new ModelAndView();
        String geoJson = this.virusService.findAllMapViruses();
        mav.addObject("geoJson", geoJson);
	    mav.setViewName("map");
		return mav;
	}
}
