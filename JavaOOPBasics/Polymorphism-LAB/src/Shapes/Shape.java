package Shapes;

public abstract class Shape {
    private Double perimeter;
    private Double area;

    protected void setPerimeter(double perimeter) {
        this.perimeter = perimeter;
    }

    protected void setArea(Double area) {
        this.area = area;
    }

    private Double getPerimeter() {
        return perimeter;
    }

    private Double getArea() {
        return area;
    }

    protected abstract void calculatePerimeter();
    protected abstract void calculateArea();
}
