package com.softuni.carDealer.dtos.binding.relations;


import java.math.BigDecimal;

public class PartDto {

    private Long id;

    private BigDecimal price;

    public PartDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
