package needForSpeedExam.models;

import needForSpeedExam.models.cars.Car;

import java.util.HashMap;
import java.util.Map;

public class Garage {
    private Map<Integer, Car> parkedCars;

    public Garage() {
        parkedCars = new HashMap<>();
    }

    public void modifyCars(int tuneIndex, String addOn) {
        if(!parkedCars.isEmpty()) {
            for (Car car : parkedCars.values()) {
                car.tuneCar(tuneIndex, addOn);
            }
        }
    }

    public void parkCar(int id, Car car) {
        if(!car.getIsRacing() && !parkedCars.containsKey(id)) {
            parkedCars.put(id, car);
            car.parkCar(true);
        }
    }

    public Car unparkCar(int id) {
        if (parkedCars.containsKey(id)) {
            parkedCars.get(id).parkCar(false);
        }
        return parkedCars.remove(id);
    }
}
