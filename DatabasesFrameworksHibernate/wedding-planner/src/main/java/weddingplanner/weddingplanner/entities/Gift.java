package weddingplanner.weddingplanner.entities;

import weddingplanner.weddingplanner.entities.enums.Size;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@DiscriminatorValue("Gift")
public class Gift extends Present {

    private String name;
    private Size size;

    public Gift() {
    }

    @NotNull
    @Basic
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Enumerated(EnumType.STRING)
    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }
}
