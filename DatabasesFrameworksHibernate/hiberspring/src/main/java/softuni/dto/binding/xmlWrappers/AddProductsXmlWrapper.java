package softuni.dto.binding.xmlWrappers;

import softuni.dto.binding.AddProductXmlDto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "products")
public class AddProductsXmlWrapper {

    @XmlElement(name = "product")
    private List<AddProductXmlDto> productXmlDtos;

    public AddProductsXmlWrapper() {
    }

    public List<AddProductXmlDto> getProductXmlDtos() {
        return productXmlDtos;
    }

    public void setProductXmlDtos(List<AddProductXmlDto> productXmlDtos) {
        this.productXmlDtos = productXmlDtos;
    }
}
