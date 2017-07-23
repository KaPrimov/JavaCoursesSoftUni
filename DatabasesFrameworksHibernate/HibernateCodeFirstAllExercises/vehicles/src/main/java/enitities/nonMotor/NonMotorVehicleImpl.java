package enitities.nonMotor;

import enitities.VehicleImpl;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "non_motor_vehicles")
@PrimaryKeyJoinColumn(name = "id")
public abstract class NonMotorVehicleImpl extends VehicleImpl implements apis.NonMotorVehicle {

    protected NonMotorVehicleImpl() {
    }

    protected NonMotorVehicleImpl(String manufacturer, String model, double price, int maxSpeed) {
        super(manufacturer, model, price, maxSpeed);
    }


}
