package com.softuni.carDealer.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.softuni.carDealer.dtos.binding.add.CarAddDto;
import com.softuni.carDealer.dtos.binding.add.PartAddDto;
import com.softuni.carDealer.dtos.binding.relations.PartDto;
import com.softuni.carDealer.dtos.binding.relations.SupplierDto;
import com.softuni.carDealer.dtos.view.CarMakerView;
import com.softuni.carDealer.dtos.view.CarPartsView;
import com.softuni.carDealer.entities.Car;
import com.softuni.carDealer.entities.Part;
import com.softuni.carDealer.services.apis.CarService;
import com.softuni.carDealer.services.apis.PartService;

@RequestMapping("/cars")
@Controller
public class CarController {
	
	private CarService<Car, Long> carService;
	private PartService<Part, Long> partService;
	
	@Autowired	
	public CarController(CarService<Car, Long> carService, PartService<Part, Long> partService) {
		this.carService = carService;
		this.partService = partService;
	}
	
	@GetMapping("/{make}")
	public ModelAndView make(@PathVariable String make) {
		List<CarMakerView> carsByMaker = carService.findAllCarsByMaker(make);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("cars-by-make");
		mav.addObject("cars", carsByMaker);
		
		return mav;
	}
	
	@GetMapping("/{id}/parts")
	public ModelAndView carWithParts(@PathVariable Long id) {
		ModelAndView mav = new ModelAndView();
		List<CarPartsView> cars = carService.findAllCarsWithParts();
		mav.addObject("cars", cars);
		mav.setViewName("cars-with-parts");
		
		return mav;
	}
	
	@GetMapping("/add")
	public ModelAndView showAddCar() {
		ModelAndView mav = new ModelAndView();
		List<PartDto> parts = this.partService.findAllPartDtos();
		mav.setViewName("add-car");
		mav.addObject("parts", parts);
		return mav;
	}
	
	@PostMapping("/add")
	public String saveCar(@ModelAttribute CarAddDto carAddDto) {
		this.carService.saveDto(carAddDto);		
		return "redirect:/";
	}
}
