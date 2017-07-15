package Mankind;

public abstract class Human {
    private String firstName;
    private String lastName;

    protected Human(String firstName, String lastName) {
        this.setFirstName(firstName);
        this.setLastName(lastName);
    }

    private void setFirstName(String firstName) {
        if (firstName == null || firstName.trim().length() < 4){
            throw new IllegalArgumentException("Expected length at least 4 symbols!Argument: firstName");
        }
        if (!Character.isUpperCase(firstName.charAt(0))){
            throw new IllegalArgumentException("Expected upper case letter!Argument: firstName");
        }
        this.firstName = firstName;
    }

    private void setLastName(String lastName) {
        if (lastName == null || lastName.trim().length() < 3){
            throw new IllegalArgumentException("Expected length at least 3 symbols!Argument: lastName");
        }
        if (!Character.isUpperCase(lastName.charAt(0))){
            throw new IllegalArgumentException("Expected upper case letter!Argument: lastName");
        }
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        output.append("First Name: ").append(this.firstName).append(System.lineSeparator());
        output.append("Last Name: ").append(this.lastName).append(System.lineSeparator());

        return output.toString();
    }
}
