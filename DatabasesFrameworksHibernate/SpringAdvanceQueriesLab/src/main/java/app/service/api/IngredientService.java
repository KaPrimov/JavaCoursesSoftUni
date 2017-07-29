package app.service.api;

import app.model.BasicChemicatIngredient;
import app.model.BasicIngredient;
import app.model.BasicShampoos;

import java.util.List;

/**
 * Created by User on 18.7.2017 г..
 */
public interface IngredientService<BasicIngredient, Long> extends ServiceInterface<BasicIngredient, Long> {
    List<app.model.BasicIngredient> findByNameEndsWith (String suffix);
    List<BasicShampoos> shampoosWithIngredient(BasicIngredient ingredient);

List<BasicChemicatIngredient> findChemicalIngWithFormula(String formula);

    List<app.model.BasicIngredient> findAllIngrWithoutPrice();

    List<app.model.BasicIngredient> findByNameStartingWith(String letter);
}
