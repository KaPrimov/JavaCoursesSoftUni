package sweepAndPrune;

public abstract class Rectangle {

    private static final double DEFAULT_HEIGHT = 10;
    private static final double DEFAULT_WIDTH = 10;

    private double x1;
    private double y1;
    private double x2;
    private double y2;

    public Rectangle(double x1, double y1) {
        this.setX1(x1);
        this.setY1(y1);
    }

    public boolean isIntersected(Rectangle other) {
        boolean isX1Less = this.x1 <= other.x2;
        boolean isX2More = this.x2 >= other.x1;
        boolean isY1Less = this.y1 <= other.y2;
        boolean isY2More = this.y2 >= other.y1;

        return isX1Less && isX2More && isY1Less && isY2More;
    }

    public final double getX1() {
        return x1;
    }

    public final double getX2() {
        return x2;
    }

    public final void setX1(double x1) {
        this.x1 = x1;
        this.x2 = this.x1 + DEFAULT_WIDTH;
    }

    public final void setY1(double y1) {
        this.y1 = y1;
        this.y2 = this.y1 + DEFAULT_HEIGHT;
    }
}
