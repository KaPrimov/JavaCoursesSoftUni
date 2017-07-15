package needForSpeedExam.models.races;

import needForSpeedExam.models.cars.Car;

public class TimeLimitRace extends Race {
    private int goldTime;
    private Car car;

    public TimeLimitRace(int length, String route, int prizePool, int goldTime) {
        super(length, route, prizePool);
        this.goldTime = goldTime;
    }

    private long returnTimePerformance() {
        return super.getLength() * ((car.getHorsepower() / 100) * car.getAcceleration());
    }

    @Override
    public String startRace() {
        if (car == null) {
            return "Cannot start the race with zero participants.\n";
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("%s - %s\n", super.getRoute(), super.getLength()));
        stringBuilder.append(String.format("%s %s - %s s.\n", car.getBrand(), car.getModel(), returnTimePerformance() ));
        stringBuilder.append(String.format("%s, $%d.\n", this.getClassification(), this.getMoney(this.getClassification())));

        return stringBuilder.toString();
    }

    private int getMoney(String classification) {
        switch (classification) {
            case "Gold Time":
                return super.getPrizePool();
            case "Silver Time":
                return super.getPrizePool() - (super.getPrizePool() * 50) / 100;
            case "Bronze Time":
                return super.getPrizePool()  - (super.getPrizePool() * 70) / 100;
        }
        return 0;
    }

    private String getClassification() {
        long tp = returnTimePerformance();
        if(tp <= goldTime) {
            return "Gold Time";
        } else if (tp <= goldTime + 15) {
            return "Silver Time";
        } else {
            return "Bronze Time";
        }
    }

    @Override
    public void addCarToRace(Car car) {
        if(super.getParticipants().isEmpty()) {
            super.addCarToRace(car);
            this.car = car;
        }
    }
}
