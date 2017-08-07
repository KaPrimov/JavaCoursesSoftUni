package softuni.course.products.dtos.binding.xmlDtos;

import softuni.course.products.dtos.binding.add.UserAddDto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserImportXmlDto {

    @XmlElement(name = "user")
    private List<UserAddDto> userAddDtos;

    public UserImportXmlDto() {
    }

    public List<UserAddDto> getUserAddDtos() {
        return userAddDtos;
    }

    public void setUserAddDtos(List<UserAddDto> userAddDtos) {
        this.userAddDtos = userAddDtos;
    }
}
