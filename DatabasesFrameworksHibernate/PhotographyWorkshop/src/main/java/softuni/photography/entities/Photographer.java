package softuni.photography.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Set;

@Entity
@Table(name = "photographers")
public class Photographer {

    private Long id;
    private String firstName;
    private String lastName;
    private String phone;
    private Camera primaryCamera;
    private Camera secondaryCamera;
    private Set<Lens> lenses;
    private Set<Accessory> accessories;
    private Set<Workshop> trainerWorkshops;
    private Set<Workshop> participantWorkshops;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "first_name")
    @NotNull
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "last_name")
    @NotNull
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Pattern(regexp = "^\\+\\d{1,3}\\/\\d{8,10}$")
    @Basic
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(referencedColumnName = "id", name = "primary_camera_id")
    @NotNull
    public Camera getPrimaryCamera() {
        return primaryCamera;
    }

    public void setPrimaryCamera(Camera primaryCamera) {
        this.primaryCamera = primaryCamera;
    }

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(referencedColumnName = "id", name = "secondary_camera_id")
    @NotNull
    public Camera getSecondaryCamera() {
        return secondaryCamera;
    }

    public void setSecondaryCamera(Camera secondaryCamera) {
        this.secondaryCamera = secondaryCamera;
    }

    @ManyToMany(mappedBy = "owners", fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    public Set<Lens> getLenses() {
        return lenses;
    }

    public void setLenses(Set<Lens> lenses) {
        this.lenses = lenses;
    }

    @OneToMany(mappedBy = "owner", fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    public Set<Accessory> getAccessories() {
        return accessories;
    }

    public void setAccessories(Set<Accessory> accessories) {
        this.accessories = accessories;
    }

    @OneToMany(mappedBy = "trainer")
    public Set<Workshop> getTrainerWorkshops() {
        return trainerWorkshops;
    }

    public void setTrainerWorkshops(Set<Workshop> trainerWorkshops) {
        this.trainerWorkshops = trainerWorkshops;
    }

    @ManyToMany(mappedBy = "participants")
    public Set<Workshop> getParticipantWorkshops() {
        return participantWorkshops;
    }

    public void setParticipantWorkshops(Set<Workshop> participantWorkshops) {
        this.participantWorkshops = participantWorkshops;
    }
}
