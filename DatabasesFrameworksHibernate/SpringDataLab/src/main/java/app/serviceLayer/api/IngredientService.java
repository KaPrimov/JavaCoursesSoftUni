package app.serviceLayer.api;

public interface IngredientService<BasicIngredient, Long> extends ServiceInterface<BasicIngredient, Long> {

    app.entities.ingredients.BasicIngredient findIngredientByName(String name);

}
