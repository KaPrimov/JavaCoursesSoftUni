package com.softuni.carDealer.dtos.binding.add;


import java.math.BigDecimal;
import java.util.Set;

import com.softuni.carDealer.dtos.binding.relations.PartDto;

public class CarAddDto {

    private String make;

    private String model;

    private Long travelledDistance;

    private BigDecimal price;

    private Set<PartDto> parts;

    public CarAddDto() {
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Long getTravelledDistance() {
        return travelledDistance;
    }

    public void setTravelledDistance(Long travelledDistance) {
        this.travelledDistance = travelledDistance;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Set<PartDto> getParts() {
        return parts;
    }

    public void setParts(Set<PartDto> parts) {
        this.parts = parts;
    }


}
