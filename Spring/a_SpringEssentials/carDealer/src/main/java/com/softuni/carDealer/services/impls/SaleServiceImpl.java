package com.softuni.carDealer.services.impls;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softuni.carDealer.dtos.binding.add.SaleAddDto;
import com.softuni.carDealer.dtos.view.ReviewSaleDTO;
import com.softuni.carDealer.dtos.view.SaleWithCarView;
import com.softuni.carDealer.entities.Car;
import com.softuni.carDealer.entities.Customer;
import com.softuni.carDealer.entities.Sale;
import com.softuni.carDealer.repositories.CarRepository;
import com.softuni.carDealer.repositories.CustomerRepository;
import com.softuni.carDealer.repositories.SaleRepository;
import com.softuni.carDealer.services.apis.SaleService;
import com.softuni.carDealer.utils.ModelParser;

@Service
@Transactional
public class SaleServiceImpl implements SaleService<Sale, Long> {
    
	private final SaleRepository saleRepository;
    private final CustomerRepository customerRepository;
    private final CarRepository carRepository;

    @Autowired
    public SaleServiceImpl(SaleRepository saleRepository, CustomerRepository customerRepository, CarRepository carRepository) {
		this.saleRepository = saleRepository;
		this.customerRepository = customerRepository;
		this.carRepository = carRepository;
	}

	@Override
    public void saveSaleDto(SaleAddDto saleAddDto) {
        Sale sale = ModelParser.getInstance().map(saleAddDto, Sale.class);
        this.saleRepository.saveAndFlush(sale);
    }

	@Override
	public void saveSaleDto(ReviewSaleDTO saleAddDto) {
		Sale sale = new Sale();
		sale.setCar(this.carRepository.getOne(saleAddDto.getCarId()));
		sale.setCustomer(this.customerRepository.getOne(saleAddDto.getCustomerId()));
		sale.setDiscount(saleAddDto.getDiscount() / 100.0);
		this.saleRepository.save(sale);
	}	

    @Override
    public List<SaleWithCarView> saleWithCars() {
        List<Sale> cars = this.saleRepository.findAll();
        return convertSalesToDTOs(cars);
    }
    
    @Override
	public List<SaleWithCarView> saleWithId(Long id) {
    	List<Sale> cars = this.saleRepository.findAllById(id);
        return convertSalesToDTOs(cars);
	}

	@Override
	public List<SaleWithCarView> salesWithAnyDiscount() {
		List<Sale> cars = this.saleRepository.salesWithAnyDiscount();
        return convertSalesToDTOs(cars);
	}
	
	@Override
	public List<SaleWithCarView> salesWithExactDiscount(final Long discount) {
		List<Sale> cars = this.saleRepository.salesWithExactDiscount(discount / 100.0);
        return convertSalesToDTOs(cars);
	}
    
	private List<SaleWithCarView> convertSalesToDTOs(List<Sale> cars) {
		List<SaleWithCarView> saleWithCarViews = new ArrayList<>();
        for (Sale car : cars) {
            SaleWithCarView saleWithCarView = ModelParser.getInstance().map(car, SaleWithCarView.class);
            BigDecimal price = saleWithCarView.getCarPrice().multiply(BigDecimal.valueOf(1 - saleWithCarView.getDiscount()));
            saleWithCarView.setPriceWithDiscount(price);
            saleWithCarViews.add(saleWithCarView);
        }
		return saleWithCarViews;
	}

	@Override
	public ReviewSaleDTO reviewSale(final SaleAddDto saleData) {
		Car car = this.carRepository.getOne(saleData.getCar());
		Customer customer = this.customerRepository.getOne(saleData.getCustomer());
		Double discount = saleData.getDiscount();
		
		if (customer.getYoungDriver()) {
			discount += 0.05;
		}
		
		ReviewSaleDTO reviewSale = new ReviewSaleDTO();
		reviewSale.setCar(car.getMake() + " " + car.getModel());
		reviewSale.setCustomer(customer.getName());
		reviewSale.setCarPrice(car.getPrice());
		BigDecimal finalPrice = car.getPrice().multiply(new BigDecimal(1.0 - discount)).setScale(2, RoundingMode.CEILING);
		reviewSale.setFinalPrice(finalPrice);		
		reviewSale.setCarId(saleData.getCar());
		reviewSale.setCustomerId(saleData.getCustomer());
		reviewSale.setDiscount(discount * 100);
		return reviewSale;
	}
}
