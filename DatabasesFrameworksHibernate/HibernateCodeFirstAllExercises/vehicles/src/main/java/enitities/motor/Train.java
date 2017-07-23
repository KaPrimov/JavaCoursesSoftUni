package enitities.motor;

import enitities.railRoad.Locomotive;
import enitities.railRoad.carriages.CarriageImpl;

import javax.persistence.*;
import java.util.List;

@Entity
@PrimaryKeyJoinColumn(name = "id")
@Table(name = "trains")
public class Train extends MotorVehicleImpl {

    private Locomotive locomotive;

    private byte numberOfCarriages;

    private List<CarriageImpl> carriages;

    public Train() {
    }

    public Train(String manufacturer, String model, double price, int maxSpeed, int numberOfEngines, String engineType, byte tankCapacity, Locomotive locomotive, byte numberOfCarriages, List<CarriageImpl> carriages) {
        super(manufacturer, model, price, maxSpeed, numberOfEngines, engineType, tankCapacity);
        this.locomotive = locomotive;
        this.numberOfCarriages = numberOfCarriages;
        this.carriages = carriages;
    }

    @OneToOne(mappedBy = "train", targetEntity = Locomotive.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public Locomotive getLocomotive() {
        return locomotive;
    }

    public void setLocomotive(Locomotive locomotive) {
        this.locomotive = locomotive;
    }

    @Column(name = "number_of_carriages")
    public byte getNumberOfCarriages() {
        return numberOfCarriages;
    }

    public void setNumberOfCarriages(byte numberOfCarriages) {
        this.numberOfCarriages = numberOfCarriages;
    }

    @OneToMany(mappedBy = "train", targetEntity = CarriageImpl.class,
            fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public List<CarriageImpl> getCarriages() {
        return carriages;
    }

    public void setCarriages(List<CarriageImpl> carriages) {
        this.carriages = carriages;
    }
}
