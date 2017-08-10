package softuni.photography.dto.viewModels;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlValue;
import java.math.BigDecimal;

@XmlAccessorType(XmlAccessType.FIELD)
public class LensExportXmlDto {
    @XmlTransient
    private String make;
    @XmlTransient
    private Integer focalLength;
    @XmlTransient
    private BigDecimal maxAperture;

    public LensExportXmlDto() {
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public Integer getFocalLength() {
        return focalLength;
    }

    public void setFocalLength(Integer focalLength) {
        this.focalLength = focalLength;
    }

    public BigDecimal getMaxAperture() {
        return maxAperture;
    }

    public void setMaxAperture(BigDecimal maxAperture) {
        this.maxAperture = maxAperture;
    }

    @XmlValue
    public String getDescription() {
        return String.format("%s %smm f%s", this.getMake(), this.getFocalLength(), this.getMaxAperture());
    }
}
