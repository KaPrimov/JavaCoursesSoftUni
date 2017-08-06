package app.domain.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by User on 2.8.2017 г..
 */
@XmlRootElement(name = "persons")
public class PersonsDto {


    private List<PersonDto> personDtos;

    @XmlElement(name = "person")
    public List<PersonDto> getPersonDtos() {
        return personDtos;
    }

    public void setPersonDtos(List<PersonDto> personDtos) {
        this.personDtos = personDtos;
    }
}
