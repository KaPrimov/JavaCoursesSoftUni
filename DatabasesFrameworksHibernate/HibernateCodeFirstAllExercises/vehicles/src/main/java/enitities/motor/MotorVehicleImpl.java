package enitities.motor;

import apis.MotorVehicle;
import enitities.VehicleImpl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "motor_vehicles")
@PrimaryKeyJoinColumn(name = "id")
public abstract class MotorVehicleImpl extends VehicleImpl implements MotorVehicle {

    private int numberOfEngines;
    private String engineType;
    private byte tankCapacity;

    protected MotorVehicleImpl() {} // DB constructor

    protected MotorVehicleImpl(String manufacturer, String model, double price, int maxSpeed, int numberOfEngines, String engineType, byte tankCapacity) {
        super(manufacturer, model, price, maxSpeed);
        this.numberOfEngines = numberOfEngines;
        this.engineType = engineType;
        this.tankCapacity = tankCapacity;
    }

    @Override
    @Column(name = "number_of_engines")
    public int getNumberOfEngines() {
        return numberOfEngines;
    }

    @Override
    public void setNumberOfEngines(int numberOfEngines) {
        this.numberOfEngines = numberOfEngines;
    }

    @Override
    @Column(name = "engine_type")
    public String getEngineType() {
        return engineType;
    }

    @Override
    public void setEngineType(String engineType) {
        this.engineType = engineType;
    }

    @Override
    @Column(name = "tank_capacity")
    public byte getTankCapacity() {
        return tankCapacity;
    }

    @Override
    public void setTankCapacity(byte tankCapacity) {
        this.tankCapacity = tankCapacity;
    }


}
