package app.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

/**
 * Created by Guest Lector on 17-Jul-17.
 */
@Entity
@Table(name = "production_batch")
public class ProductionBatch implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic
    private String name;

    @Column(name = "start_date")
    private Date date;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "batch_id")
    private Set<BasicShampoos> shampoos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<BasicShampoos> getShampoos() {
        return shampoos;
    }

    public void setShampoos(Set<BasicShampoos> shampoos) {
        this.shampoos = shampoos;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "ProductionBatch{" +
                "id=" + id +
                ", shampoos=" + shampoos +
                '}';
    }
}
