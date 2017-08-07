package com.car_dealer.services.impls;

import com.car_dealer.dtos.binding.add.CarAddDto;
import com.car_dealer.dtos.binding.relations.CarDto;
import com.car_dealer.dtos.view.CarMakerView;
import com.car_dealer.dtos.view.CarPartsView;
import com.car_dealer.dtos.view.xmlWrappers.CarsPartsXmlWrapper;
import com.car_dealer.dtos.view.xmlWrappers.MakerCarsXmlWrapper;
import com.car_dealer.entities.Car;
import com.car_dealer.repositories.CarRepository;
import com.car_dealer.services.apis.CarService;
import com.car_dealer.utils.ModelParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CarServiceImpl implements CarService<Car, Long> {
    private final CarRepository carRepository;

    @Autowired
    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public void saveDto(CarAddDto carAddDto) {
        Car car = ModelParser.getInstance().map(carAddDto, Car.class);
        String deb = "";
        this.carRepository.saveAndFlush(car);
    }

    @Override
    public List<CarDto> findAllCarDtos() {
        List<Car> cars = this.carRepository.findAll();
        List<CarDto> carDtos = new ArrayList<>();

        for (Car car : cars) {
            carDtos.add(ModelParser.getInstance().map(car, CarDto.class));
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
    public MakerCarsXmlWrapper findAllCarsByMakerXml(String make) {
        List<CarMakerView> carMakerViews = this.findAllCarsByMaker(make);
        MakerCarsXmlWrapper xmlWrapper = new MakerCarsXmlWrapper();
        xmlWrapper.setCarMakerViewList(carMakerViews);
        return xmlWrapper;
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

    @Override
    public CarsPartsXmlWrapper findAllCarsWithPartsXml() {
        List<CarPartsView> allCarsWithParts = this.findAllCarsWithParts();
        CarsPartsXmlWrapper carsPartsXmlWrapper = new CarsPartsXmlWrapper();
        carsPartsXmlWrapper.setCarPartsViews(allCarsWithParts);
        return carsPartsXmlWrapper;
    }
}
