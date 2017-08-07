package com.car_dealer.dtos.binding.add.xml;

import com.car_dealer.dtos.binding.add.CustomerAddDto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "customers")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomerAddXml {

    @XmlElement(name = "customer")
    private List<CustomerAddDto> customerAddDtos;

    public CustomerAddXml() {
    }

    public List<CustomerAddDto> getCustomerAddDtos() {
        return customerAddDtos;
    }

    public void setCustomerAddDtos(List<CustomerAddDto> customerAddDtos) {
        this.customerAddDtos = customerAddDtos;
    }
}
