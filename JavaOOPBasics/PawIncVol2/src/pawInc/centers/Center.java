package pawInc.centers;

import pawInc.animals.Animal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Center {

    private String name;
    private List<Animal> animals;

    protected Center(String name) {
        this.name = name;
        this.animals = new ArrayList<>();
    }

    void registerAll(List<Animal> animals) {
        this.animals.addAll(animals);
    }

    public void registerAnimal(Animal animal) {
        this.animals.add(animal);
    }

    public List<Animal> getAnimals() {
        return Collections.unmodifiableList(this.animals);
    }

    protected void removeAnimals(List<Animal> animals) {
        this.animals.removeAll(animals);
    }

    private void setName(String name) {
        this.name = name;
    }

    public final String getName() {
        return name;
    }
}
