package app.services.impl;

import app.entities.Homework;
import app.repositories.HomeworkRepository;
import app.services.api.HomeworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class HomeworkServiceImpl implements HomeworkService<Homework, Long> {

    private HomeworkRepository dao;

    @Autowired
    public HomeworkServiceImpl(HomeworkRepository dao) {
        this.dao = dao;
    }

    @Override
    public Homework findByID(Long id) {
        return dao.findOne(id);
    }

    @Override
    public void remove(Homework object) {
        dao.delete(object);
    }

    @Override
    public List<Homework> findAll() {
        return dao.findAll();
    }

    @Override
    public void save(Homework object) {
        dao.save(object);
    }
}
