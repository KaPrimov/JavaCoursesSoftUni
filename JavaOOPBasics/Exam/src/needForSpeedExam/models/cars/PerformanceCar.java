package needForSpeedExam.models.cars;

import java.util.LinkedList;
import java.util.List;

public class PerformanceCar extends Car {

    private List<String> addOns;

    public PerformanceCar(String brand, String model, int yearOfProduction, int horsepower, int acceleration, int suspension, int durability) {
        super(brand, model, yearOfProduction, horsepower, acceleration, suspension, durability);
        this.addOns = new LinkedList<>();
    }

    @Override
    protected void setHorsepower(int horsepower) {
        super.setHorsepower(horsepower + ((horsepower*50)/100));
    }

    @Override
    protected void setSuspension(int suspension) {
        super.setSuspension(suspension - ((suspension*25)/100));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        if(addOns.isEmpty()) {
            sb.append("Add-ons: None");
        } else {
            sb.append(String.format("Add-ons: %s", String.join(", ", this.addOns)));
        }
        return sb.toString();
    }

    @Override
    public void tuneCar(int index, String addOn) {
        super.tuneCar(index, addOn);
        this.addOns.add(addOn);
    }
}
