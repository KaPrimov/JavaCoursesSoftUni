package softuni.photography.dto.viewModels;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class LocationDto {

    @XmlAttribute(name = "name")
    private String workshopLocation;

    @XmlElement(name = "workshop")
    private List<WorkshopDtoWrapper> workshops;

    public LocationDto() {
    }

    public String getWorkshopLocation() {
        return workshopLocation;
    }

    public void setWorkshopLocation(String workshopLocation) {
        this.workshopLocation = workshopLocation;
    }

    public List<WorkshopDtoWrapper> getWorkshops() {
        return workshops;
    }

    public void setWorkshops(List<WorkshopDtoWrapper> workshops) {
        this.workshops = workshops;
    }
}
