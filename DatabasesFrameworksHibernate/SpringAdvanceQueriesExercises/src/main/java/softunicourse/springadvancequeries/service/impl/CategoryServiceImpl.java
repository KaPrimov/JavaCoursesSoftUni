package softunicourse.springadvancequeries.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import softunicourse.springadvancequeries.dao.api.CategoriesDao;
import softunicourse.springadvancequeries.domain.Category;
import softunicourse.springadvancequeries.service.api.CategoryService;

import java.util.List;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService<Category, Long> {
    private CategoriesDao categoriesDao;

    @Autowired
    public CategoryServiceImpl(CategoriesDao categoriesDao) {
        this.categoriesDao = categoriesDao;
    }

    @Override
    public Category findById(Long id) {
        return this.categoriesDao.findOne(id);
    }

    @Override
    public void remove(Category object) {
        this.categoriesDao.delete(object);
    }

    @Override
    public List<Category> findAll(Class<Category> object) {
        return this.categoriesDao.findAll();
    }

    @Override
    public void save(Category object) {
        this.categoriesDao.save(object);
    }

    @Override
    public Category findCategoryByName(String name) {
        name = Character.toUpperCase(name.charAt(0)) + name.substring(1, name.length()).toLowerCase();
        return this.categoriesDao.findByName(name);
    }

    @Override
    public List<Object[]> findTotalProfitByCategory() {
        return this.categoriesDao.findTotalProfitByCategory();
    }

    @Override
    public List<Category> findCategoriesWithMoreThan35Books() {
        return this.categoriesDao.findCategoriesWithMoreThan35Books();
    }
}
