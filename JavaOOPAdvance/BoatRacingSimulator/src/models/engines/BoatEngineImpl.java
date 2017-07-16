package models.engines;

import Utility.Constants;
import Utility.Validator;
import contracts.BoatEngine;
import contracts.Modelable;

public abstract class BoatEngineImpl implements BoatEngine, Modelable {

    private int cachedOutput;

    private String model;

    private int horsepower;

    private int displacement;

    protected BoatEngineImpl(String model, int horsepower, int displacement) {
        this.setModel(model);
        this.setHorsepower(horsepower);
        this.setDisplacement(displacement);
    }

    private void setHorsepower(int horsepower) {
        Validator.validatePropertyValue(horsepower, "Horsepower");
        this.horsepower = horsepower;
    }

    private void setModel(String model) {
        Validator.validateModelLength(model, Constants.MIN_BOAT_ENGINE_MODEL_LENGTH);
        this.model = model;
    }

    private void setDisplacement(int displacement) {
        Validator.validatePropertyValue(displacement, "Displacement");
        this.displacement = displacement;
    }

    @Override
    public String getModel() {
        return model;
    }

    protected final int getHorsepower() {
        return horsepower;
    }

    protected final int getDisplacement() {
        return displacement;
    }
}
