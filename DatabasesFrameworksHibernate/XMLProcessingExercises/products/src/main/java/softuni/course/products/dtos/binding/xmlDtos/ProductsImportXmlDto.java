package softuni.course.products.dtos.binding.xmlDtos;

import softuni.course.products.dtos.binding.add.ProductAddDto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "products")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductsImportXmlDto {

    @XmlElement(name = "product")
    private List<ProductAddDto> productAddDtos;

    public ProductsImportXmlDto() {
    }

    public List<ProductAddDto> getProductAddDtos() {
        return productAddDtos;
    }

    public void setProductAddDtos(List<ProductAddDto> productAddDtos) {
        this.productAddDtos = productAddDtos;
    }
}
