package pawinc.models.centers;

import pawinc.models.animals.Animal;

import java.util.List;
import java.util.stream.Collectors;

public class AdoptionCenter extends Center {

    public AdoptionCenter(String name) {
        super(name);
    }

    public void sendForCleansing(CleansingCenter cleansingCenter) {
        List<Animal> forCleansing = super.getAnimals().stream()
                .filter(a -> a.isCleansed().equals("UNCLEANSED"))
                .collect(Collectors.toList());

        super.removeAnimals(forCleansing);

        cleansingCenter.registerAll(forCleansing);
    }

    public List<Animal> adopt() {
        List<Animal> forAdoption = super.getAnimals().stream()
                .filter(a -> a.isCleansed().equals("CLEANSED"))
                .collect(Collectors.toList());

        super.removeAnimals(forAdoption);
        return forAdoption;
    }


    public void sendForCastration(CastrationCenter castrationCenter) {
        List<Animal> forCastration = super.getAnimals().stream()
                .filter(a -> a.isCleansed().equals("UNCLEANSED"))
                .collect(Collectors.toList());

        super.removeAnimals(forCastration);

        castrationCenter.registerAll(forCastration);

    }
}
