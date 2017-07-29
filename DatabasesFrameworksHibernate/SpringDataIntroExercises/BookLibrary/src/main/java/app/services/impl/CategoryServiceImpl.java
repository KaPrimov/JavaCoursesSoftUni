package app.services.impl;

import app.entities.Category;
import app.repositories.CategoryRepository;
import app.services.api.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService<Category, Long> {

    private CategoryRepository dao;

    @Autowired
    public CategoryServiceImpl(CategoryRepository bookRepository) {
        this.dao = bookRepository;
    }

    @Override
    public Category findByID(Long id) {
        return this.dao.findOne(id);
    }

    @Override
    public void remove(Category object) {
        this.dao.delete(object);
    }

    @Override
    public List<Category> findAll() {
        return this.dao.findAll();
    }

    @Override
    public void save(Category object) {
        this.dao.save(object);
    }
}
