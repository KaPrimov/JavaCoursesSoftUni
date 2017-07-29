package app.services.impl;

import app.entities.Resource;
import app.repositories.ResourceRepository;
import app.services.api.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ResourceServiceImpl implements ResourceService<Resource, Long> {

    private ResourceRepository dao;

    @Autowired
    public ResourceServiceImpl(ResourceRepository dao) {
        this.dao = dao;
    }

    @Override
    public Resource findByID(Long id) {
        return dao.findOne(id);
    }

    @Override
    public void remove(Resource object) {
        dao.delete(object);
    }

    @Override
    public List<Resource> findAll() {
        return dao.findAll();
    }

    @Override
    public void save(Resource object) {
        dao.save(object);
    }
}
