package softuni.course.products.services.api;

import org.springframework.stereotype.Component;
import softuni.course.products.dtos.binding.CategoryDto;
import softuni.course.products.dtos.binding.add.CategoryAddDto;
import softuni.course.products.dtos.view.CategoryStatisticView;

import java.util.List;

@Component
public interface CategoryService {
    void save(CategoryAddDto categoryDto);

    List<CategoryDto> findAllDtos();

    List<CategoryStatisticView> findAllByProductsCount();
}
