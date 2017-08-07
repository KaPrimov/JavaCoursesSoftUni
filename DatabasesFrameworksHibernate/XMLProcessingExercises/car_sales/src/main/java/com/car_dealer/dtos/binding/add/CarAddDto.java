package com.car_dealer.dtos.binding.add;

import com.car_dealer.dtos.binding.relations.PartDto;
import com.google.gson.annotations.Expose;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;
import java.util.Set;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "car")
public class CarAddDto {

    @Expose
    @XmlElement(name = "make")
    private String make;
    @Expose
    @XmlElement(name = "model")
    private String model;
    @Expose
    @XmlElement(name = "travelled-distance")
    private Long travelledDistance;
    @Expose
    private BigDecimal price;
    @Expose
    private Set<PartDto> parts;

    public CarAddDto() {
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Set<PartDto> getParts() {
        return parts;
    }

    public void setParts(Set<PartDto> parts) {
        this.parts = parts;
    }


}
