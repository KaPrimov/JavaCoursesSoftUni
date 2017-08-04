package softuni.course.products.dtos.binding;

import com.google.gson.annotations.Expose;

public class UserDto {

    @Expose
    private Long id;

    public UserDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
