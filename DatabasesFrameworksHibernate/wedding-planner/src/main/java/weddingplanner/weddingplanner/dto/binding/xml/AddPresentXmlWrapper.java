package weddingplanner.weddingplanner.dto.binding.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "presents")
public class AddPresentXmlWrapper {

    @XmlElement(name = "present")
    private List<AddPresentDto> presentDtoList;

    public AddPresentXmlWrapper() {
    }

    public List<AddPresentDto> getPresentDtoList() {
        return presentDtoList;
    }

    public void setPresentDtoList(List<AddPresentDto> presentDtoList) {
        this.presentDtoList = presentDtoList;
    }
}
