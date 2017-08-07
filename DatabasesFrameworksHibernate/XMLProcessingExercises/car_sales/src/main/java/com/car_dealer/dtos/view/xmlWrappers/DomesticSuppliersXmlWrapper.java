package com.car_dealer.dtos.view.xmlWrappers;

import com.car_dealer.dtos.view.LocalSupplierView;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "suppliers")
@XmlAccessorType(XmlAccessType.FIELD)
public class DomesticSuppliersXmlWrapper {

    @XmlElement(name = "supplier")
    private List<LocalSupplierView> localSupplierViews;

    public DomesticSuppliersXmlWrapper() {
    }

    public List<LocalSupplierView> getLocalSupplierViews() {
        return localSupplierViews;
    }

    public void setLocalSupplierViews(List<LocalSupplierView> localSupplierViews) {
        this.localSupplierViews = localSupplierViews;
    }
}
