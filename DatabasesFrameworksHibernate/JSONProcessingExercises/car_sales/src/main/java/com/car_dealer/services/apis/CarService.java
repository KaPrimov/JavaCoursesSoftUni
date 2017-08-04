package com.car_dealer.services.apis;


import com.car_dealer.dtos.binding.add.CarAddDto;
import com.car_dealer.dtos.binding.relations.CarDto;
import com.car_dealer.dtos.view.CarMakerView;
import com.car_dealer.dtos.view.CarPartsView;

import java.util.List;

public interface CarService<Car, Long> {
    void saveDto(CarAddDto carAddDto);

    List<CarDto> findAllCarDtos();

    List<CarMakerView> findAllCarsByMaker(String make);

    List<CarPartsView> findAllCarsWithParts();
}
