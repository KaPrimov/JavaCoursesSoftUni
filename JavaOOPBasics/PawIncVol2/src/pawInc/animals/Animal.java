package pawInc.animals;

public abstract class Animal {
    private String name;
    private int age;
    private String cleansedStatus;
    private String adoptionCenter;

    protected Animal(String name, int age) {
        setName(name);
        setAge(age);
        this.cleansedStatus = "UNCLEANSED";
    }

    protected Animal(String name, int age, String adoptionCenter) {
        this(name, age);
        this.adoptionCenter = adoptionCenter;
    }

    public void cleanse() {
        this.setCleansedStatus("CLEANSED");
    }

    private void setCleansedStatus(String cleansedStatus) {
        this.cleansedStatus = cleansedStatus;
    }

    public final String getCleansedStatus() {
        return cleansedStatus;
    }

    public final String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public final int getAge() {
        return age;
    }

    private void setAge(int age) {
        this.age = age;
    }

    public final String getAdoptionCenter() {
        return adoptionCenter;
    }
}
