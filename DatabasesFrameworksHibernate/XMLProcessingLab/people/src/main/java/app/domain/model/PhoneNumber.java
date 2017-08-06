package app.domain.model;

import app.domain.validation.PhoneNumberListerner;
import app.domain.validation.Phone;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "phone_numbers")
@EntityListeners(PhoneNumberListerner.class)
public class PhoneNumber implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Phone
    @Column(name = "number")
    private String number;

//    @ManyToOne(cascade = CascadeType.PERSIST)
//    @JoinColumn(name = "person_id", referencedColumnName = "id")
//    private Person person;

    public PhoneNumber() {
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNumber() {
        return this.number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

//    public Person getPerson() {
//        return this.person;
//    }
//
//    public void setPerson(Person person) {
//        this.person = person;
//    }
}
