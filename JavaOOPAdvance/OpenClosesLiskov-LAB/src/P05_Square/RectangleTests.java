package P05_Square;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RectangleTests {

    private static final int RECTANGLE_HEIGHT = 5;
    private static final int RECTANGLE_WIDTH = 10;
    private static final int AREA = 50;


    private Rectangle rectangle;

    @Before
    public void initializeObjects() {
        this.rectangle = new Rectangle(RECTANGLE_WIDTH, RECTANGLE_HEIGHT);
    }

    @Test
    public void getSidesTest() {
        Assert.assertEquals(RECTANGLE_WIDTH, this.rectangle.getWidth());
        Assert.assertEquals(RECTANGLE_HEIGHT, this.rectangle.getHeight());
    }

    @Test
    public void getAreaTest() {
        Assert.assertEquals(AREA, this.rectangle.getArea());
    }
}
