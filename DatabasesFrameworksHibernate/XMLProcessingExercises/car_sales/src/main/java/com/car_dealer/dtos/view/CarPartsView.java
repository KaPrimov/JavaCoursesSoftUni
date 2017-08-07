package com.car_dealer.dtos.view;

import com.google.gson.annotations.Expose;

import javax.xml.bind.annotation.*;
import java.util.Set;

@XmlAccessorType(XmlAccessType.FIELD)
public class CarPartsView {
    @Expose
    @XmlAttribute(name = "make")
    private String make;
    @Expose
    @XmlAttribute(name = "model")
    private String model;
    @Expose
    @XmlAttribute(name = "travelled-distance")
    private Long travelledDistance;
    @Expose
    @XmlElementWrapper(name = "parts")
    @XmlElement(name = "part")
    private Set<PartView> parts;

    public CarPartsView() {
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

    public Set<PartView> getParts() {
        return parts;
    }

    public void setParts(Set<PartView> parts) {
        this.parts = parts;
    }
}
