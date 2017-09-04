package weddingplanner.weddingplanner.dto.view.xml;

import javax.xml.bind.annotation.*;
import java.math.BigDecimal;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class WeddingViewXmlDto {

    @XmlTransient
    private Long id;
    @XmlAttribute(name = "cash")
    private BigDecimal cash;
    @XmlAttribute(name = "count")
    private Long count;
    @XmlElement(name = "bride")
    private String brideFullName;
    @XmlElement(name = "bridegroom")
    private String bridegroomFullName;
    @XmlElementWrapper(name = "guests")
    @XmlElement(name = "guest")
    private List<GuestXmlViewDto> guestXmlViewDtos;

    public WeddingViewXmlDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getCash() {
        return cash;
    }

    public void setCash(BigDecimal cash) {
        this.cash = cash;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public String getBrideFullName() {
        return brideFullName;
    }

    public void setBrideFullName(String brideFullName) {
        this.brideFullName = brideFullName;
    }

    public String getBridegroomFullName() {
        return bridegroomFullName;
    }

    public void setBridegroomFullName(String bridegroomFullName) {
        this.bridegroomFullName = bridegroomFullName;
    }

    public List<GuestXmlViewDto> getGuestXmlViewDtos() {
        return guestXmlViewDtos;
    }

    public void setGuestXmlViewDtos(List<GuestXmlViewDto> guestXmlViewDtos) {
        this.guestXmlViewDtos = guestXmlViewDtos;
    }
}
