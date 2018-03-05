package com.softuni.carDealer.dtos.view;


import java.util.Date;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;

public class CustomerView {

    private Long id;
    private String name;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthDate;
    private Boolean isYoungDriver;
    private Set<SaleView> sales;

    public CustomerView() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Boolean getYoungDriver() {
        return isYoungDriver;
    }

    public void setYoungDriver(Boolean youngDriver) {
        isYoungDriver = youngDriver;
    }

    public Set<SaleView> getSales() {
        return sales;
    }

    public void setSales(Set<SaleView> sales) {
        this.sales = sales;
    }
}
