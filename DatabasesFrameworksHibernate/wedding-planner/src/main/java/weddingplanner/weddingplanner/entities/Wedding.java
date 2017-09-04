package weddingplanner.weddingplanner.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "weddings")
public class Wedding {

    private Long id;
    private Person bride;
    private Person bridegroom;
    private Date date;
    private Agency agency;
    private List<Venue> venues;

    public Wedding() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NotNull
    @OneToOne
    @JoinColumn(name = "bride_id", referencedColumnName = "id")
    public Person getBride() {
        return bride;
    }

    public void setBride(Person bride) {
        this.bride = bride;
    }

    @NotNull
    @OneToOne
    @JoinColumn(name = "bridegroom_id", referencedColumnName = "id")
    public Person getBridegroom() {
        return bridegroom;
    }

    public void setBridegroom(Person bridegroom) {
        this.bridegroom = bridegroom;
    }

    @NotNull
    @Column(name = "date", columnDefinition = "DATE")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @NotNull
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "agency_id", referencedColumnName = "id")
    public Agency getAgency() {
        return agency;
    }

    public void setAgency(Agency agency) {
        this.agency = agency;
    }

    @ManyToMany(mappedBy = "weddings")
    public List<Venue> getVenues() {
        return venues;
    }

    public void setVenues(List<Venue> venues) {
        this.venues = venues;
    }
}
