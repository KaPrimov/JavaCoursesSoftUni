package app.services.impl;

import app.entities.Student;
import app.repositories.StudentRepository;
import app.services.api.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class StudentServiceImpl implements StudentService<Student, Long> {

    private StudentRepository dao;

    @Autowired
    public StudentServiceImpl(StudentRepository dao) {
        this.dao = dao;
    }

    @Override
    public Student findByID(Long id) {
        return dao.findOne(id);
    }

    @Override
    public void remove(Student object) {
        dao.delete(object);
    }

    @Override
    public List<Student> findAll() {
        return dao.findAll();
    }

    @Override
    public void save(Student object) {
        dao.save(object);
    }

    @Override
    public List<Object[]> findAllStudentsAndTheirHomeworks() {
        return this.dao.findAllStudentsAndTheirHomeworkSubmissions();
    }

    @Override
    public List<Object[]> studentsInfo() {
        return this.dao.studentsInfo();
    }
}
