package enitities.nonMotor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "bikes")
@PrimaryKeyJoinColumn(name = "id")
public class Bike extends NonMotorVehicleImpl {

    private int shiftsCount;

    private String color;

    public Bike() {}

    public Bike(String manufacturer, String model, double price, int maxSpeed, int shiftsCount, String color) {
        super(manufacturer, model, price, maxSpeed);
        this.shiftsCount = shiftsCount;
        this.color = color;
    }

    @Column(name = "shifts_count")
    public int getShiftsCount() {
        return shiftsCount;
    }

    public void setShiftsCount(int shiftsCount) {
        this.shiftsCount = shiftsCount;
    }

    @Column(name = "color")
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
