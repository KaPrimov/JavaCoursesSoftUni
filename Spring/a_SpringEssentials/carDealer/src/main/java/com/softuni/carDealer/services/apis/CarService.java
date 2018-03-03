package com.softuni.carDealer.services.apis;


import java.util.List;

import com.softuni.carDealer.dtos.binding.add.CarAddDto;
import com.softuni.carDealer.dtos.binding.relations.CarDto;
import com.softuni.carDealer.dtos.view.CarMakerView;
import com.softuni.carDealer.dtos.view.CarPartsView;

public interface CarService<Car, Long> {
    void saveDto(CarAddDto carAddDto);

    List<CarDto> findAllCarDtos();

    List<CarMakerView> findAllCarsByMaker(String make);

    List<CarPartsView> findAllCarsWithParts();
}
