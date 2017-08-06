package com.car_dealer.services.apis;

import com.car_dealer.dtos.binding.add.SaleAddDto;
import com.car_dealer.dtos.view.SaleWithCarView;

import java.util.List;

public interface SaleService<Sale, Long> {
    void saveSaleDto(SaleAddDto saleAddDto);

    List<SaleWithCarView> saleWithCars();
}
