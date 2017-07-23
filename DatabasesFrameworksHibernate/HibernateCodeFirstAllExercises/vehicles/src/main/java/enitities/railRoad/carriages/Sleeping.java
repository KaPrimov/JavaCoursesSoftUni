package enitities.railRoad.carriages;

import enitities.motor.Train;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "sleeping_carriages")
public class Sleeping extends CarriageImpl {

    private int bedsCount;

    public Sleeping() {
    }

    public Sleeping(Train train, int bedsCount) {
        super(train);
        this.bedsCount = bedsCount;
    }

    @Column(name = "beds_count")
    public int getBedsCount() {
        return bedsCount;
    }

    public void setBedsCount(int bedsCount) {
        this.bedsCount = bedsCount;
    }
}
