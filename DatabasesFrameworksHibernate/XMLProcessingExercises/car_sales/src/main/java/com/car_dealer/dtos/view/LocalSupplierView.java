package com.car_dealer.dtos.view;

import com.google.gson.annotations.Expose;

public class LocalSupplierView {
    @Expose
    private Long id;
    @Expose
    private String name;
    @Expose
    private Integer partsCount;

    public LocalSupplierView(Long id, String name, Integer partsCount) {
        this.id = id;
        this.name = name;
        this.partsCount = partsCount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPartsCount() {
        return partsCount;
    }

    public void setPartsCount(Integer partsCount) {
        this.partsCount = partsCount;
    }
}
