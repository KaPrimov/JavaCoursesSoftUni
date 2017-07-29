package softunicourse.springadvancequeries.service.api;

import java.util.List;

public interface CategoryService<Category, Long> extends ServiceInterface<Category, Long> {
    softunicourse.springadvancequeries.domain.Category findCategoryByName(String name);

    List<Object[]> findTotalProfitByCategory();

    List<softunicourse.springadvancequeries.domain.Category> findCategoriesWithMoreThan35Books();
}
