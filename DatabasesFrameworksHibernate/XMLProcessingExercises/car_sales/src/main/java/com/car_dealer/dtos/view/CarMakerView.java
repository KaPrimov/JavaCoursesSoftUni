package com.car_dealer.dtos.view;

import com.google.gson.annotations.Expose;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@XmlAccessorType(XmlAccessType.FIELD)
public class CarMakerView {
    @Expose
    @XmlAttribute(name = "id")
    private Long id;
    @Expose
    @XmlAttribute(name = "make")
    private String make;
    @Expose
    @XmlAttribute(name = "model")
    private String model;
    @Expose
    @XmlAttribute(name = "travelled-distance")
    private Long travelledDistance;

    public CarMakerView() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Long getTravelledDistance() {
        return travelledDistance;
    }

    public void setTravelledDistance(Long travelledDistance) {
        this.travelledDistance = travelledDistance;
    }
}
