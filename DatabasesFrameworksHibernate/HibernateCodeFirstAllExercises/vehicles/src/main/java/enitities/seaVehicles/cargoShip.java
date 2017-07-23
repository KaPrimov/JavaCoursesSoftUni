package enitities.seaVehicles;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "cargo_ships")
@PrimaryKeyJoinColumn(name = "id")
public class cargoShip extends Ship {

    private long maxLoad;

    public cargoShip() {
    }

    public cargoShip(String nationality, String captainName, byte crewSize, long maxLoad) {
        super(nationality, captainName, crewSize);
        this.maxLoad = maxLoad;
    }

    @Column(name = "max_load")
    public long getMaxLoad() {
        return maxLoad;
    }

    public void setMaxLoad(long maxLoad) {
        this.maxLoad = maxLoad;
    }
}
