package com.neckandelbows.domain.ingredients;


public interface ChemicalIngredient extends Ingredient{

    String getChemicalFormula();

    void setChemicalFormula(String chemicalFormula);
}
