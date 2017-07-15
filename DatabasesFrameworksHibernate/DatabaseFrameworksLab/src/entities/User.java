package entities;

import annotations.Column;
import annotations.Entity;
import annotations.Id;

import java.time.LocalDate;

@Entity(name = "users")
public class User {

    @Id
    @Column(name = "user_id")
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "age")
    private int age;
    @Column(name = "registration_date")
    private LocalDate registrationDate;

    public User() {}

    public User(String name, int age, LocalDate registrationDate) {
        this.name = name;
        this.age = age;
        this.registrationDate = registrationDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }
}
