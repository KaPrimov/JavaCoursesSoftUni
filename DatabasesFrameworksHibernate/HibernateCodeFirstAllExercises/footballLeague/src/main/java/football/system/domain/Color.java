package football.system.domain;

import javax.persistence.*;

@Entity(name = "colors")
public class Color {
    private Long id;
    private String name;

    public Color() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
