package weddingplanner.weddingplanner.dto.binding.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "venues")
public class AddVenuesXmlWrapper {

    @XmlElement(name = "venue")
    private List<AddVenueXml> addVenueXmls;

    public AddVenuesXmlWrapper() {
    }

    public List<AddVenueXml> getAddVenueXmls() {
        return addVenueXmls;
    }

    public void setAddVenueXmls(List<AddVenueXml> addVenueXmls) {
        this.addVenueXmls = addVenueXmls;
    }
}
