package com.car_dealer.dtos.binding.relations;

import com.google.gson.annotations.Expose;

public class SupplierDto {
    @Expose
    private Long id;

    public SupplierDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
