package weddingplanner.weddingplanner.dto.view.xml;

import javax.xml.bind.annotation.*;
import java.math.BigInteger;

@XmlAccessorType(XmlAccessType.FIELD)
public class VenuesInSofiaDto {

    @XmlAttribute(name = "name")
    private String name;
    @XmlAttribute(name = "capacity")
    private BigInteger capacity;
    @XmlElement(name = "weddings-count")
    private Integer weddingsCount;

    public VenuesInSofiaDto() {
    }

    public VenuesInSofiaDto(String name, BigInteger capacity, Integer weddingsCount) {
        this.name = name;
        this.capacity = capacity;
        this.weddingsCount = weddingsCount;
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

    public Integer getWeddingsCount() {
        return weddingsCount;
    }

    public void setWeddingsCount(Integer weddingsCount) {
        this.weddingsCount = weddingsCount;
    }
}
