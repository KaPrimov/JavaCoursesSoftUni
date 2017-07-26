package app.repositories;

import app.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    @Query("SELECT c.name, r FROM Course AS c INNER JOIN c.resources r ORDER BY c.startDate ASC, c.endDate DESC ")
    List<Object[]> findAllCoursesAndResources();

    @Query(value = "SELECT concat(c.name, ' ', count(*)) FROM courses AS c \n" +
            "INNER JOIN resources AS r ON\n" +
            "r.course_id = c.id\n" +
            "GROUP BY c.name\n" +
            "HAVING count(*) >= 3 \n" +
            "ORDER BY count(*) DESC, c.start_date ASC ", nativeQuery = true)
    List<String> findAllCoursesWithMoreThan3Resources();

    @Query("SELECT concat(c.name,' ', c.startDate, ' ', c.endDate, ' ', datediff(c.endDate, c.startDate), ' ', count(s)) " +
            "FROM Course AS c INNER JOIN c.students AS s " +
            "GROUP BY c.name, c.startDate, c.endDate " +
            "HAVING c.startDate > '2010-03-20' AND c.endDate < '2016-03-20' " +
            "ORDER BY count(s) DESC, datediff(c.endDate, c.startDate) DESC")
    List<String> findAllActiveCourses();

}
