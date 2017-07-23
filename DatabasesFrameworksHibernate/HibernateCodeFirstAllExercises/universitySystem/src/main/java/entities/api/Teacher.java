package entities.api;

import entities.impl.CourseImpl;

import java.util.Set;

public interface Teacher extends Person {

    String getEmail();

    void setEmail(String email);

    double getSalaryPerHour();

    void setSalaryPerHour(double salaryPerHour);

    public Set<CourseImpl> getCourses();

    public void setCourses(Set<CourseImpl> courses);
}
