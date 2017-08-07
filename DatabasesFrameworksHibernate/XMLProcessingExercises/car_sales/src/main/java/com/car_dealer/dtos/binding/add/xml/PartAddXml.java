package com.car_dealer.dtos.binding.add.xml;

import com.car_dealer.dtos.binding.add.PartAddDto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "parts")
@XmlAccessorType(XmlAccessType.FIELD)
public class PartAddXml {

    @XmlElement(name = "part")
    private List<PartAddDto> partAddDtos;

    public PartAddXml() {
    }

    public List<PartAddDto> getPartAddDtos() {
        return partAddDtos;
    }

    public void setPartAddDtos(List<PartAddDto> partAddDtos) {
        this.partAddDtos = partAddDtos;
    }
}
