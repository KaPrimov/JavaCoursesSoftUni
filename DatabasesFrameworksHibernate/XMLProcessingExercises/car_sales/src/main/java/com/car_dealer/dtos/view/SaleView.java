package com.car_dealer.dtos.view;

import com.google.gson.annotations.Expose;

public class SaleView {
    @Expose
    private Long id;
    @Expose
    private Double discount;
    @Expose
    private String customerName;
    @Expose
    private String carModel;

    public SaleView() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }
}
