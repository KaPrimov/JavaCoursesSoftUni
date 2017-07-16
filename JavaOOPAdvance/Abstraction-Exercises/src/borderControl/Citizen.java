package borderControl;

public class Citizen implements Identifiable, Birthdable, Buyer{

    private String name;
    private int age;
    private String id;
    private String birthday;
    private int food;

    public Citizen(String name, int age, String id, String birthday) {
        this.name = name;
        this.age = age;
        this.id = id;
        this.birthday = birthday;
    }

    @Override
    public int getFood() {
        return this.food;
    }

    @Override
    public void buyFood() {
        this.food += 10;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public String getBirthday() {
        return this.birthday;
    }
}
