package Animals;

public class Tomcat extends Animal{
    public Tomcat(String name, int age) {
        super(name, age, "Male");
    }

    @Override
    public String produceSound() {
        return "Give me one million b***h";
    }
}
