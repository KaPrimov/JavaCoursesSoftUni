package needForSpeedExam.models.races;

import needForSpeedExam.models.cars.Car;

import java.util.*;
import java.util.stream.Collectors;

public abstract class Race {
    private int length;
    private String route;
    private int prizePool;
    private List<Car> participants;
    private boolean isRaced;

    protected Race(int length, String route, int prizePool) {
        setLength(length);
        setRoute(route);
        setPrizePool(prizePool);
        this.participants = new ArrayList<>();
        this.isRaced = false;
    }

    private void setLength(int length) {
        this.length = length;
    }

    private void setRoute(String route) {
        this.route = route;
    }

    private void setPrizePool(int prizePool) {
        this.prizePool = prizePool;
    }

    public void addCarToRace(Car car) {
        if(car.canBeAdded()) {
            participants.add(car);
            car.setIsRacing(true);
        }
    }

    public String startRace() {
        this.isRaced = true;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("%s - %d\n", this.route, this.length));
        String name = this.getClass().getSimpleName();
        List<Car> rank = new LinkedList<>();
        if(participants.isEmpty()) {
            return "Cannot start the race with zero participants.\n";
        }
        switch (name) {
            case "CasualRace":
                rank = this.participants.stream()
                        .sorted((a, b) -> Long.compare(b.casualRaceCoeficient(), a.casualRaceCoeficient()))
                        .distinct()
                        .limit(3)
                        .collect(Collectors.toList());
                break;
            case "DragRace":
                rank = this.participants.stream()
                        .sorted((a, b) -> Long.compare(b.dragRaceCoeficient(), a.dragRaceCoeficient()))
                        .distinct()
                        .limit(3)
                        .collect(Collectors.toList());
                break;
            case "DriftRace":
                rank = this.participants.stream()
                        .sorted((a, b) -> Long.compare(b.driftRaceCoeficient(), a.driftRaceCoeficient()))
                        .distinct()
                        .limit(3)
                        .collect(Collectors.toList());
                break;
        }
        int count = 1;
        int moneyWon = 0;
        for (Car car : rank) {
            long pp = 0L;
            if(count == 1) {
                moneyWon = this.prizePool - (this.prizePool * 50) / 100;
            } else if (count == 2) {
                moneyWon = this.prizePool - (this.prizePool * 70) / 100;
            } else if (count == 3) {
                moneyWon = this.prizePool -  (this.prizePool * 80) / 100;
            }

            switch (name) {
                case "CasualRace":
                   pp = car.casualRaceCoeficient();
                    break;
                case "DragRace":
                   pp = car.dragRaceCoeficient();
                    break;
                case "DriftRace":
                   pp = car.driftRaceCoeficient();
                    break;
                    default:
                        break;
            }
            stringBuilder.append(String.format("%d. %s %s %dPP - $%d\n",count, car.getBrand(), car.getModel(),pp, moneyWon));
            count++;
        }
        return stringBuilder.toString();
    }

    public List<Car> getParticipants() {
        return Collections.unmodifiableList(this.participants);
    }

    public int getLength() {
        return length;
    }

    public String getRoute() {
        return route;
    }

    public int getPrizePool() {
        return prizePool;
    }
}
