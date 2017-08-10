package softuni.photography.dto.bindingModels.add.xmlWrappers;

import softuni.photography.dto.bindingModels.add.AddAccessoryXmlDto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "accessories")
@XmlAccessorType(XmlAccessType.FIELD)
public class AccessoryXmlWrapper {

    @XmlElement(name = "accessory")
    private List<AddAccessoryXmlDto> addAccessoryXmlDtos;

    public AccessoryXmlWrapper() {
    }

    public List<AddAccessoryXmlDto> getAddAccessoryXmlDtos() {
        return addAccessoryXmlDtos;
    }

    public void setAddAccessoryXmlDtos(List<AddAccessoryXmlDto> addAccessoryXmlDtos) {
        this.addAccessoryXmlDtos = addAccessoryXmlDtos;
    }
}
