package com.car_dealer.dtos.view;

import com.google.gson.annotations.Expose;

import java.util.Date;
import java.util.Set;

public class CustomerView {

    @Expose
    private Long id;
    @Expose
    private String name;
    @Expose
    private Date birthDate;
    @Expose
    private Boolean isYoungDriver;
    @Expose
    private Set<SaleView> sales;

    public CustomerView() {
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
