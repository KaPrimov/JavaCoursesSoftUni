package com.softuni.carDealer.dtos.view;


import java.math.BigDecimal;

public class SaleWithCarView {

    private CarSaleView car;

    private String customerName;
    private Double discount;
    private BigDecimal carPrice;
    private BigDecimal priceWithDiscount;

    public SaleWithCarView() {
    }

    public CarSaleView getCar() {
        return car;
    }

    public void setCar(CarSaleView car) {
        this.car = car;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public BigDecimal getCarPrice() {
        return carPrice;
    }

    public void setCarPrice(BigDecimal carPrice) {
        this.carPrice = carPrice;
    }

    public BigDecimal getPriceWithDiscount() {
        return priceWithDiscount;
    }

    public void setPriceWithDiscount(BigDecimal priceWithDiscount) {
        this.priceWithDiscount = priceWithDiscount;
    }
}
