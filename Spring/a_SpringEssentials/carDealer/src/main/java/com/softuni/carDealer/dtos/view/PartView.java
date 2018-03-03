package com.softuni.carDealer.dtos.view;


import java.math.BigDecimal;

public class PartView {

    private String name;

    private BigDecimal price;

    public PartView() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
