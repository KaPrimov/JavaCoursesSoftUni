package app.services.api;

import java.util.List;

public interface StudentService<Student, Long> extends ServiceInterface<Student, Long> {

    List<Object[]> findAllStudentsAndTheirHomeworks();

    List<Object[]> studentsInfo();

}
