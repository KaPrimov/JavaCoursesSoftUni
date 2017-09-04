package weddingplanner.weddingplanner.entities;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.List;

@Entity
@Table(name = "venues")
public class Venue {

    private Long id;
    private String name;
    private BigInteger capacity;
    private String town;
    private List<Wedding> weddings;

    public Venue() {

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

    @Basic
    public BigInteger getCapacity() {
        return capacity;
    }

    public void setCapacity(BigInteger capacity) {
        this.capacity = capacity;
    }

    @Basic
    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "venue_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "wedding_id", referencedColumnName = "id"))
    public List<Wedding> getWeddings() {
        return weddings;
    }

    public void setWeddings(List<Wedding> weddings) {
        this.weddings = weddings;
    }
}
