package app.repositories;

import app.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query("SELECT s.name, h.content, h.contentType FROM Student AS s INNER JOIN s.homeworkSubmissions AS h")
    List<Object[]> findAllStudentsAndTheirHomeworkSubmissions();

    @Query("SELECT s.name, count(c), sum(c.price), avg(c.price) FROM Student AS s INNER JOIN s.courses AS c " +
            "GROUP BY s.name " +
            "ORDER BY sum(c.price) DESC, s.name ")
    List<Object[]> studentsInfo();
}
