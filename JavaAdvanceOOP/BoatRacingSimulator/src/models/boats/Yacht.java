package models.boats;

import Utility.Validator;
import contracts.BoatEngine;
import contracts.Race;

public class Yacht extends Boat {

    private BoatEngine engine;
    private int cargoWeight;

    public Yacht(String model, int weight, BoatEngine boatEngine, int cargoWeight) {
        super(model, weight, true);
        setEngine(boatEngine);
        setCargoWeight(cargoWeight);
    }

    private void setEngine(BoatEngine engine) {
        if (engine == null) {
            throw new IllegalArgumentException();
        }
        this.engine = engine;
    }

    private void setCargoWeight(int cargoWeight) {
        Validator.validatePropertyValue(cargoWeight, "Cargo Weight");
        this.cargoWeight = cargoWeight;
    }

    @Override
    public double getRaceTime(Race race) {
        return race.getDistance() / (this.engine.getOutput() - (super.getWeight() + this.cargoWeight) + (race.getOceanCurrentSpeed() / 2));
    }
}
