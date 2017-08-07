package com.car_dealer.dtos.view.xmlWrappers;

import com.car_dealer.dtos.view.SaleWithCarView;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "sales")
@XmlAccessorType(XmlAccessType.FIELD)
public class SaleWithDiscountXmlWrapper {

    @XmlElement(name = "car")
    private List<SaleWithCarView> saleWithCarViews;

    public SaleWithDiscountXmlWrapper() {
    }

    public List<SaleWithCarView> getSaleWithCarViews() {
        return saleWithCarViews;
    }

    public void setSaleWithCarViews(List<SaleWithCarView> saleWithCarViews) {
        this.saleWithCarViews = saleWithCarViews;
    }
}
