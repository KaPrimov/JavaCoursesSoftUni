package com.car_dealer.dtos.view.xmlWrappers;

import com.car_dealer.dtos.view.CarMakerView;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "cars")
@XmlAccessorType(XmlAccessType.FIELD)
public class MakerCarsXmlWrapper {

    @XmlElement(name = "car")
    private List<CarMakerView> carMakerViewList;

    public MakerCarsXmlWrapper() {
    }

    public List<CarMakerView> getCarMakerViewList() {
        return carMakerViewList;
    }

    public void setCarMakerViewList(List<CarMakerView> carMakerViewList) {
        this.carMakerViewList = carMakerViewList;
    }
}
