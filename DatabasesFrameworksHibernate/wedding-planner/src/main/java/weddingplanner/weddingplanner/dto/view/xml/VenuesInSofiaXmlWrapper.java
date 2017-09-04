package weddingplanner.weddingplanner.dto.view.xml;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "venues")
public class VenuesInSofiaXmlWrapper {

    @XmlAttribute(name = "town")
    private String town = "Sofia";
    @XmlElement(name = "venue")
    private List<VenuesInSofiaDto> venuesDtos;

    public VenuesInSofiaXmlWrapper() {
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public List<VenuesInSofiaDto> getVenuesDtos() {
        return venuesDtos;
    }

    public void setVenuesDtos(List<VenuesInSofiaDto> venuesDtos) {
        this.venuesDtos = venuesDtos;
    }
}
