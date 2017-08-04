package com.car_dealer.dtos.binding.add;

import com.google.gson.annotations.Expose;

public class SupplierAddDto {

    @Expose
    private String name;
    @Expose
    private Boolean isImporter;

    public SupplierAddDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getImporter() {
        return isImporter;
    }

    public void setImporter(Boolean importer) {
        isImporter = importer;
    }
}
