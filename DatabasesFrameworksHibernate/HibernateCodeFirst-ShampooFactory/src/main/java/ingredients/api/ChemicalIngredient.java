package ingredients.api;

public interface ChemicalIngredient extends Ingredient {
    String getChemicalFormula();

    void setChemicalFormula(String chemicalFormula);
}
