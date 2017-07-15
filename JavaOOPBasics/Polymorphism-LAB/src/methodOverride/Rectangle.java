package methodOverride;

public class Rectangle {

    private double sideA;
    private double sideB;

    protected Rectangle(double sideA, double sideB) {
        this.sideA = sideA;
        this.sideB = sideB;
    }

    protected double getSideA() {
        return sideA;
    }

    protected Rectangle(double sideA) {
        this.sideA = sideA;
    }

    protected Double area() {
        return this.sideA * this.sideB;
    }
}
