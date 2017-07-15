package Animals;

public abstract class Animal implements soundProducible {

    private static final String ERROR_MESSAGE = "Invalid input!";

    private String name;
    private int age;
    private String gender;

    Animal(String name, int age, String gender) {
        setName(name);
        setAge(age);
        setGender(gender);
    }

    private void setName(String name) {
        if(name == null || name.trim().length() == 0) {
            throw new IllegalArgumentException(ERROR_MESSAGE);
        }
        this.name = name;
    }

    private void setAge(int age) {
        if(age < 0) {
            throw new IllegalArgumentException(ERROR_MESSAGE);
        }
        this.age = age;
    }

    private void setGender(String gender) {
        if(gender == null || gender.trim().length() == 0) {
            throw new IllegalArgumentException(ERROR_MESSAGE);
        }
        this.gender = gender;
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        output.append(this.getClass().getSimpleName()).append(System.lineSeparator());
        output.append(String.format("%s %d %s", this.name, this.age, this.gender));
        return output.toString();
    }
}
