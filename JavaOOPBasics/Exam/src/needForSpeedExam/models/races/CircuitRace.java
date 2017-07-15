package needForSpeedExam.models.races;

import needForSpeedExam.models.cars.Car;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class CircuitRace extends Race {
    private int laps;

    public CircuitRace(int length, String route, int prizePool, int laps) {
        super(length, route, prizePool);
        this.laps = laps;
    }

    @Override
    public String startRace() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("%s - %d\n", this.getRoute(), this.getLength() * this.laps));

        List<Car> rank = new LinkedList<>();
        if(super.getParticipants().isEmpty()) {
            return "Cannot start the race with zero participants.\n";
        }

        rank = this.getParticipants().stream()
            .sorted((a, b) -> Long.compare(b.casualRaceCoeficient(), a.casualRaceCoeficient()))
            .limit(4).collect(Collectors.toList());

            for (Car car : super.getParticipants()) {
                for (int i = 0; i < this.laps; i++) {
                    car.decreaseDurability(this.getLength() * this.getLength());
                }
            }

        int count = 1;
        int moneyWon = 0;
        for (Car car : rank) {
            long pp = 0L;
            if(count == 1) {
                moneyWon = super.getPrizePool() - (super.getPrizePool() * 60) / 100;
            } else if (count == 2) {
                moneyWon = super.getPrizePool() -(super.getPrizePool() * 70) / 100;
            } else if (count == 3) {
                moneyWon = super.getPrizePool() -(super.getPrizePool() * 80) / 100;
            } else if(count == 4) {
                moneyWon = super.getPrizePool() -(super.getPrizePool() * 90) / 100;
            }
            pp = car.casualRaceCoeficient();

            stringBuilder.append(String.format("%d. %s %s %dPP - $%d\n",count, car.getBrand(), car.getModel(),pp, moneyWon));
            count++;
        }

        return stringBuilder.toString();
    }
}
