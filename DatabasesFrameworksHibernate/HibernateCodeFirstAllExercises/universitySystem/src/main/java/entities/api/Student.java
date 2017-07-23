package entities.api;

import entities.impl.CourseImpl;

import java.util.Set;

public interface Student extends Person {

    double getAverageGrade();

    void setAverageGrade(double averageGrade);

    String getAttendance();

    void setAttendance(String attendance);

    Set<CourseImpl> getCourses();

    void setCourses(Set<CourseImpl> courses);
}
