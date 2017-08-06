package com.car_dealer.dtos.view;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;

public class SaleWithCarView {
    @Expose
    private CarSaleView car;
    @Expose
    private String customerName;
    @SerializedName("Discount")
    @Expose
    private Double discount;
    @Expose
    @SerializedName("price")
    private BigDecimal carPrice;
    @Expose
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
