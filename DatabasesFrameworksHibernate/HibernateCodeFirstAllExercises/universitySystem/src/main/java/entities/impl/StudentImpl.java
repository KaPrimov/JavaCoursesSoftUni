package entities.impl;

import entities.api.Course;
import entities.api.Student;

import javax.persistence.*;
import java.util.Set;

@Entity
//@DiscriminatorValue("Student")
@Table(name = "students")
@PrimaryKeyJoinColumn(name = "id")
public class StudentImpl extends PersonImpl implements Student {

    private double averageGrade;

    private String attendance;

    private Set<CourseImpl> courses;

    public StudentImpl() {
    }

    @ManyToMany(mappedBy = "students", targetEntity = CourseImpl.class)
    public Set<CourseImpl> getCourses() {
        return courses;
    }

    public void setCourses(Set<CourseImpl> courses) {
        this.courses = courses;
    }

    public StudentImpl(String firstName, String lastName, String phoneNumber, double averageGrade, String attendance) {
        super(firstName, lastName, phoneNumber);
        this.averageGrade = averageGrade;
        this.attendance = attendance;
    }

    @Override
    @Column(name = "average_grade")
    public double getAverageGrade() {
        return averageGrade;
    }

    @Override
    public void setAverageGrade(double averageGrade) {
        this.averageGrade = averageGrade;
    }

    @Override
    @Column(name = "attendance")
    public String getAttendance() {
        return attendance;
    }

    @Override
    public void setAttendance(String attendance) {
        this.attendance = attendance;
    }
}
