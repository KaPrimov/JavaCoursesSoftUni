package softuni.course.products.dtos.view;

import com.google.gson.annotations.Expose;
import softuni.course.products.dtos.binding.CategoryDto;
import softuni.course.products.dtos.binding.UserDto;

import java.math.BigDecimal;
import java.util.Set;

public class ProductDto {
    @Expose
    private String name;
    @Expose
    private BigDecimal price;

    private UserDto seller;
    private UserDto buyer;
    private Set<CategoryDto> categories;

    public ProductDto(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
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
