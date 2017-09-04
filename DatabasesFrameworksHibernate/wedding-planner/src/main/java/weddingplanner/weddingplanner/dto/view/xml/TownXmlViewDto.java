package weddingplanner.weddingplanner.dto.view.xml;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class TownXmlViewDto {

    @XmlAttribute(name = "name")
    private String name;
    @XmlElementWrapper(name = "agencies")
    @XmlElement(name = "agency")
    private List<AgencyXmlViewDto> agencyXmlViewDtos;

    public TownXmlViewDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<AgencyXmlViewDto> getAgencyXmlViewDtos() {
        return agencyXmlViewDtos;
    }

    public void setAgencyXmlViewDtos(List<AgencyXmlViewDto> agencyXmlViewDtos) {
        this.agencyXmlViewDtos = agencyXmlViewDtos;
    }
}
