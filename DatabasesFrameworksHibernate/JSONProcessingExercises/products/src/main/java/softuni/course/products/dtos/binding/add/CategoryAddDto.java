package softuni.course.products.dtos.binding.add;

import com.google.gson.annotations.Expose;

public class CategoryAddDto {
    @Expose
    private String name;

    public CategoryAddDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
