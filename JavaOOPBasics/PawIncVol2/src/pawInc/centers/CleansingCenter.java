package pawInc.centers;

import pawInc.animals.Animal;

import java.util.ArrayList;
import java.util.List;

public class CleansingCenter extends Center {

    public CleansingCenter(String name) {
        super(name);
    }

    public List<Animal> cleanse() {
        List<Animal> cleansedAnimals = new ArrayList<>();
        super.getAnimals()
                .forEach(a -> {
                    a.cleanse();
                    cleansedAnimals.add(a);
                });

        super.removeAnimals(cleansedAnimals);

        return cleansedAnimals;

    }
}
