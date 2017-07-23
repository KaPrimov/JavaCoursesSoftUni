package app.serviceLayer.impl;

import app.dataLayer.IngredientsDao;
import app.entities.ingredients.BasicIngredient;
import app.serviceLayer.api.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class IngredientServiceImpl implements IngredientService<BasicIngredient, Long> {

    private IngredientsDao dao;

    @Autowired
    public IngredientServiceImpl(IngredientsDao dao) {
        this.dao = dao;
    }

    @Override
    public BasicIngredient findIngredientByName(String name) {
        return dao.findByName(name);
    }

    @Override
    public BasicIngredient findByID(Long id) {
        return this.dao.findOne(id);
    }

    @Override
    public void remove(BasicIngredient object) {
        this.dao.delete(object);
    }

    @Override
    public List<BasicIngredient> findAll(Class<BasicIngredient> serviceClass) {
        return this.dao.findAll();
    }

    @Override
    public void save(BasicIngredient object) {
        this.dao.save(object);
    }
}
