package d_Hospital;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "visitations")
public class Visitation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "visitation_id")
    private long id;

    @Column
    private LocalDate date;

    @Column(columnDefinition = "TEXT")
    private String comments;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id", referencedColumnName = "patient_id")
    private Patient patient;

    public Visitation() {
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
