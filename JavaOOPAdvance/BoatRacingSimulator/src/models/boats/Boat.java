package models.boats;

import Utility.Constants;
import Utility.Validator;
import contracts.Raceable;

public abstract class Boat implements Raceable {

    private String model;
    private int weight;
    private boolean isMotorBoat;

    protected Boat(String model, int weight, boolean isMotorBoat) {
        setModel(model);
        setWeight(weight);
        this.isMotorBoat = isMotorBoat;
    }

    @Override
    public boolean isMotorBoat() {
        return isMotorBoat;
    }

    @Override
    public String getModel() {
        return this.model;
    }

    protected int getWeight() {
        return weight;
    }

    private void setModel(String model) {
        Validator.validateModelLength(model, Constants.MIN_BOAT_MODEL_LENGTH);
        this.model = model;
    }

    private void setWeight(int weight) {
        Validator.validatePropertyValue(weight, "Weight");
        this.weight = weight;
    }
}
