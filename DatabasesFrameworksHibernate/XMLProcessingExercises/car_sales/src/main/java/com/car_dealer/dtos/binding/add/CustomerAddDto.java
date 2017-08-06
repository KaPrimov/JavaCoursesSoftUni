package com.car_dealer.dtos.binding.add;

import com.google.gson.annotations.Expose;

import java.util.Date;

public class CustomerAddDto {
    @Expose
    private String name;
    @Expose
    private Date birthDate;
    @Expose
    private Boolean isYoungDriver;

    public CustomerAddDto() {
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
}
