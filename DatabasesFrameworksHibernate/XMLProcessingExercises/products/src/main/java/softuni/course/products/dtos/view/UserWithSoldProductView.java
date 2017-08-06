package softuni.course.products.dtos.view;

import com.google.gson.annotations.Expose;

import java.util.List;

public class UserWithSoldProductView {

    @Expose
    private String firstName;
    @Expose
    private String lastName;
    @Expose
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
