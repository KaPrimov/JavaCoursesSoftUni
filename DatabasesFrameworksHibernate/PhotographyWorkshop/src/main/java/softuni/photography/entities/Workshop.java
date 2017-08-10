package softuni.photography.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "workshops")
public class Workshop {

    private Long id;
    private String name;
    private Date startDate;
    private Date endDate;
    private String location;
    private BigDecimal price;
    private Photographer trainer;
    private List<Photographer> participants;

    public Workshop() {
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
    @NotNull
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "start_date", columnDefinition = "DATE")
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Column(name = "end_date", columnDefinition = "DATE")
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Basic
    @NotNull
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Basic
    @NotNull
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @ManyToOne(optional = false, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "trainer_id", referencedColumnName = "id")
    @NotNull
    public Photographer getTrainer() {
        return trainer;
    }

    public void setTrainer(Photographer trainer) {
        this.trainer = trainer;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "workshops_participants",
    joinColumns = @JoinColumn(name = "workshop_id" ,referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "participant_id", referencedColumnName = "id"))
    public List<Photographer> getParticipants() {
        return participants;
    }

    public void setParticipants(List<Photographer> participants) {
        this.participants = participants;
    }
}
