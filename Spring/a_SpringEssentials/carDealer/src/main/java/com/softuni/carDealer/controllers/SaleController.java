package com.softuni.carDealer.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.softuni.carDealer.dtos.view.SaleWithCarView;
import com.softuni.carDealer.entities.Sale;
import com.softuni.carDealer.services.apis.SaleService;

@Controller
@RequestMapping("/sales")
public class SaleController {
	
	private SaleService<Sale, Long> saleService;
	
	@Autowired
	public SaleController(SaleService<Sale, Long> saleService) {
		this.saleService = saleService;
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
	
	@GetMapping("/discounted/{persentage}")
	public ModelAndView queryExactDiscountedSales(@PathVariable final Long persentage) {
		List<SaleWithCarView> sales = saleService.salesWithExactDiscount(persentage);
		return extractModelAndView(sales);
	}

	private ModelAndView extractModelAndView(List<SaleWithCarView> sales) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("sales-show-data");
		mav.addObject("sales", sales);
		return mav;
	}
}
