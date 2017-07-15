package models.boats;

import contracts.BoatEngine;
import contracts.Race;

public class PowerBoat extends Boat {

    private BoatEngine firstEngine;
    private BoatEngine secondEngine;

    public PowerBoat(String model, int weight, BoatEngine firstEngine, BoatEngine secondEngine) {
        super(model, weight, true);
        this.setFirstEngine(firstEngine);
        this.setSecondEngine(secondEngine);
    }

    private void setFirstEngine(BoatEngine firstEngine) {
        if (firstEngine == null) {
            throw new IllegalArgumentException();
        }
        this.firstEngine = firstEngine;
    }

    private void setSecondEngine(BoatEngine secondEngine) {
        if (secondEngine == null) {
            throw new IllegalArgumentException();
        }
        this.secondEngine = secondEngine;
    }

    @Override
    public double getRaceTime(Race race) {
        return race.getDistance() / ((firstEngine.getOutput() + secondEngine.getOutput()) - super.getWeight() + (race.getOceanCurrentSpeed() / 5.0));
    }
}

