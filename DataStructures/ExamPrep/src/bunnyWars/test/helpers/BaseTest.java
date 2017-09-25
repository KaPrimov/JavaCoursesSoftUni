package bunnyWars.test.helpers;


import bunnyWars.main.IBunnyWarsStructure;
import bunnyWars.test.BunnyWarsStructureInitializer;
import org.junit.Before;

public class BaseTest {
    protected IBunnyWarsStructure BunnyWarCollection;

    @Before
    public void setUp() {
        this.BunnyWarCollection = BunnyWarsStructureInitializer.create();
    }
}
