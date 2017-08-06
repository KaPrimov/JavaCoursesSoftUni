package com.car_dealer.dtos.view;

import com.google.gson.annotations.Expose;

import java.math.BigDecimal;

public class TotalCustomerSalesView {
    @Expose
    private String fullName;
    @Expose
    private Integer boughtCars;
    @Expose
    private BigDecimal spentMoney;

    public TotalCustomerSalesView(String fullName, Integer boughtCars, BigDecimal spentMoney) {
        this.fullName = fullName;
        this.boughtCars = boughtCars;
        this.spentMoney = spentMoney;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Integer getBoughtCars() {
        return boughtCars;
    }

    public void setBoughtCars(Integer boughtCars) {
        this.boughtCars = boughtCars;
    }

    public BigDecimal getSpentMoney() {
        return spentMoney;
    }

    public void setSpentMoney(BigDecimal spentMoney) {
        this.spentMoney = spentMoney;
    }
}
