package com.car_dealer.dtos.binding.relations;

import com.google.gson.annotations.Expose;

import java.math.BigDecimal;

public class PartDto {

    @Expose
    private Long id;

    @Expose
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
