package bubbleTests;


import bubbleSort.Bubble;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BubbleTests {

    private static final String[] STRING_ARRAY = new String[] {"Pesho", "Gosho", "Tosho"};
    private static final Integer[] INTEGER_ARRAY = new Integer[] {4, 5, 1, 12, 5};

    private Bubble<Integer> integerBubble;
    private Bubble<String> stringBubble;

    @Before
    public void initializeObjects() {
        integerBubble = new Bubble<Integer>(INTEGER_ARRAY);
        stringBubble = new Bubble<String>(STRING_ARRAY);
    }

    @Test(expected = IllegalArgumentException.class)
    public void bubbleClassCanNotBeInstantiatedWithZeroArguments() {
        Bubble<String> bubble = new Bubble<String>();
    }

    @Test
    public void stringBubbleKeepTheRightOrder() {
        Assert.assertEquals("[Gosho, Pesho, Tosho]", stringBubble.returnElements().toString());
    }

    @Test
    public void integerBubbleKeepTheRightOrder() {
        Assert.assertEquals("[1, 4, 5, 5, 12]", integerBubble.returnElements().toString());
    }

    @Test
    public void bubbleWorksWithOneElement() {
        Bubble<Integer> integerBubble = new Bubble<Integer>(1);
        Assert.assertEquals("[1]", integerBubble.returnElements().toString());
    }

    @Test
    public void bubbleWorksWithTwoElements() {
        Bubble<Integer> integerBubble = new Bubble<Integer>(7, 1);
        Assert.assertEquals("[1, 7]", integerBubble.returnElements().toString());
    }

}
