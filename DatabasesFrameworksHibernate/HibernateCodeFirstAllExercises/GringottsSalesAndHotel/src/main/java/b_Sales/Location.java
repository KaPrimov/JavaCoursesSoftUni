package b_Sales;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "store_location")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "location_id")
    private long id;

    @Column(name = "location_name")
    private String locationName;

    @OneToMany(mappedBy = "storeLocation")
    private Set<Sale> salesInStore;

    public Location(String locationName) {
        this.locationName = locationName;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public Set<Sale> getSalesInStore() {
        return salesInStore;
    }

    public void setSalesInStore(Set<Sale> salesInStore) {
        this.salesInStore = salesInStore;
    }
}
