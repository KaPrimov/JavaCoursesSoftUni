package pawinc.models.centers;

import pawinc.models.animals.Animal;

import java.util.List;
import java.util.stream.Collectors;

public class CastrationCenter extends Center {
    public CastrationCenter(String name) {
        super(name);
    }

    public List<Animal> castrate() {
        List<Animal> castrated = super.getAnimals().stream().collect(Collectors.toList());
        super.removeAnimals(castrated);

        return castrated;
    }
}
