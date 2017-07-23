package enitities.motor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "cars")
@PrimaryKeyJoinColumn(name = "id")
public class Car extends MotorVehicleImpl {

    private byte numberOfDoors;

    private String insuranceInfo;

    public Car() {}

    public Car(String manufacturer, String model, double price, int maxSpeed, int numberOfEngines, String engineType, byte tankCapacity, byte numberOfDoors, String insuranceInfo) {
        super(manufacturer, model, price, maxSpeed, numberOfEngines, engineType, tankCapacity);
        this.numberOfDoors = numberOfDoors;
        this.insuranceInfo = insuranceInfo;
    }

    @Column(name = "number_of_doors")
    public byte getNumberOfDoors() {
        return numberOfDoors;
    }

    public void setNumberOfDoors(byte numberOfDoors) {
        this.numberOfDoors = numberOfDoors;
    }

    @Column(name = "insurance_info")
    public String getInsuranceInfo() {
        return insuranceInfo;
    }

    public void setInsuranceInfo(String insuranceInfo) {
        this.insuranceInfo = insuranceInfo;
    }
}
