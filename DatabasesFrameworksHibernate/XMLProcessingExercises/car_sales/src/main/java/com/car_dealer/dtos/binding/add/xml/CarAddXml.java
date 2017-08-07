package com.car_dealer.dtos.binding.add.xml;

import com.car_dealer.dtos.binding.add.CarAddDto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "cars")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarAddXml {

    @XmlElement(name = "car")
    private List<CarAddDto> carAddDtos;

    public CarAddXml() {
    }

    public List<CarAddDto> getCarAddDtos() {
        return carAddDtos;
    }

    public void setCarAddDtos(List<CarAddDto> carAddDtos) {
        this.carAddDtos = carAddDtos;
    }
}
