package app.services.impl;

import app.entities.Course;
import app.repositories.CourseRepository;
import app.services.api.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CourseServiceImpl implements CourseService<Course, Long> {

    private CourseRepository dao;

    @Autowired
    public CourseServiceImpl(CourseRepository dao) {
        this.dao = dao;
    }

    @Override
    public Course findByID(Long id) {
        return dao.findOne(id);
    }

    @Override
    public void remove(Course object) {
        dao.delete(object);
    }

    @Override
    public List<Course> findAll() {
        return dao.findAll();
    }

    @Override
    public void save(Course object) {
        dao.save(object);
    }

    @Override
    public List<Object[]> findAllCoursesAndResources() {
        return this.dao.findAllCoursesAndResources();
    }

    @Override
    public List<String> findAllCoursesWithMoreThan3Resources() {
        return this.dao.findAllCoursesWithMoreThan3Resources();
    }

    @Override
    public List<String> findAllActiveCourses() {
        return this.dao.findAllActiveCourses();
    }
}
