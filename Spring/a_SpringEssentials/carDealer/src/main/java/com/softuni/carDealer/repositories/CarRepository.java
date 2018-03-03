package com.softuni.carDealer.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.softuni.carDealer.entities.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findAllByMakeOrderByModelAscTravelledDistanceDesc(String make);

}
