package org.softuni.nuggets.controllers;

import org.softuni.nuggets.repositories.NuggetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/nuggets")
public class NuggetController {
    private final NuggetRepository nuggetRepository;

    @Autowired
    public NuggetController(NuggetRepository nuggetRepository) {
        this.nuggetRepository = nuggetRepository;
    }

    @GetMapping("/all")
    public ModelAndView all(ModelAndView modelAndView) {
        modelAndView.setViewName("nuggets-all");
        modelAndView.addObject("nuggets", this.nuggetRepository.findAll());

        return modelAndView;
    }
}
