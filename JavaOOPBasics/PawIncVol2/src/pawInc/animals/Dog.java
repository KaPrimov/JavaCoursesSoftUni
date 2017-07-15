package pawInc.animals;

public class Dog extends Animal {

    private int commands;

    public Dog(String name, int age, int commands) {
        super(name, age);
        this.commands = commands;
    }

    public Dog(String name, int age, int commands, String adoptionCenter) {
        super(name, age, adoptionCenter);
        setCommands(commands);
    }

    private void setCommands(int commands) {
        this.commands = commands;
    }
}