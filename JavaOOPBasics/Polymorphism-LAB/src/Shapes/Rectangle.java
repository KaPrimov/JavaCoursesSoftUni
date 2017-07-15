package Shapes;

public class Rectangle extends Shape {

    private double height;
    private double width;

    public Rectangle(double height, double width) {
        setHeight(height);
        setWidth(width);
        this.calculateArea();
        this.calculatePerimeter();

    }

    private void setWidth(double width) {
        if(width <= 0) {
            throw new IllegalArgumentException("Invalid argument!");
        }
        this.width = width;
    }

    private void setHeight(double height) {
        if(height <= 0) {
            throw new IllegalArgumentException("Illegal argument!");
        }
        this.height = height;
    }

    private double getHeight() {
        return height;
    }

    private double getWidth() {
        return width;
    }

    @Override
    protected void calculatePerimeter() {
        setPerimeter(this.getHeight() * 2 + this.getWidth() * 2);
    }

    @Override
    protected void calculateArea() {
        setArea(this.getHeight() * this.getWidth());
    }
}
