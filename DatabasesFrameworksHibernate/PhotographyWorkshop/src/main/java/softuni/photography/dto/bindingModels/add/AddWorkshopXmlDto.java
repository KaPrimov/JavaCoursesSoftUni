package softuni.photography.dto.bindingModels.add;

import javax.xml.bind.annotation.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "workshop")
public class AddWorkshopXmlDto {
    @XmlAttribute(name = "name")
    private String name;
    @XmlAttribute(name = "start-date")
    private Date startDate;
    @XmlAttribute(name = "end-date")
    private Date endDate;
    @XmlAttribute(name = "location")
    private String location;
    @XmlAttribute(name = "price")
    private BigDecimal price;
    @XmlElement(name = "trainer")
    private String trainerFullName;
    @XmlElementWrapper(name = "participants")
    @XmlElement(name = "participant")
    private List<PhotographerInWorkshopDto> participants;

    public AddWorkshopXmlDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getTrainerFullName() {
        return trainerFullName;
    }

    public void setTrainerFullName(String trainerFullName) {
        this.trainerFullName = trainerFullName;
    }

    public List<PhotographerInWorkshopDto> getParticipants() {
        return participants;
    }

    public void setParticipants(List<PhotographerInWorkshopDto> participants) {
        this.participants = participants;
    }
}
