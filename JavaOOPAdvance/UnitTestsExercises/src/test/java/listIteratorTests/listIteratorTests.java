package listIteratorTests;

import iteratorTest.ListIterator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;

public class listIteratorTests {

    private static final String[] ELEMENT_ARRAY = new String[] {"Pesho", "Gosho", "Tosho"};
    private ListIterator<String> strings;

    @Before
    public void initializeObjects() throws OperationNotSupportedException {
        this.strings = new ListIterator<String>(ELEMENT_ARRAY);
    }


    @Test(expected = OperationNotSupportedException.class)
    public void initializeClassWithEmptyArray() throws OperationNotSupportedException {
        ListIterator<String> strings = new ListIterator<String>();
    }

    @Test
    public void moveReturnsTheCorrectBoolean() {
        this.strings.move();
        Assert.assertEquals(true, this.strings.move());
        Assert.assertEquals(true, this.strings.move());
        Assert.assertEquals(false, this.strings.move());
    }

    @Test
    public void hasNextReturnsCorrectBoolean() {
        this.strings.move();
        Assert.assertEquals(true, this.strings.hasNext());
        this.strings.move();
        this.strings.move();
        Assert.assertEquals(false, this.strings.hasNext());

    }

    @Test
    public void printReturnCorrectValues() {
        Assert.assertEquals("Pesho", this.strings.getStrings().get(this.strings.getIndex()));
        this.strings.move();
        this.strings.move();
        Assert.assertEquals("Tosho", this.strings.getStrings().get(this.strings.getIndex()));
    }

}
