package enitities.railRoad.carriages;

import enitities.motor.Train;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "passenger_carriages")
public class Passenger extends CarriageImpl {

    private int standingPassengersCount;

    public Passenger() {
    }

    public Passenger(Train train, int standingPassengersCount) {
        super(train);
        this.standingPassengersCount = standingPassengersCount;
    }

    @Column(name = "standing_passengers_count")
    public int getStandingPassengersCount() {
        return standingPassengersCount;
    }

    public void setStandingPassengersCount(int standingPassengersCount) {
        this.standingPassengersCount = standingPassengersCount;
    }
}
