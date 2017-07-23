package football.system.domain;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "countries")
public class Country {
    private String id;
    private String name;
    private Set<Continent> continents;

    public Country() {
    }

    @Id
    @Column(length = 3)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToMany
    @JoinTable(name = "countries_continents",
    joinColumns = @JoinColumn(name = "coutry_id"),
    inverseJoinColumns = @JoinColumn(name = "continent_id"))
    public Set<Continent> getContinents() {
        return continents;
    }

    public void setContinents(Set<Continent> continents) {
        this.continents = continents;
    }
}
