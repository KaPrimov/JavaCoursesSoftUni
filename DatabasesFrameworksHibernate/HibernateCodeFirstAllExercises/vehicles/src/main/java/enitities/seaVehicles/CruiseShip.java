package enitities.seaVehicles;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "cruise_ships")
@PrimaryKeyJoinColumn(name = "id")
public class CruiseShip extends Ship {

    private int passengersCapacity;

    public CruiseShip() {
    }

    public CruiseShip(String nationality, String captainName, byte crewSize, int passengersCapacity) {
        super(nationality, captainName, crewSize);
        this.passengersCapacity = passengersCapacity;
    }

    @Column(name = "pasengers_capacity")
    public int getPassengersCapacity() {
        return passengersCapacity;
    }

    public void setPassengersCapacity(int passengersCapacity) {
        this.passengersCapacity = passengersCapacity;
    }
}
