package com.softuni.carDealer.dtos.binding.add;


import java.math.BigDecimal;
import java.util.Set;

public class CarAddDto {

    private String make;

    private String model;

    private Long travelledDistance;

    private BigDecimal price;

    private Set<Long> checkedParts;

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

	public Set<Long> getCheckedParts() {
		return checkedParts;
	}

	public void setCheckedParts(Set<Long> checkedParts) {
		this.checkedParts = checkedParts;
	}

}
