package app.domain.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by User on 2.8.2017 г..
 */
@XmlRootElement(name = "addresses")
public class AddressesDto {


    private List<AddressDto> addressDtos;

    @XmlElement(name = "address")
    public List<AddressDto> getAddressDtos() {
        return addressDtos;
    }

    public void setAddressDtos(List<AddressDto> addressDtos) {
        this.addressDtos = addressDtos;
    }
}
