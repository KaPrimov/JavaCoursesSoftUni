package pawinc.models.animals;

public abstract class Animal {
    private String name;
    private int age;
    private String isCleansed;
    private String adoptionCenter;

    protected Animal(String name, int age) {
        setName(name);
        setAge(age);
    }

    protected Animal(String name, int age, String adoptionCenter) {
        this(name, age);
        this.isCleansed = "UNCLEANSED";
        setAdoptionCenter(adoptionCenter);
    }
    public final String isCleansed() {
        return isCleansed;
    }

    public final void cleanse() {
        isCleansed = "CLEANSED";
    }

    public String getCenterName() {
        return this.adoptionCenter;
    }

    public final String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    private void setAge(int age) {
        this.age = age;
    }

    private void setAdoptionCenter(String adoptionCenter) {
        this.adoptionCenter = adoptionCenter;
    }
}
