package entities.api;

import entities.impl.StudentImpl;
import entities.impl.TeacherImpl;

import java.time.LocalDate;
import java.util.Set;

public interface Course {

    long getId();

    void setId(long id);

    String getName();

    void setName(String name);

    String getDescription();

    void setDescription(String description);

    LocalDate getStartDate();

    void setStartDate(LocalDate startDate);

    LocalDate getEndDate();

    void setEndDate(LocalDate endDate);

    byte getCredits();

    void setCredits(byte credits);

        Set<StudentImpl> getStudents();

    void setStudents(Set<StudentImpl> students);

    TeacherImpl getTeacher();

    void setTeacher(TeacherImpl teacher);
}
