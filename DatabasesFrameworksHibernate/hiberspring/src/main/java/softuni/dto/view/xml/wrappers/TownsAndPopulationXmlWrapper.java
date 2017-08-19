package softuni.dto.view.xml.wrappers;

import softuni.dto.view.xml.elements.TownXmlViewDto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "towns")
public class TownsAndPopulationXmlWrapper {

    @XmlElement(name = "town")
    private List<TownXmlViewDto> towns;

    public TownsAndPopulationXmlWrapper() {
    }

    public List<TownXmlViewDto> getTowns() {
        return towns;
    }

    public void setTowns(List<TownXmlViewDto> towns) {
        this.towns = towns;
    }
}
