package needForSpeedExam.models.cars;

public class ShowCar extends Car {
    private int stars;

    public ShowCar(String brand, String model, int yearOfProduction, int horsepower, int acceleration, int suspension, int durability) {
        super(brand, model, yearOfProduction, horsepower, acceleration, suspension, durability);
        this.stars = 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append(String.format("%d *", this.stars));
        return sb.toString();
    }

    @Override
    public void tuneCar(int index, String addOn) {
        super.tuneCar(index, addOn);
        this.stars += index;
    }
}
