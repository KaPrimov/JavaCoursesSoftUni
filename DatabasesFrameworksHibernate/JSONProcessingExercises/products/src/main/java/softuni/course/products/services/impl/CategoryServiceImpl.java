package softuni.course.products.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import softuni.course.products.dtos.binding.CategoryDto;
import softuni.course.products.dtos.binding.add.CategoryAddDto;
import softuni.course.products.dtos.view.CategoryStatisticView;
import softuni.course.products.entities.Category;
import softuni.course.products.repositories.CategoryRepository;
import softuni.course.products.services.api.CategoryService;
import softuni.course.products.utils.ModelParser;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    public CategoryServiceImpl() {
    }

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void save(CategoryAddDto categoryDto) {
        Category category = ModelParser.getInstance().map(categoryDto, Category.class);

        this.categoryRepository.saveAndFlush(category);
    }

    @Override
    public List<CategoryDto> findAllDtos() {
        List<Category> categories = this.categoryRepository.findAll();
        List<CategoryDto> categoryDtos = new ArrayList<>();

        for (Category category : categories) {
            categoryDtos.add(ModelParser.getInstance().map(category, CategoryDto.class));
        }
        return categoryDtos;
    }

    @Override
    public List<CategoryStatisticView> findAllByProductsCount() {
        return this.categoryRepository.findAllByProductsCount();
    }
}
