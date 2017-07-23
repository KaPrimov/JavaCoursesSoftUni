package enitities.railRoad.carriages;

import enitities.motor.Train;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "carriages")
public abstract class CarriageImpl {

    private long id;
    private Train train;

    public CarriageImpl() {
    }

    public CarriageImpl(Train train) {
        this.train = train;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @ManyToOne(optional = false)
    @JoinColumn(name = "train_id", referencedColumnName = "id")
    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }
}
