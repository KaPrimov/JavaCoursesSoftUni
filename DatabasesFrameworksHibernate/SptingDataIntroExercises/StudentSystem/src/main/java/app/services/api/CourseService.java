package app.services.api;

import java.util.List;

public interface CourseService<Course, Long> extends ServiceInterface<Course, Long> {
    List<Object[]> findAllCoursesAndResources();

    List<String> findAllCoursesWithMoreThan3Resources();

    List<String> findAllActiveCourses();
}
