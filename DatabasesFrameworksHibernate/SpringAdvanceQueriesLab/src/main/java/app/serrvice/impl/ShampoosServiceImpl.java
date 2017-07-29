package app.serrvice.impl;

import app.dao.api.ClassicLabelDao;
import app.dao.api.ShampoosDao;
import app.model.BasicShampoos;
import app.model.ClassicLabel;
import app.service.api.ShampoosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by User on 18.7.2017 г..
 */
@Service
@Transactional
public class ShampoosServiceImpl implements ShampoosService<BasicShampoos, Long> {

    @Autowired
    private ShampoosDao dao;

    @Autowired
    private ClassicLabelDao classicLabelDao;

    @Override
    public BasicShampoos findById(Long id) {
        return dao.findOne(id);
    }

    @Override
    public void remove(BasicShampoos object) {
        dao.delete(object);
    }

    @Override
    public List<BasicShampoos> findAll(Class<BasicShampoos> serviceClass) {
        return dao.findAll();
    }

    @Override
    public void solveAllTasks() {
        //Problem 1
        List<BasicShampoos> shampsBrand1 = dao.findByBrand("Brand1");
        System.out.println(shampsBrand1);
        //Problem 2
        List<BasicShampoos> shamps = dao.findByBrandAndSize("Brand2", 2);
        System.out.println(shamps);
        //Problem 3
        ClassicLabel classicLabel = classicLabelDao.findOne(1L);
        List<BasicShampoos> shampsWithBrandOrLabel = dao.findByBrandOrLabelOrderByPrice("Brand2", classicLabel);
        System.out.println(shampsWithBrandOrLabel);

        //Problem 4
        List<BasicShampoos> shampsWithPrice = dao.findByPriceGreaterThanOrderByBrandDesc(BigDecimal.valueOf(3.3));
        System.out.println(shampsWithPrice);
    }
    @Override
    public void save(BasicShampoos shampoo) {
        dao.saveAndFlush(shampoo);
    }

    @Override
    public List<BasicShampoos> shampoosWithBrand(String brand) {
        return dao.findByBrand(brand);
    }

    @Override
    public List<BasicShampoos> shampoosWithIngredient(String ingredient) {
        return dao.shampoosWithIngredient(ingredient);
    }

    @Override
    public Integer countAllByPriceLessThan(BigDecimal price) {
        return this.dao.countAllByPriceLessThan(price);
    }

    @Override
    public List<BasicShampoos> findBasicShampoosByIngredients(long count) {
        return this.dao.findBasicShampoosByIngredientsCount(count);

    }
}
