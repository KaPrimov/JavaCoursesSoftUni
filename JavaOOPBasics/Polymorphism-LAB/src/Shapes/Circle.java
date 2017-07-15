package Shapes;

public class Circle extends Shape {
    private Double radius;

    public Circle(Double raduis) {
        setRadius(raduis);
        this.calculateArea();
        this.calculatePerimeter();
    }

    private void setRadius(Double radius) {
        if(radius <= 0) {
            throw new IllegalArgumentException("Invalid argument!");
        }
        this.radius = radius;
    }

    private final Double getRadius() {
        return radius;
    }

    @Override
    protected void calculatePerimeter() {
        setPerimeter(2 * Math.PI * this.getRadius());
    }

    @Override
    protected void calculateArea() {
        setArea(Math.PI * Math.pow(this.getRadius(), 2));
    }
}
