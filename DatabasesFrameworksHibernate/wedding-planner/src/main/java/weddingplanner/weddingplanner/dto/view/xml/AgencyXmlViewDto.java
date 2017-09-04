package weddingplanner.weddingplanner.dto.view.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class AgencyXmlViewDto {
    @XmlAttribute(name = "name")
    private String name;
    @XmlElement(name = "wedding")
    private List<WeddingViewXmlDto> weddings;

    public AgencyXmlViewDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlAttribute(name = "profit")
    public Double getProfit() {
        double totalPrice = 0.0;
        for (int i = 1; i <= this.weddings.size(); i++) {
            totalPrice += (i * 1000);
        }
        totalPrice *= 0.2;
        BigDecimal bd = new BigDecimal(totalPrice);
        bd = bd.setScale(3, RoundingMode.HALF_UP);

        return bd.doubleValue();
    }

    public List<WeddingViewXmlDto> getWeddings() {
        return weddings;
    }

    public void setWeddings(List<WeddingViewXmlDto> weddings) {
        this.weddings = weddings;
    }
}
