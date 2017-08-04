package com.car_dealer.dtos.binding.add;

import com.car_dealer.dtos.binding.relations.SupplierDto;
import com.google.gson.annotations.Expose;

import java.math.BigDecimal;

public class PartAddDto {
    @Expose
    private String name;
    @Expose
    private BigDecimal price;
    @Expose
    private Long quantity;
    @Expose
    private SupplierDto supplier;

    public PartAddDto() {
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

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public SupplierDto getSupplier() {
        return supplier;
    }

    public void setSupplier(SupplierDto supplier) {
        this.supplier = supplier;
    }
}
