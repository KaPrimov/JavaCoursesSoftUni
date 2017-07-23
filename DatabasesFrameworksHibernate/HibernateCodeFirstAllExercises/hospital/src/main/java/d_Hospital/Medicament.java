package d_Hospital;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "medicaments")
public class Medicament {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "medicament_id")
    private long id;

    @Column(length = 75, nullable = false)
    private String name;

    @ManyToMany(mappedBy = "medicaments", targetEntity = Patient.class)
    private Set<Patient> patients;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Patient> getPatients() {
        return patients;
    }

    public void setPatients(Set<Patient> patients) {
        this.patients = patients;
    }
}
