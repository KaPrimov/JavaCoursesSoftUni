package app.serviceLayer.api;

import java.util.List;

public interface ShampooService<Shampoo, Long> extends ServiceInterface<Shampoo, Long> {

    List<Shampoo> findShampoosContainingIngredient(String ingredient);

}
