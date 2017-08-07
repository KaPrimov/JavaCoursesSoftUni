package com.car_dealer.dtos.view.xmlWrappers;

import com.car_dealer.dtos.view.CarPartsView;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "cars")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarsPartsXmlWrapper {

    @XmlElement(name = "car")
    private List<CarPartsView> carPartsViews;

    public CarsPartsXmlWrapper() {
    }

    public List<CarPartsView> getCarPartsViews() {
        return carPartsViews;
    }

    public void setCarPartsViews(List<CarPartsView> carPartsViews) {
        this.carPartsViews = carPartsViews;
    }
}
