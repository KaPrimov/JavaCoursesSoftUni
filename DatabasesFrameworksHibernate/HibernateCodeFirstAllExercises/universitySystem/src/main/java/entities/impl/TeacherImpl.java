package entities.impl;

import entities.api.Teacher;

import javax.persistence.*;
import java.util.Set;

@Entity
//@DiscriminatorValue("Teacher")
@Table(name = "teachers")
public class TeacherImpl extends PersonImpl implements Teacher {

    private String email;

    private double salaryPerHour;

    private Set<CourseImpl> courses;

    public TeacherImpl() {
    }

    @OneToMany(mappedBy = "teacher", targetEntity = CourseImpl.class)
    public Set<CourseImpl> getCourses() {
        return courses;
    }

    public void setCourses(Set<CourseImpl> courses) {
        this.courses = courses;
    }

    public TeacherImpl(String firstName, String lastName, String phoneNumber, String email, double salaryPerHour) {
        super(firstName, lastName, phoneNumber);
        this.email = email;
        this.salaryPerHour = salaryPerHour;
    }

    @Override
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    @Column(name = "salary_per_hour")
    public double getSalaryPerHour() {
        return salaryPerHour;
    }

    @Override
    public void setSalaryPerHour(double salaryPerHour) {
        this.salaryPerHour = salaryPerHour;
    }
}
