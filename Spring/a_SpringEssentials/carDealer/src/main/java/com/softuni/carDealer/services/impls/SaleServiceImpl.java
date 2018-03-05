package com.softuni.carDealer.services.impls;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softuni.carDealer.dtos.binding.add.SaleAddDto;
import com.softuni.carDealer.dtos.view.SaleWithCarView;
import com.softuni.carDealer.entities.Sale;
import com.softuni.carDealer.repositories.SaleRepository;
import com.softuni.carDealer.services.apis.SaleService;
import com.softuni.carDealer.utils.ModelParser;

@Service
@Transactional
public class SaleServiceImpl implements SaleService<Sale, Long> {
    private final SaleRepository saleRepository;

    @Autowired
    public SaleServiceImpl(SaleRepository carRepository) {
        this.saleRepository = carRepository;
    }

    @Override
    public void saveSaleDto(SaleAddDto saleAddDto) {
        Sale sale = ModelParser.getInstance().map(saleAddDto, Sale.class);
        this.saleRepository.saveAndFlush(sale);
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
}
