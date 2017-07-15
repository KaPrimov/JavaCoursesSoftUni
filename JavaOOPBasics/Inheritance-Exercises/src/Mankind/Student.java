package Mankind;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Student extends Human {
    private String facultyNumber;

    public Student(String firstName, String lastName, String facultyNumber) {
        super(firstName, lastName);
        this.setFacultyNumber(facultyNumber);
    }

    private void setFacultyNumber(String facultyNumber) {
        if (!isValidFacultyNumber(facultyNumber)){
            throw new IllegalArgumentException("Invalid faculty number!");
        }

        this.facultyNumber = facultyNumber;
    }

    private boolean isValidFacultyNumber(String facultyNumber) {
        String regex = "^[\\da-zA-Z]{5,10}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(facultyNumber);
        return matcher.find();
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        output.append(super.toString());
        output.append("Faculty number: ").append(this.facultyNumber).append(System.lineSeparator());
        return output.toString();
    }
}