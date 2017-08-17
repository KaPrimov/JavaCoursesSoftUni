package softuni.photography.dto.viewModels;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlValue;

@XmlAccessorType(XmlAccessType.FIELD)
public class PhotographerParticipantDto {

    @XmlTransient
    private String firstName;
    @XmlTransient
    private String lastName;

    public PhotographerParticipantDto() {

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

    @XmlValue
    public String getFullName() {
        return this.getFirstName() +  " " + this.getLastName();
    }
}
