package football.system.domain;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "continents")
public class Continent {
    private Long id;
    private String name;
    private Set<Country> countries;

    public Continent() {
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

    @ManyToMany
    @JoinTable(name = "countries_continents",
            inverseJoinColumns = @JoinColumn(name = "coutry_id"),
            joinColumns = @JoinColumn(name = "continent_id"))
    public Set<Country> getCountries() {
        return countries;
    }

    public void setCountries(Set<Country> countries) {
        this.countries = countries;
    }
}
