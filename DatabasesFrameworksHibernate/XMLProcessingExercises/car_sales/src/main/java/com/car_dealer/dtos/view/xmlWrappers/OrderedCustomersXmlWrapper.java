package com.car_dealer.dtos.view.xmlWrappers;

import com.car_dealer.dtos.view.CustomerView;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "customers")
@XmlAccessorType(XmlAccessType.FIELD)
public class OrderedCustomersXmlWrapper {

    @XmlElement(name = "customer")
    private List<CustomerView> customerViews;

    public OrderedCustomersXmlWrapper() {
    }

    public List<CustomerView> getCustomerViews() {
        return customerViews;
    }

    public void setCustomerViews(List<CustomerView> customerViews) {
        this.customerViews = customerViews;
    }
}
