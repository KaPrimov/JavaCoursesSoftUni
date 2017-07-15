package google;

public class Car {
    private String model;
    private int carSpreed;

    public Car(String model, int carSpreed) {
        this.model = model;
        this.carSpreed = carSpreed;
    }

    @Override
    public String toString() {
        return String.format("%s %d", this.model, this.carSpreed);
    }
}
