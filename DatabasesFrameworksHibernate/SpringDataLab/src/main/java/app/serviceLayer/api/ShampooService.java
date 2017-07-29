package app.serviceLayer.api;

import app.entities.shampoos.BasicShampoo;

import java.util.List;

public interface ShampooService<Shampoo, Long> extends ServiceInterface<Shampoo, Long> {

    List<Shampoo> findShampoosContainingIngredient(String ingredient);

    List<BasicShampoo> findAllByBrand(String brand);
}
