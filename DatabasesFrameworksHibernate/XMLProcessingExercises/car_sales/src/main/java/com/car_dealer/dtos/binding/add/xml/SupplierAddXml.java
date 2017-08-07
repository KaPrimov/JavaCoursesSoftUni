package com.car_dealer.dtos.binding.add.xml;

import com.car_dealer.dtos.binding.add.SupplierAddDto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "suppliers")
@XmlAccessorType(XmlAccessType.FIELD)
public class SupplierAddXml {

    @XmlElement(name = "supplier")
    private List<SupplierAddDto> supplierAddDtos;

    public SupplierAddXml() {
    }

    public List<SupplierAddDto> getSupplierAddDtos() {
        return supplierAddDtos;
    }

    public void setSupplierAddDtos(List<SupplierAddDto> supplierAddDtos) {
        this.supplierAddDtos = supplierAddDtos;
    }
}
