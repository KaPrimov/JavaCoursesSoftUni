package com.softuni.carDealer.dtos.binding.add;


public class SupplierAddDto {

    private String name;
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

    public void setImporter(Boolean isImporter) {
        this.isImporter = isImporter;
    }
}
