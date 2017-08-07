package com.car_dealer.services.impls;

import com.car_dealer.dtos.binding.add.SaleAddDto;
import com.car_dealer.dtos.view.SaleWithCarView;
import com.car_dealer.dtos.view.xmlWrappers.SaleWithDiscountXmlWrapper;
import com.car_dealer.entities.Sale;
import com.car_dealer.repositories.SaleRepository;
import com.car_dealer.services.apis.SaleService;
import com.car_dealer.utils.ModelParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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
    public SaleWithDiscountXmlWrapper saleWithCarsXml() {
        List<SaleWithCarView> saleWithCarViews = this.saleWithCars();
        SaleWithDiscountXmlWrapper saleWithDiscountXmlWrapper = new SaleWithDiscountXmlWrapper();
        saleWithDiscountXmlWrapper.setSaleWithCarViews(saleWithCarViews);
        return saleWithDiscountXmlWrapper;
    }
}
