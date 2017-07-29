package app.serviceLayer.impl;

import app.dataLayer.ShampoosDao;
import app.entities.shampoos.BasicShampoo;
import app.serviceLayer.api.ShampooService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ShampooServiceImpl implements ShampooService<BasicShampoo, Long> {

    private ShampoosDao dao;

    @Autowired
    public ShampooServiceImpl(ShampoosDao dao) {
        this.dao = dao;
    }

    @Override
    public List<BasicShampoo> findShampoosContainingIngredient(String ingredient) {
        return this.dao.shampoosWithIngredient(ingredient);
    }

    @Override
    public BasicShampoo findByID(Long id) {
        return this.dao.findOne(id);
    }

    @Override
    public void remove(BasicShampoo object) {
        this.dao.delete(object);
    }

    @Override
    public List<BasicShampoo> findAll(Class<BasicShampoo> serviceClass) {
        return this.dao.findAll();
    }

    @Override
    public void save(BasicShampoo object) {
        this.dao.save(object);
    }

    @Override
    public List<BasicShampoo> findAllByBrand(String brand) {
        return this.dao.findAllByBrand(brand);
    }
}
