package entities.impl;

import entities.api.Course;
import entities.api.Student;
import entities.api.Teacher;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "courses")
@PrimaryKeyJoinColumn(name = "id")
public class CourseImpl implements Course {

    private long id;
    private String name;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private byte credits;
    private Set<StudentImpl> students;
    private TeacherImpl teacher;

    public CourseImpl() {
    }

    @Override
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "courses_students",
            joinColumns = @JoinColumn(name = "course_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "student_id", referencedColumnName = "id"))
    public Set<StudentImpl> getStudents() {
        return students;
    }

    @Override
    public void setStudents(Set<StudentImpl> students) {
        this.students = students;
    }

    @Override
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "teacher_id", referencedColumnName = "id")
    public TeacherImpl getTeacher() {
        return teacher;
    }

    @Override
    public void setTeacher(TeacherImpl teacher) {
        this.teacher = teacher;
    }

    @Override
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    @Override
    @Column(name = "name")
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    @Column(name = "description", columnDefinition = "TEXT")
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    @Column(name = "start_date", columnDefinition = "DATE")
    public LocalDate getStartDate() {
        return startDate;
    }

    @Override
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    @Override
    @Column(name = "end_date", columnDefinition = "DATE")
    public LocalDate getEndDate() {
        return endDate;
    }

    @Override
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    @Override
    @Column(name = "credits")
    public byte getCredits() {
        return credits;
    }

    @Override
    public void setCredits(byte credits) {
        this.credits = credits;
    }
}
