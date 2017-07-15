package needForSpeedExam;

import needForSpeedExam.models.Garage;
import needForSpeedExam.models.cars.Car;
import needForSpeedExam.models.cars.PerformanceCar;
import needForSpeedExam.models.cars.ShowCar;
import needForSpeedExam.models.races.*;

import java.util.LinkedHashMap;
import java.util.Map;

public class CarManager {

    private Map<Integer, Car> cars;
    private Map<Integer, Race> races;
    private Garage garage;

    public CarManager() {
        cars = new LinkedHashMap<>();
        races = new LinkedHashMap<>();
        garage = new Garage();
    }

    public void register(int id, String type, String brand, String model, int
            yearOfProduction, int horsepower, int acceleration, int suspension, int
                          durability){
        Car car = null;
        switch (type) {
            case "Performance":
                car = new PerformanceCar(brand, model, yearOfProduction, horsepower, acceleration, suspension, durability);
                break;
                case "Show":
                car = new ShowCar(brand, model, yearOfProduction, horsepower, acceleration, suspension, durability);
                break;
                default:
                    break;
        }
        if(!cars.containsKey(id)) {
            cars.put(id, car);
        }

    }
    public String check(int id){
        return cars.get(id).toString();
    }
    public void open(int id, String type, int length, String route, int prizePool) {

        Race race = null;

        switch (type) {
            case "Casual":
                race = new CasualRace(length, route, prizePool);
               break;
            case "Drag":
                race = new DragRace(length, route, prizePool);
                break;
            case "Drift":
                race = new DriftRace(length, route, prizePool);
                break;
                default:
                    break;
        }
        if(!races.containsKey(id)) {
            races.put(id, race);
        }

    }

    public void open(int id, String type, int length, String route, int prizePool, int specialArg) {

        Race race = null;

        switch (type) {
            case "Circuit":
                race = new CircuitRace(length, route, prizePool, specialArg);
                break;
            case "TimeLimit":
                race = new TimeLimitRace(length, route, prizePool, specialArg);
                default:
                    break;
        }

        if(!races.containsKey(id)) {
            races.put(id, race);
        }
    }
    public void participate(int carId, int raceId){
        if(cars.containsKey(carId) && races.containsKey(raceId)) {
            races.get(raceId).addCarToRace(cars.get(carId));
        }
    }
    public String start(int id){
            return races.get(id).startRace();
    }
    public void park(int id){
        if(!cars.get(id).getIsRacing() && cars.containsKey(id)) {
            garage.parkCar(id, cars.get(id));
        }
    }
    public void unpark(int id){
        if(cars.containsKey(id)) {
            garage.unparkCar(id);
        }
    }
    public void tune(int tuneIndex, String addOn){
        garage.modifyCars(tuneIndex, addOn);
    }
}
