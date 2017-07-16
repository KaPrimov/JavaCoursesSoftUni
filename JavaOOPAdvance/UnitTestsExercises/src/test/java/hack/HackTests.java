package hack;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

public class HackTests {

    private Hack hack;

    @Before
    public void mockObjects() {
        this.hack = new Hack();
    }


    @Test
    public void mathAbsReturnsAbsoluteValueFromNonNegative() {
        Assert.assertTrue(this.hack.mathAbs(2.0) == 2.0);
    }

    @Test
    public void mathAbsReturnsAbsoluteValueFromNegative() {
        Assert.assertTrue(this.hack.mathAbs(-2.3) == 2.3);
    }

    @Test
    public void mathFloorReturnsTheCorrectAnswerWhenUsedPositiveDouble() {
        Assert.assertEquals(2.0, this.hack.mathFloor(2.4), 0);
    }

    @Test
    public void mathFloorReturnsTheCorrectAnswerWhenUsedNegativeDouble() {
        Assert.assertEquals(-3.0, this.hack.mathFloor(-2.4), 0);
    }

    @Test
    public void systemLineSeparatorAppendsNewLine() {
        String newLine = System.getProperty("line.separator");
        String answer = this.hack.newLine("one", "two");
        Assert.assertTrue(answer.contains(newLine));
    }
}
