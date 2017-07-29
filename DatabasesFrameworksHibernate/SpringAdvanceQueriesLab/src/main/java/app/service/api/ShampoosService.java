package app.service.api;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by User on 18.7.2017 г..
 */
public interface ShampoosService<BasicShampoos, Long> extends ServiceInterface<BasicShampoos, Long> {
    void solveAllTasks();

    List<app.model.BasicShampoos> shampoosWithBrand(String brand);

    List<BasicShampoos> shampoosWithIngredient(String ingredientName);

    Integer countAllByPriceLessThan(BigDecimal price);

    List<app.model.BasicShampoos> findBasicShampoosByIngredients(long count);
}
