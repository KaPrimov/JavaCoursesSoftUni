package enitities.motor;

import enitities.motor.MotorVehicleImpl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "planes")
@PrimaryKeyJoinColumn(name = "id")
public class Plane extends MotorVehicleImpl {

    private String owner;

    private String color;

    private int capacity;

    public Plane() {
    }

    public Plane(String manufacturer, String model, double price, int maxSpeed, int numberOfEngines, String engineType, byte tankCapacity, String owner, String color, int capacity) {
        super(manufacturer, model, price, maxSpeed, numberOfEngines, engineType, tankCapacity);
        this.owner = owner;
        this.color = color;
        this.capacity = capacity;
    }

    @Column(name = "owner")
    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    @Column(name = "color")
    public String getColor() {
        return color;
    }

    @Column(name = "capacity")
    public void setColor(String color) {
        this.color = color;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
