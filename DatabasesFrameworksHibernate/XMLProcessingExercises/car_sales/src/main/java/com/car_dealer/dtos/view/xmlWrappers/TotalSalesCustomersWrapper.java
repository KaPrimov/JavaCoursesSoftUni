package com.car_dealer.dtos.view.xmlWrappers;

import com.car_dealer.dtos.view.TotalCustomerSalesView;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "customers")
@XmlAccessorType(XmlAccessType.FIELD)
public class TotalSalesCustomersWrapper {

    @XmlElement(name = "customer")
    private List<TotalCustomerSalesView> totalCustomerSalesViews;

    public TotalSalesCustomersWrapper() {
    }

    public List<TotalCustomerSalesView> getTotalCustomerSalesViews() {
        return totalCustomerSalesViews;
    }

    public void setTotalCustomerSalesViews(List<TotalCustomerSalesView> totalCustomerSalesViews) {
        this.totalCustomerSalesViews = totalCustomerSalesViews;
    }
}
