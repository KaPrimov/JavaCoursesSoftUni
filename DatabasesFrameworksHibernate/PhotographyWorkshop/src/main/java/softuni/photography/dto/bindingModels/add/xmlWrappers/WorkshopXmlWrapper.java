package softuni.photography.dto.bindingModels.add.xmlWrappers;

import softuni.photography.dto.bindingModels.add.AddWorkshopXmlDto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "workshops")
@XmlAccessorType(XmlAccessType.FIELD)
public class WorkshopXmlWrapper {

    @XmlElement(name = "workshop")
    private List<AddWorkshopXmlDto> addWorkshopXmlDtos;

    public WorkshopXmlWrapper() {
    }

    public List<AddWorkshopXmlDto> getAddWorkshopXmlDtos() {
        return addWorkshopXmlDtos;
    }

    public void setAddWorkshopXmlDtos(List<AddWorkshopXmlDto> addWorkshopXmlDtos) {
        this.addWorkshopXmlDtos = addWorkshopXmlDtos;
    }
}
