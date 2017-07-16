package models.boats;

import Utility.Validator;
import contracts.Race;

public class RowBoat extends Boat {

    private int oars;

    public RowBoat(String model, int weight, int oars) {
        super(model, weight, false);
        this.setOars(oars);
    }

    private void setOars(int oars) {
        Validator.validatePropertyValue(oars, "Oars");
        this.oars = oars;
    }

    @Override
    public double getRaceTime(Race race) {

        return race.getDistance() / ((this.oars * 100.0) - super.getWeight() + race.getOceanCurrentSpeed());
    }
}
