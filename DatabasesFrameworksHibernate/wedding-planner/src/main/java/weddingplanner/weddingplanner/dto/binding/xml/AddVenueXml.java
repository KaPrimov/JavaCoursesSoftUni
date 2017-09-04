package weddingplanner.weddingplanner.dto.binding.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.math.BigInteger;

@XmlAccessorType(XmlAccessType.FIELD)
public class AddVenueXml {

    @XmlAttribute(name = "name")
    private String name;
    @XmlElement(name = "capacity")
    private BigInteger capacity;
    @XmlElement(name = "town")
    private String town;

    public AddVenueXml() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigInteger getCapacity() {
        return capacity;
    }

    public void setCapacity(BigInteger capacity) {
        this.capacity = capacity;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }
}
