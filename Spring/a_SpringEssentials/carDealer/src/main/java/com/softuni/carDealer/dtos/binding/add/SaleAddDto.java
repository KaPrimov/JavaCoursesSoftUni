package com.softuni.carDealer.dtos.binding.add;

import com.softuni.carDealer.dtos.binding.relations.CarDto;
import com.softuni.carDealer.dtos.binding.relations.CustomerDto;

public class SaleAddDto {
    private Double discount;
    private Long customer;
    private Long car;

    public SaleAddDto() {
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Long getCustomer() {
        return customer;
    }

    public void setCustomer(Long customer) {
        this.customer = customer;
    }

    public Long getCar() {
        return car;
    }

    public void setCar(Long car) {
        this.car = car;
    }
}
