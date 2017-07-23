package d_Hospital;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Entity
@Table(name = "patients")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "patient_id")
    private long id;

    @Column(name = "first_name", length = 50)
    private String firstName;

    @Column(name = "last_name", length = 50, nullable = false)
    private String lastName;

    @Column(name = "address", length = 100)
    private String address;

    @Column(length = 100)
    private String email;

    @Column(name = "date_of_birth", columnDefinition = "DATE", nullable = false)
    private LocalDate dateOFBirth;

    @Column(columnDefinition = "BLOB")
    private byte[] picture;

    @Column(name = "is_insured")
    private boolean isInsured;

    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL)
    private Set<Visitation> visitations;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "patients_diagnoses",
    joinColumns = @JoinColumn(name = "patient_id"),
    inverseJoinColumns = @JoinColumn(name = "diagnose_id"))
    private Set<Diagnose> diagnoses;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "patients_medicaments",
    joinColumns = @JoinColumn(name = "patient_id"),
    inverseJoinColumns = @JoinColumn(name = "medicament_id"))
    private Set<Medicament> medicaments;

    public Patient() {}

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email == null){
            throw new IllegalArgumentException("Email cannot be null");
        }
        Pattern pattern = Pattern.compile("^[A-Za-z0-9][A-Za-z0-9+_.-]+[A-Za-z0-9]@([A-Za-z0-9+_.-]+)\\.([A-Za-z0-9+]+)$");
        Matcher matcher = pattern.matcher(email);
        if (!matcher.find()){
            throw new IllegalArgumentException("Invalid email");
        }
        this.email = email;
    }

    public LocalDate getDateOFBirth() {
        return dateOFBirth;
    }

    public void setDateOFBirth(LocalDate dateOFBirth) {
        this.dateOFBirth = dateOFBirth;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    public boolean isInsured() {
        return isInsured;
    }

    public void setInsured(boolean insured) {
        isInsured = insured;
    }

    public Set<Visitation> getVisitations() {
        return visitations;
    }

    public void setVisitations(Set<Visitation> visitations) {
        this.visitations = visitations;
    }

    public Set<Diagnose> getDiagnoses() {
        return diagnoses;
    }

    public void setDiagnoses(Set<Diagnose> diagnoses) {
        this.diagnoses = diagnoses;
    }

    public Set<Medicament> getMedicaments() {
        return medicaments;
    }

    public void setMedicaments(Set<Medicament> medicaments) {
        this.medicaments = medicaments;
    }
}
