package enitities.railRoad.carriages;

import enitities.motor.Train;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "restaurant_carriages")
public class Restaurant extends CarriageImpl {

    private int tablesCount;

    public Restaurant() {
    }

    public Restaurant(Train train, int tablesCount) {
        super(train);
        this.tablesCount = tablesCount;
    }

    @Column(name = "tables_count")
    public int getTablesCount() {
        return tablesCount;
    }

    public void setTablesCount(int tablesCount) {
        this.tablesCount = tablesCount;
    }
}
