package softuni.photography.dto.viewModels;

import javax.xml.bind.annotation.*;
import java.math.BigDecimal;

@XmlAccessorType(XmlAccessType.FIELD)
public class WorkshopDtoWrapper {

    @XmlAttribute(name = "name")
    private String name;
    @XmlTransient
    private BigDecimal price;
    @XmlElement(name = "participants")
    private ParticipantsXmlDtoWrapper participants;

    public WorkshopDtoWrapper() {}

    public String getName() {
        return name;
    }

    @XmlAttribute(name = "total-profit")
    public BigDecimal getTotalProfit() {
        return BigDecimal.valueOf(this.getParticipants().getCount())
                .multiply(this.getPrice().multiply(BigDecimal.valueOf(0.8)));
    }

    public ParticipantsXmlDtoWrapper getParticipants() {
        return participants;
    }

    public void setParticipants(ParticipantsXmlDtoWrapper participants) {
        this.participants = participants;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
