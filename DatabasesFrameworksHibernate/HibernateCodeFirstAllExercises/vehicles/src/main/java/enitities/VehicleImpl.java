package enitities;

import apis.Vehicle;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "vehicles")
public abstract class VehicleImpl implements Vehicle {


    private long id;
    private String manufacturer;
    private String model;
    private double price;
    private int maxSpeed;

    protected VehicleImpl() {} // DB constructor

    protected VehicleImpl(String manufacturer, String model, double price, int maxSpeed) {
        this.manufacturer = manufacturer;
        this.model = model;
        this.price = price;
        this.maxSpeed = maxSpeed;
    }

    @Override
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public long getId() {
        return this.id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    @Override
    @Column(name = "manufacturer")
    public String getManufacturer() {
        return manufacturer;
    }

    @Override
    @Column(name = "model")
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    @Override
    public String getModel() {
        return model;
    }

    @Override
    public void setModel(String model) {
        this.model = model;
    }

    @Override
    @Column(name = "price")
    public double getPrice() {
        return price;
    }

    @Override
    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    @Column(name = "max_speed")
    public int getMaxSpeed() {
        return maxSpeed;
    }

    @Override
    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }
}
