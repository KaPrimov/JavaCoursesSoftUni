package pawInc.centers;

import pawInc.animals.Animal;

import java.util.List;
import java.util.stream.Collectors;

public class AdoptionCenter extends Center {

    public AdoptionCenter(String name) {
        super(name);
    }

    public List<Animal> adopt() {
        List<Animal> animalsForAdoption = super.getAnimals().stream()
                .filter(a -> a.getCleansedStatus().equals("CLEANSED"))
                .collect(Collectors.toList());

        super.removeAnimals(animalsForAdoption);
        return animalsForAdoption;
    }

    public void sendForCleansing(CleansingCenter cleansingCenter) {
        List<Animal> forCleansing = super.getAnimals().stream()
                .filter(a -> a.getCleansedStatus().equals("UNCLEANSED"))
                .collect(Collectors.toList());

        super.removeAnimals(forCleansing);

        cleansingCenter.registerAll(forCleansing);
    }
}
