package weddingplanner.weddingplanner.dto.view.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "towns")
public class AgenciesByTownXmlWrapper {

    @XmlElement(name = "town")
    private List<TownXmlViewDto> townXmlViewDtos;

    public AgenciesByTownXmlWrapper() {
    }

    public List<TownXmlViewDto> getTownXmlViewDtos() {
        return townXmlViewDtos;
    }

    public void setTownXmlViewDtos(List<TownXmlViewDto> townXmlViewDtos) {
        this.townXmlViewDtos = townXmlViewDtos;
    }
}
