package methodOverride;

/**
 * Created by Kalin on 3/2/2017.
 */
public class Square extends Rectangle{
    public Square(double sideA) {
        super(sideA);
    }

    @Override
    protected Double area() {
        return super.getSideA() * this.getSideA();
    }
}
