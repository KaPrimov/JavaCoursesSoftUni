package softuni.course.products.dtos.view;

import com.google.gson.annotations.Expose;

import java.util.List;

public class AllUsersWIthSoldProducts {
    @Expose
    private Integer count;
    @Expose
    private List<UserWithSoldProductsDto> users;

    public AllUsersWIthSoldProducts(Integer count, List<UserWithSoldProductsDto> users) {
        this.count = count;
        this.users = users;
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
