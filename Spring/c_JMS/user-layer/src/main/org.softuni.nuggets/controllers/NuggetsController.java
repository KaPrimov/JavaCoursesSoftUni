package org.softuni.nuggets.controllers;

import org.softuni.nuggets.services.NuggetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.jms.JMSException;

@Controller
public class NuggetsController {

    private final JmsTemplate jmsTemplate;
    private final NuggetService nuggetService;

    @Autowired
    public NuggetsController(JmsTemplate jmsTemplate, NuggetService nuggetService) {
        this.jmsTemplate = jmsTemplate;
        this.nuggetService = nuggetService;
    }

    @GetMapping("/all-nuggets")
    public ModelAndView all(ModelAndView modelAndView) throws JMSException {
        this.jmsTemplate.setDefaultDestinationName("get-nuggets");
        this.jmsTemplate.convertAndSend("get-nuggets");
        String[] nuggets = this.nuggetService.getAllNuggets();
        modelAndView.setViewName("nuggets-all");
        modelAndView.addObject("nuggets", nuggets == null ? new String[0] : nuggets);
        modelAndView.addObject("title", "All Nuggets");
        return modelAndView;
    }
}
