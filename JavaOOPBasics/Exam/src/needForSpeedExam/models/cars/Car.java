package needForSpeedExam.models.cars;

public abstract class Car {
    private String brand;
    private String model;
    private int yearOfProduction;
    private long horsepower;
    private int acceleration;
    private long suspension;
    private long durability;
    private boolean isParked;
    private boolean isRacing;

    protected Car(String brand, String model, int yearOfProduction, int horsepower, int acceleration, int suspension, int durability) {
        setBrand(brand);
        setModel(model);
        setYearOfProduction(yearOfProduction);
        setHorsepower(horsepower);
        setAcceleration(acceleration);
        setSuspension(suspension);
        setDurability(durability);
        this.isParked = false;
        this.isRacing = false;
    }


    private void setBrand(String brand) {
        this.brand = brand;
    }

    private void setModel(String model) {
        this.model = model;
    }

    private void setYearOfProduction(int yearOfProduction) {
        this.yearOfProduction = yearOfProduction;
    }

    private void setAcceleration(int acceleration) {
        this.acceleration = acceleration;
    }

    private void setDurability(int durability) {
        this.durability = durability;
    }

    protected void setHorsepower(int horsepower) {
        this.horsepower = horsepower;
    }

    protected void setSuspension(int suspension) {
        this.suspension = suspension;
    }

    public boolean canBeAdded() {
        return !isParked;
    }

    public void parkCar(boolean isParked) {
        this.isParked = isParked;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public long casualRaceCoeficient() {
        return (this.horsepower / this.acceleration) + (this.suspension + this.durability);
    }

    public long dragRaceCoeficient() {
        return this.horsepower / this.acceleration;
    }

    public long driftRaceCoeficient() {
        return this.suspension + this.durability;
    }

    public void tuneCar(int index, String addOn) {
        this.suspension += index - ((index * 50) / 100);
        this.horsepower += index;
    }

    public long getHorsepower() {
        return horsepower;
    }

    public void decreaseDurability(long decrease) {
        this.durability -= decrease;
    }

    public int getAcceleration() {
        return acceleration;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%s %s %d", this.brand, this.model, this.yearOfProduction)).append(System.lineSeparator());
        sb.append(String.format("%d HP, 100 m/h in %d s", this.horsepower, this.acceleration)).append(System.lineSeparator());
        sb.append(String.format("%d Suspension force, %d Durability", this.suspension, this.durability)).append(System.lineSeparator());

        return sb.toString();
    }

    public void setIsRacing(boolean isRacing) {
        this.isRacing = isRacing;
    }

    public boolean getIsRacing() {
        return isRacing;
    }
}
