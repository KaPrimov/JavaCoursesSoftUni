package com.car_dealer.dtos.view;

import com.car_dealer.utils.DateAdapter;
import com.google.gson.annotations.Expose;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Date;
import java.util.Set;

@XmlAccessorType(XmlAccessType.FIELD)
public class CustomerView {

    @Expose
    @XmlElement(name = "id")
    private Long id;
    @Expose
    @XmlElement(name = "name")
    private String name;
    @Expose
    @XmlElement(name = "birth-date")
    @XmlJavaTypeAdapter(DateAdapter.class)
    private Date birthDate;
    @Expose
    @XmlElement(name = "is-young-driver")
    private Boolean isYoungDriver;
    @Expose
    @XmlTransient
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
