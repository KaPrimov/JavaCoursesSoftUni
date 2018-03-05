package com.softuni.carDealer.services.impls;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softuni.carDealer.dtos.binding.add.CarAddDto;
import com.softuni.carDealer.dtos.binding.relations.CarDto;
import com.softuni.carDealer.dtos.view.CarMakerView;
import com.softuni.carDealer.dtos.view.CarPartsView;
import com.softuni.carDealer.entities.Car;
import com.softuni.carDealer.entities.Part;
import com.softuni.carDealer.repositories.CarRepository;
import com.softuni.carDealer.repositories.PartRepository;
import com.softuni.carDealer.services.apis.CarService;
import com.softuni.carDealer.utils.ModelParser;

@Service
@Transactional
public class CarServiceImpl implements CarService<Car, Long> {
    
	private final CarRepository carRepository;
	private final PartRepository partRepository;
	
    @Autowired
    public CarServiceImpl(CarRepository carRepository, PartRepository partRepository) {
        this.carRepository = carRepository;
        this.partRepository = partRepository;
    }

    @Override
    public void saveDto(CarAddDto carAddDto) {
        Car car = ModelParser.getInstance().map(carAddDto, Car.class);
        Set<Part> parts = new LinkedHashSet<>();
        for (Long partId : carAddDto.getCheckedParts()) {
			parts.add(partRepository.getOne(partId));
		}
        car.setParts(parts);
        this.carRepository.saveAndFlush(car);
    }

    @Override
    public List<CarDto> findAllCarDtos() {
        List<Car> cars = this.carRepository.findAll();
        List<CarDto> carDtos = new ArrayList<>();

        for (Car car : cars) {
            CarDto carDto = ModelParser.getInstance().map(car, CarDto.class);
            carDto.setName(car.getMake() + " - " + car.getModel());
			carDtos.add(carDto);
        }

        return carDtos;
    }

    @Override
    public List<CarMakerView> findAllCarsByMaker(String make) {
        List<Car> cars = this.carRepository.findAllByMakeOrderByModelAscTravelledDistanceDesc(make);
        List<CarMakerView> carViews = new ArrayList<>();
        for (Car car : cars) {
            carViews.add(ModelParser.getInstance().map(car, CarMakerView.class));
        }
        return carViews;
    }

    @Override
    public List<CarPartsView> findAllCarsWithParts() {
        List<Car> cars = this.carRepository.findAll();
        List<CarPartsView> carPartsViews = new ArrayList<>();

        for (Car car : cars) {
            carPartsViews.add(ModelParser.getInstance().map(car, CarPartsView.class));
        }
        return carPartsViews;
    }
}
