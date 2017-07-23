package app.serviceLayer.impl;

import app.dataLayer.LabelsDao;
import app.entities.labels.ClassicLabel;
import app.serviceLayer.api.LabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class LabelServiceImpl implements LabelService<ClassicLabel, Long> {

    private LabelsDao dao;

    @Autowired
    public LabelServiceImpl(LabelsDao dao) {
        this.dao = dao;
    }

    @Override
    public ClassicLabel findByID(Long id) {
        return (ClassicLabel) dao.findOne(id);
    }

    @Override
    public void remove(ClassicLabel object) {
        dao.delete(object);
    }

    @Override
    public List<ClassicLabel> findAll(Class<ClassicLabel> serviceClass) {
        return dao.findAll();
    }

    @Override
    public void save(ClassicLabel object) {
        this.dao.save(object);
    }

}
