package softuni.course.products.dtos.binding.add;

import com.google.gson.annotations.Expose;
import softuni.course.products.dtos.binding.CategoryDto;
import softuni.course.products.dtos.binding.UserDto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;
import java.util.Set;
@XmlRootElement(name = "category")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductAddDto {

    @Expose
    @XmlElement(name = "name")
    private String name;
    @Expose
    @XmlElement(name = "price")
    private BigDecimal price;
    private UserDto seller;
    private UserDto buyer;
    private Set<CategoryDto> categories;

    public ProductAddDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public UserDto getSeller() {
        return seller;
    }

    public void setSeller(UserDto seller) {
        this.seller = seller;
    }

    public UserDto getBuyer() {
        return buyer;
    }

    public void setBuyer(UserDto buyer) {
        this.buyer = buyer;
    }

    public Set<CategoryDto> getCategories() {
        return categories;
    }

    public void setCategories(Set<CategoryDto> categories) {
        this.categories = categories;
    }
}
