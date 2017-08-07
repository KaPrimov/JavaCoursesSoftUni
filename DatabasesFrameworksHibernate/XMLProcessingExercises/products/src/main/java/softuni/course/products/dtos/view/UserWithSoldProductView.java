package softuni.course.products.dtos.view;

import com.google.gson.annotations.Expose;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserWithSoldProductView {

    @Expose
    @XmlAttribute(name = "first-name")
    private String firstName;
    @Expose
    @XmlAttribute(name = "last-name")
    private String lastName;
    @Expose
    @XmlElementWrapper(name = "sold-products")
    private List<SoldProductView> soldProducts;

    public UserWithSoldProductView() {
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

    public List<SoldProductView> getSoldProducts() {
        return soldProducts;
    }

    public void setSoldProducts(List<SoldProductView> soldProducts) {
        this.soldProducts = soldProducts;
    }
}
