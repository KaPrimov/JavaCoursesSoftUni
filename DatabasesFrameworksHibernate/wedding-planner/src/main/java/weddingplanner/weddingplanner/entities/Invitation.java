package weddingplanner.weddingplanner.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "invitations")
public class Invitation {

    private Long id;
    private Wedding wedding;
    private Person guest;
    private Present present;
    private boolean isAttending;
    private String family;

    public Invitation() {
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
    @ManyToOne
    @JoinColumn(name = "wedding_id", referencedColumnName = "id")
    public Wedding getWedding() {
        return wedding;
    }

    public void setWedding(Wedding wedding) {
        this.wedding = wedding;
    }

    @NotNull
    @OneToOne
    @JoinColumn(name = "guest_id", referencedColumnName = "id")
    public Person getGuest() {
        return guest;
    }

    public void setGuest(Person guest) {
        this.guest = guest;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "present_id", referencedColumnName = "id")
    public Present getPresent() {
        return present;
    }

    public void setPresent(Present present) {
        this.present = present;
    }

    @Column(name = "is_attending")
    public boolean isAttending() {
        return isAttending;
    }

    public void setAttending(boolean attending) {
        this.isAttending = attending;
    }

    @NotNull
    @Basic
    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }
}
