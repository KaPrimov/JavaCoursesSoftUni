package com.softuni.carDealer.services.apis;

import java.util.List;

import com.softuni.carDealer.dtos.binding.add.SaleAddDto;
import com.softuni.carDealer.dtos.view.SaleWithCarView;

public interface SaleService<Sale, Long> {
    void saveSaleDto(SaleAddDto saleAddDto);

    List<SaleWithCarView> saleWithCars();
}
