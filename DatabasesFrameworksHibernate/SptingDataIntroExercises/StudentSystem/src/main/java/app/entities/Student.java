package app.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "students")
public class Student {

    private Long id;
    private String name;
    private String phoneNumber;
    private Date registrationDate;
    private Date birthday;

    private Set<Course> courses;
    private Set<Homework> homeworkSubmissions;

    public Student() {
    }

    public Student(String name, String phoneNumber, Date registrationDate, Date birthday) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.registrationDate = registrationDate;
        this.birthday = birthday;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "phone_number")
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Column(name = "registration_date", nullable = false, columnDefinition = "DATE")
    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    @Column(name = "birthday", columnDefinition = "DATE")
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @ManyToMany(mappedBy = "students")
    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }

    @OneToMany(mappedBy = "student")
    public Set<Homework> getHomeworkSubmissions() {
        return homeworkSubmissions;
    }

    public void setHomeworkSubmissions(Set<Homework> homeworkSubmissions) {
        this.homeworkSubmissions = homeworkSubmissions;
    }
}

