package models.races;

import Utility.Constants;
import Utility.Validator;
import contracts.Race;
import contracts.Raceable;
import exeptions.DuplicateModelException;

import java.util.*;

public class RaceImpl implements Race {
    private int distance;
    private int windSpeed;
    private int oseanCurrentSpeed;
    private boolean allowsMotorBoats;
    private Map<String, Raceable> registeredBoats;

    public RaceImpl(int distance, int windSpeed, int oceanCurrentSpeed, boolean allowsMotorBoats) {
        this.setDistance(distance);
        this.windSpeed = windSpeed;
        this.oseanCurrentSpeed = oceanCurrentSpeed;
        this.allowsMotorBoats = allowsMotorBoats;
        this.registeredBoats = new LinkedHashMap<>();
    }

    @Override
    public int getDistance() {
        return distance;
    }

    @Override
    public int getWindSpeed() {
        return windSpeed;
    }

    @Override
    public int getOceanCurrentSpeed() {
        return oseanCurrentSpeed;
    }

    @Override
    public boolean getAllowsMotorboats() {
        return allowsMotorBoats;
    }

    @Override
    public void addParticipant(Raceable boat) throws DuplicateModelException {
        if (this.registeredBoats.containsKey(boat.getModel())) {
            throw new DuplicateModelException(Constants.DUPLICATE_MODEL_MESSAGE);
        }
        this.registeredBoats.put(boat.getModel(), boat);
    }

    @Override
    public List<Raceable> getParticipants() {
        return new ArrayList<>(this.registeredBoats.values());
    }
    private void setDistance(int distance) {
        Validator.validatePropertyValue(distance, "Distance");
        this.distance = distance;
    }
}