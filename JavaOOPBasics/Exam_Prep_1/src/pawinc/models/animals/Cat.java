package pawinc.models.animals;

public class Cat extends Animal {

    private int intelligence;

    public Cat(String name, int age, int intelligence) {
        super(name, age);
        this.intelligence = intelligence;
    }

    public Cat(String name, int age, int intelligence, String adoptionCenter) {
        super(name, age, adoptionCenter);
        setIntelligence(intelligence);
    }

    private void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }
}
