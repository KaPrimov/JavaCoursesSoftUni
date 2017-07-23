package entities;

import javax.persistence.*;

@Entity
@Table(name = "billing_details")
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // for single table
//@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING) // for single table
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS) //for table per class
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class BasicBillingDetail implements BillingDetail {

    private Long id;
    private String number;
    private User owner;

    public BasicBillingDetail() {}

    @Id
    //@GeneratedValue(strategy = GenerationType.TABLE) // for table per class
    @GeneratedValue(strategy = GenerationType.IDENTITY) // for single table and joined
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    @Override
    @Column(name = "number")
    public String getNumber() {
        return this.number;
    }

    @Override
    @ManyToOne
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    public User getOwner() {
        return this.owner;
    }
}
