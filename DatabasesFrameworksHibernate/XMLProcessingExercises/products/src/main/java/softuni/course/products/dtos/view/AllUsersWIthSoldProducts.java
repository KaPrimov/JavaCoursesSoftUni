package softuni.course.products.dtos.view;

import com.google.gson.annotations.Expose;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class AllUsersWIthSoldProducts {
    @Expose
    @XmlAttribute(name = "count")
    private Integer count;
    @Expose
    @XmlElement(name = "user")
    private List<UserWithSoldProductsDto> users;

    public AllUsersWIthSoldProducts(Integer count, List<UserWithSoldProductsDto> users) {
        this.count = count;
        this.users = users;
    }

    public AllUsersWIthSoldProducts() {
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<UserWithSoldProductsDto> getUsers() {
        return users;
    }

    public void setUsers(List<UserWithSoldProductsDto> users) {
        this.users = users;
    }
}
