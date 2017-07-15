package pawinc.models.centers;

import pawinc.models.animals.Animal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Center {

    private String name;
    private List<Animal> animals;

    protected Center(String name) {
        setName(name);
        this.animals = new ArrayList<>();
    }

    public void register(Animal animal) {
        this.animals.add(animal);
    }

    void registerAll(List<Animal> animals) {
        this.animals.addAll(animals);
    }

    public List<Animal> getAnimals() {
        return Collections.unmodifiableList(this.animals);
    }

    void removeAnimals(List<Animal> animals) {
        this.animals.removeAll(animals);
    }

    private void setName(String name) {
        this.name = name;
    }

    public final String getName() {
        return name;
    }
}
