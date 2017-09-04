package weddingplanner.weddingplanner.entities;

import javax.persistence.*;

@Entity
@Table(name = "presents")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "present_type", discriminatorType = DiscriminatorType.STRING)
public abstract class Present {

    private Long id;
    private Person Owner;

    protected Present() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @OneToOne()
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    public Person getOwner() {
        return Owner;
    }

    public void setOwner(Person owner) {
        Owner = owner;
    }
}
