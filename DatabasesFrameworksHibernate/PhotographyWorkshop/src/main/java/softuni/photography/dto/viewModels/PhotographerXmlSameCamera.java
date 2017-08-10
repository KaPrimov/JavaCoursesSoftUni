package softuni.photography.dto.viewModels;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)

public class PhotographerXmlSameCamera {
    @XmlTransient
    private String firstName;
    @XmlTransient
    private String lastName;
    @XmlTransient
    private String primaryCameraMake;
    @XmlTransient
    private String primaryCameraModel;
    @XmlElementWrapper(name = "lenses")
    @XmlElement(name = "lens")
    private List<LensExportXmlDto> lenses;

    public PhotographerXmlSameCamera() {
    }
    @XmlAttribute(name = "primary-camera")
    public String getCameraInfo() {
        return this.getPrimaryCameraMake() + " " + this.getPrimaryCameraModel();
    }


    @XmlAttribute(name = "name")
    public String getFullName() {
        return this.getFirstName() + " " + this.getLastName();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPrimaryCameraMake() {
        return primaryCameraMake;
    }

    public void setPrimaryCameraMake(String primaryCameraMake) {
        this.primaryCameraMake = primaryCameraMake;
    }

    public String getPrimaryCameraModel() {
        return primaryCameraModel;
    }

    public void setPrimaryCameraModel(String primaryCameraModel) {
        this.primaryCameraModel = primaryCameraModel;
    }

    public List<LensExportXmlDto> getLenses() {
        return lenses;
    }

    public void setLenses(List<LensExportXmlDto> lenses) {
        this.lenses = lenses;
    }

}
