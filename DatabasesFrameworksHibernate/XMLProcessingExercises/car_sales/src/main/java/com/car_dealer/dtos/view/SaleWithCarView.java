package com.car_dealer.dtos.view;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

@XmlRootElement(name = "sale")
@XmlAccessorType(XmlAccessType.FIELD)
public class SaleWithCarView {
    @Expose
    @XmlElement(name = "car")
    private CarSaleView car;
    @Expose
    @XmlElement(name = "customer-name")
    private String customerName;
    @SerializedName("Discount")
    @Expose
    @XmlElement(name = "discount")
    private Double discount;
    @Expose
    @SerializedName("price")
    @XmlElement(name = "price")
    private BigDecimal carPrice;
    @Expose
    @XmlElement(name = "price-with-discount")
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
