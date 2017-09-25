package bunnyWars.test.correctness;

import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import bunnyWars.test.helpers.BaseTest;
import bunnyWars.test.types.CorrectnessTests;

public class RoomCount extends BaseTest {

    @Category(CorrectnessTests.class)
    @Test
    public void RoomsCount_WithANewCollection_ShouldBeZero()
    {
        //Assert
        Assert.assertEquals("Collection constructed with an incorrect rooms count!", 0, this.BunnyWarCollection.getRoomCount());
    }
}
