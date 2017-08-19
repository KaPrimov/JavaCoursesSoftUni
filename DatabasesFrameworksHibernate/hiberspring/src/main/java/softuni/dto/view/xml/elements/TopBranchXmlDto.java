package softuni.dto.view.xml.elements;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@XmlAccessorType(XmlAccessType.FIELD)
public class TopBranchXmlDto {

    @XmlAttribute(name = "name")
    private String name;
    @XmlAttribute(name = "town")
    private String townName;
    @XmlAttribute(name = "total_clients")
    private Integer totalClients;

    public TopBranchXmlDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTownName() {
        return townName;
    }

    public void setTownName(String townName) {
        this.townName = townName;
    }

    public Integer getTotalClients() {
        return totalClients;
    }

    public void setTotalClients(Integer totalClients) {
        this.totalClients = totalClients;
    }
}
