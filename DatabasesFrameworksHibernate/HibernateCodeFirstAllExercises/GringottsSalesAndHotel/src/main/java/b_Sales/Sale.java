package b_Sales;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "sale")
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sale_id")
    private int id;

    @ManyToOne(optional = false, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "product", referencedColumnName = "product_id")
    private Product product;

    @ManyToOne(optional = true, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "customer", referencedColumnName = "customer_id")
    private Customer customer;

    @ManyToOne(optional = false, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "storeLocation", referencedColumnName = "location_id")
    private Location storeLocation;

    public Sale(Product product, Customer customer, Location storeLocation, LocalDateTime date) {
        this.product = product;
        this.customer = customer;
        this.storeLocation = storeLocation;
        this.date = date;
    }

    private LocalDateTime date;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Location getStoreLocation() {
        return storeLocation;
    }

    public void setStoreLocation(Location storeLocation) {
        this.storeLocation = storeLocation;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
