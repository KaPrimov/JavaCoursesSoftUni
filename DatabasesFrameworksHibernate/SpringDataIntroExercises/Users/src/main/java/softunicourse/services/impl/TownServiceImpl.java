package softunicourse.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import softunicourse.repositories.TownRepository;
import softunicourse.services.api.TownService;
import softunicourse.users.entities.Town;

import java.util.List;

@Service
@Transactional
public class TownServiceImpl implements TownService<Town, Long> {

    private TownRepository dao;

    @Autowired
    public TownServiceImpl(TownRepository dao) {
        this.dao = dao;
    }

    @Override
    public Town findByID(Long id) {
        return this.dao.findOne(id);
    }

    @Override
    public void remove(Town object) {
        this.dao.delete(object);
    }

    @Override
    public List<Town> findAll() {
        return this.dao.findAll();
    }

    @Override
    public void save(Town object) {
        this.dao.save(object);
    }
}
