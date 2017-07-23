package enitities.railRoad;

import enitities.motor.Train;

import javax.persistence.*;

@Entity
@Table(name = "locomotives")
public class Locomotive {


    private long id;

    private String model;

    private short power;

    private Train train;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "model")
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Column(name = "power")
    public short getPower() {
        return power;
    }

    public void setPower(short power) {
        this.power = power;
    }

    @OneToOne(optional = false)
    @JoinColumn(name = "train_id", referencedColumnName = "id")
    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }
}
