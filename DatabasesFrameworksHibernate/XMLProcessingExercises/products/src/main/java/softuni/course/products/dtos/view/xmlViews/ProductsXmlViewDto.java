package softuni.course.products.dtos.view.xmlViews;

import softuni.course.products.dtos.view.ProductViewDto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "products")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductsXmlViewDto {

    @XmlElement(name = "name")
    private List<ProductViewDto> productViewDtos;

    public ProductsXmlViewDto() {
    }

    public List<ProductViewDto> getProductViewDtos() {
        return productViewDtos;
    }

    public void setProductViewDtos(List<ProductViewDto> productViewDtos) {
        this.productViewDtos = productViewDtos;
    }
}
