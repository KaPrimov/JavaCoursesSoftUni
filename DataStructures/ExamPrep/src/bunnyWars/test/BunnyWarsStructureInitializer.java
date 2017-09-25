package bunnyWars.test;


import bunnyWars.main.BunnyWarsStructure;
import bunnyWars.main.IBunnyWarsStructure;

public class BunnyWarsStructureInitializer {

    public static IBunnyWarsStructure create() {
        return new BunnyWarsStructure();
    }
}
