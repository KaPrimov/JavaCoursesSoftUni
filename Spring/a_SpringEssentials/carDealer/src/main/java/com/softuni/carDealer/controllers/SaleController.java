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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.softuni.carDealer.dtos.binding.add.SaleAddDto;
import com.softuni.carDealer.dtos.binding.relations.CarDto;
import com.softuni.carDealer.dtos.binding.relations.CustomerDto;
import com.softuni.carDealer.dtos.view.ReviewSaleDTO;
import com.softuni.carDealer.dtos.view.SaleWithCarView;
import com.softuni.carDealer.entities.Car;
import com.softuni.carDealer.entities.Customer;
import com.softuni.carDealer.entities.Sale;
import com.softuni.carDealer.services.apis.CarService;
import com.softuni.carDealer.services.apis.CustomerService;
import com.softuni.carDealer.services.apis.SaleService;

@Controller
@RequestMapping("/sales")
public class SaleController {
	
	private SaleService<Sale, Long> saleService;
	private CustomerService<Customer, Long> customerService;
	private CarService<Car, Long> carService;
	
	@Autowired
	public SaleController(SaleService<Sale, Long> saleService, CustomerService<Customer, Long> customerService, CarService<Car, Long> carService) {
		this.saleService = saleService;
		this.customerService = customerService;
		this.carService = carService;
	}


	@GetMapping("")
	public ModelAndView queryAllSales() {
		List<SaleWithCarView> sales = saleService.saleWithCars();
		return extractModelAndView(sales);
	}
	
	@GetMapping("/{id}")
	public ModelAndView querySingleSaleSales(@PathVariable final Long id) {
		List<SaleWithCarView> sales = saleService.saleWithId(id);
		return extractModelAndView(sales);
	}
	
	@GetMapping("/discounted")
	public ModelAndView queryAnyDiscountedSales() {
		List<SaleWithCarView> sales = saleService.salesWithAnyDiscount();
		return extractModelAndView(sales);
	}
	
	@GetMapping("/discounted/{percentage}")
	public ModelAndView queryExactDiscountedSales(@PathVariable final Long percentage) {
		List<SaleWithCarView> sales = saleService.salesWithExactDiscount(percentage);
		return extractModelAndView(sales);
	}
	
	@GetMapping("/add")
	public ModelAndView showAddSale(HttpSession session) {
		ModelAndView mav = new ModelAndView();
		if (session.getAttribute("username") == null) {
			mav.setViewName("redirect:/users/login");
		} else {
			List<CarDto> cars = this.carService.findAllCarDtos();
			List<CustomerDto> customers = this.customerService.findAllCustomerDtos();
			
			mav.addObject("cars", cars);
			mav.addObject("customers", customers);
			mav.setViewName("add-sale");			
		}
		return mav;
	}
	

	@PostMapping("/add")
	public String addSale(@ModelAttribute SaleAddDto saleData, RedirectAttributes redirectAttributes, HttpSession session) { 
		if (session.getAttribute("username") == null) {
			return "redirect:/users/login";
		} else {
			ReviewSaleDTO reviewSale = this.saleService.reviewSale(saleData);
			redirectAttributes.addFlashAttribute("reviewSale", reviewSale);
			return "redirect:/sales/review";			
		}
	}
	
	@GetMapping("/review") 
	public ModelAndView reviewSale(@ModelAttribute("reviewSale") ReviewSaleDTO reviewSaleDTO, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		
		if (session.getAttribute("username") == null) {
			mav.setViewName("redirect:/users/login");
		} else {
			mav.setViewName("review-sale");
			mav.addObject("sale", reviewSaleDTO);
		}
		return mav;
	}
	
	@PostMapping("/review")
	public ModelAndView addSale(@ModelAttribute ReviewSaleDTO reviewSaleDTO, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		
		if (session.getAttribute("username") == null) {
			mav.setViewName("redirect:/users/login");
		} else {
			this.saleService.saveSaleDto(reviewSaleDTO);
			mav.setViewName("redirect:/");
		}
		return mav;
	}
	

	private ModelAndView extractModelAndView(List<SaleWithCarView> sales) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("sales-show-data");
		mav.addObject("sales", sales);
		return mav;
	}
}
