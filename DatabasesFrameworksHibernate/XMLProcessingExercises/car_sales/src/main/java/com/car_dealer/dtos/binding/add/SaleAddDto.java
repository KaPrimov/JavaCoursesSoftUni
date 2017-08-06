package com.car_dealer.dtos.binding.add;

import com.car_dealer.dtos.binding.relations.CarDto;
import com.car_dealer.dtos.binding.relations.CustomerDto;

public class SaleAddDto {
    private Double discount;
    private CustomerDto customer;
    private CarDto car;

    public SaleAddDto() {
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public CustomerDto getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDto customer) {
        this.customer = customer;
    }

    public CarDto getCar() {
        return car;
    }

    public void setCar(CarDto car) {
        this.car = car;
    }
}
